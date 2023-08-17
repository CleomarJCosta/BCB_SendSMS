package com.chat.sendSMS.service;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;

import java.util.List;

public interface BackofficeService {

    public void createCustomer(CustomerDTO customerDTO);
    public void includeCredit(Long customerId, double value);
    public void alterPlan(Long customerId, String plan);
    public void alterLimit(Long customerId, double limit);
    public List<CustomerDTO> seeCustomerData();
    public double checkBalance();

    public void sendSMS(MessageDTO messageDTO, CustomerDTO customerDTO);



}
