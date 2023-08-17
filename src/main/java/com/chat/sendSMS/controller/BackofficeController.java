package com.chat.sendSMS.controller;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;
import com.chat.sendSMS.entity.Customer;
import com.chat.sendSMS.entity.Message;
import com.chat.sendSMS.service.BackofficeService;
import com.chat.sendSMS.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {
    @Autowired
    private BackofficeService backofficeService;
    @Autowired
    private MessageService messageService;
    @PostMapping("/customer")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO){
        backofficeService.createCustomer(customerDTO);
        return ResponseEntity.ok("Customer added sucessfuly");
    }

    @PostMapping("/send-sms")
    public ResponseEntity<String> chat(@RequestBody Message message) {
        try {
            messageService.sendSMS(message);
            return ResponseEntity.ok("SMS sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/credit/{customerId}")
    public ResponseEntity<String> includeCredit(@PathVariable Long customerId, @RequestParam double value) {
        backofficeService.includeCredit(customerId, value);
        return ResponseEntity.ok("Credit added successfully.");
    }

    @GetMapping("/balance/{customerId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long customerId) {
        double balance = backofficeService.checkBalance(customerId);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/limit/{customerId}")
    public ResponseEntity<String> changeLimit(@PathVariable Long customerId, @RequestParam double newLimit) {
        backofficeService.alterLimit(customerId, newLimit);
        return ResponseEntity.ok("Limit changed successfully.");
    }

    @PutMapping("/plan/{customerId}")
    public ResponseEntity<String> changePlan(@PathVariable Long customerId, @RequestParam String newPlan) {
        backofficeService.alterPlan(customerId, newPlan);
        return ResponseEntity.ok("Plan changed successfully.");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerData(@PathVariable Long customerId) {
        Customer customer = backofficeService.seeCustomerData(customerId);
        return ResponseEntity.ok(customer);
    }
}








