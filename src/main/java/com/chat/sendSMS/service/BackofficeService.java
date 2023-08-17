package com.chat.sendSMS.service;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;
import com.chat.sendSMS.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BackofficeService {

    public void createCustomer(CustomerDTO customerDTO);
    public void includeCredit(Long customerId, double value);
    public void alterPlan(Long customerId, String plan);
    public void alterLimit(Long customerId, double limit);
    public Customer seeCustomerData(Long customerId);
    public double checkBalance(Long customerId);





}
