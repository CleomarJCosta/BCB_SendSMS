package com.chat.sendSMS.service;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;
import com.chat.sendSMS.entity.Customer;
import com.chat.sendSMS.entity.Message;
import com.chat.sendSMS.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BackofficeServiceImpl implements BackofficeService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapDTOToCustomer(customerDTO);
        customerRepository.save(customer);
    }

    private Customer mapDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setLimit(customerDTO.getLimit());
        customer.setCredit(customerDTO.getCredit());
        customer.setPlan(customerDTO.getPlan());
        customer.setBalance(customerDTO.getBalance());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerCPF(customerDTO.getCustomerCPF());
        customer.setCustomerCNPJ(customerDTO.getCustomerCNPJ());
        customer.setCustomerCompanyName(customerDTO.getCustomerCompanyName());
        customer.setCustomerNumberTelephone(customerDTO.getCustomerNumberTelephone());
        return customer;

    }

    public void includeCredit(Long customerId, double value){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCredit(customer.getCredit() + value);
        customerRepository.save(customer);

    }

    @Override
    public void alterPlan(Long customerId, String plan) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setPlan(plan);
        customerRepository.save(customer);
    }

    @Override
    public void alterLimit(Long customerId, double limit) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow( () -> new RuntimeException("Customer not found"));
        customer.setLimit(limit);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> seeCustomerData() {
        List<Customer> customers = customerRepository.findAll();
        return convertToDTOList(customers);
    }

    private List<CustomerDTO> convertToDTOList(List<Customer> customers) {
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(convertToDTO(customer));
        }
        return customerDTOs;
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.builder()
                .Id(customer.getId())
                .limit(customer.getLimit())
                .credit(customer.getCredit())
                .plan(customer.getPlan())
                .balance(customer.getBalance())
                .customerName(customer.getCustomerName())
                .customerCPF(customer.getCustomerCPF())
                .customerCNPJ(customer.getCustomerCNPJ())
                .customerCompanyName(customer.getCustomerCompanyName())
                .customerNumberTelephone(customer.getCustomerNumberTelephone())
                .build();
    }

    @Override
    public double checkBalance() {
        return 0;
    }

    @Override
    public void sendSMS(MessageDTO messageDTO, CustomerDTO customerDTO) {
        Long customerId = customerDTO.getId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if ("pre-pago".equalsIgnoreCase(customer.getPlan())) {
            if (customer.getCredit() >= 0.25) {
                customer.setCredit(customer.getCredit() - 0.25);
                saveMessage(messageDTO, customerDTO);

            } else {
                throw new RuntimeException("Insufficient credits for sending SMS.");
            }
        } else if ("pós-pago".equalsIgnoreCase(customer.getPlan())) {
            if (customer.getBalance() + 0.25 <= customer.getLimit()) {
                customer.setBalance(customer.getBalance() + 0.25);
                saveMessage(messageDTO, customerDTO);

            } else {
                throw new RuntimeException("Exceeded balance limit for sending SMS.");
            }
        } else {
            throw new RuntimeException("Invalid customer plan.");
        }
    }

    private void saveMessage(MessageDTO messageDTO, CustomerDTO customerDTO) {
        Message newMessage = new Message();
        newMessage = mapDTOToMessage(messageDTO);

        Customer customer = new Customer();
        customer = mapDTOToCustomer(customerDTO);

        customer.getMessages().add(newMessage);
        newMessage.setCustomer(mapDTOToCustomer(customerDTO));

        customerRepository.save(customer);
    }

    private CustomerDTO mapCustomerToDTO(Customer customer){
        return CustomerDTO.builder()
                .Id(customer.getId())
                .credit(customer.getCredit())
                .customerCNPJ(customer.getCustomerCNPJ())
                .customerCPF(customer.getCustomerCPF())
                .customerName(customer.getCustomerName())
                .customerNumberTelephone(customer.getCustomerNumberTelephone())
                .customerCompanyName(customer.getCustomerCompanyName())
                .balance(customer.getBalance())
                .limit(customer.getLimit())
                .plan(customer.getPlan())
                .build();
    }

    private MessageDTO mapMessageToDTO(Message message){
       return MessageDTO.builder()
               .id(message.getId())
               .text(message.getText())
               .customer(message.getCustomer())
               .isWhatsApp(message.isWhatsApp())
               .build();
    }

    private Message mapDTOToMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setText(messageDTO.getText());
        message.setCustomer(messageDTO.getCustomer());
        message.setWhatsApp(message.isWhatsApp());
        // Set other message properties as needed
        return message;
    }


}
