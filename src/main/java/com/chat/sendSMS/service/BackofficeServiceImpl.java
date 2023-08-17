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
import java.util.Optional;

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

        customer.setCustomerLimit(customerDTO.getCustomerLimit());
        customer.setCredit(customerDTO.getCredit());
        customer.setPlan(customerDTO.getPlan());
        customer.setBalance(customerDTO.getBalance());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerCPF(customerDTO.getCustomerCPF());
        customer.setCustomerCNPJ(customerDTO.getCustomerCNPJ());
        customer.setCustomerCompanyName(customerDTO.getCustomerCompanyName());
        customer.setCustomerNumberTelephone(customerDTO.getCustomerNumberTelephone());
        customer.setMessages(customerDTO.getMessages());

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
        customer.setCustomerLimit(limit);
        customerRepository.save(customer);
    }


    @Override
    public double checkBalance(Long customerId) {
        double value = 0;
         Customer customer = customerRepository.findById(customerId)
                 .orElseThrow(()->new RuntimeException("Customer not found"));
         value = customer.getBalance();
        return value;
    }

    @Override
    public Customer seeCustomerData(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.get();
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
                .customerLimit(customer.getCustomerLimit())
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
                .customerLimit(customer.getCustomerLimit())
                .plan(customer.getPlan())
                .build();
    }

    private MessageDTO mapMessageToDTO(Message message){
       return MessageDTO.builder()
               .idCustomer(message.getIdCustomer())
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
        return message;
    }


}
