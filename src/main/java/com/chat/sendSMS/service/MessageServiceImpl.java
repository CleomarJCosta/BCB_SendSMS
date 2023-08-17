package com.chat.sendSMS.service;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;
import com.chat.sendSMS.entity.Customer;
import com.chat.sendSMS.entity.Message;
import com.chat.sendSMS.repository.CustomerRepository;
import com.chat.sendSMS.repository.MessageRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRespository messageRespository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void sendSMS(Message message) {
        Long customerId = message.getIdCustomer();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if ("pre-pago".equalsIgnoreCase(customer.getPlan())) {
            if (customer.getCredit() >= 0.25) {
                customer.setCredit(customer.getCredit() - 0.25);
                message.setIdCustomer(customerId);
                messageRespository.save(message);
            } else {
                throw new RuntimeException("Insufficient credits for sending SMS.");
            }
        } else if ("pós-pago".equalsIgnoreCase(customer.getPlan())) {
            if (customer.getBalance() + 0.25 <= customer.getCustomerLimit()) {
                customer.setBalance(customer.getBalance() + 0.25);
                message.setIdCustomer(customerId);
                messageRespository.save(message);

            } else {
                throw new RuntimeException("Exceeded balance limit for sending SMS.");
            }
        } else {
            throw new RuntimeException("Invalid customer plan.");

        }
    }

}
