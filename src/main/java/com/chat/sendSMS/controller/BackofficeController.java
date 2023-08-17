package com.chat.sendSMS.controller;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.service.BackofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BackofficeController {
    @Autowired
    private BackofficeService backofficeService;

    @PostMapping("/credit/{customerId}")
    public ResponseEntity<String> includeCredit(@PathVariable Long customerId, @RequestParam double value) {
        backofficeService.includeCredit(customerId, value);
        return ResponseEntity.ok("Credit added successfully.");
    }

    @GetMapping("/balance/{customerId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long customerId) {
        double balance = backofficeService.checkBalance(customerId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/limit/{customerId}")
    public ResponseEntity<String> changeLimit(@PathVariable Long customerId, @RequestParam double newLimit) {
        backofficeService.alterLimit(customerId, newLimit);
        return ResponseEntity.ok("Limit changed successfully.");
    }

    @PostMapping("/plan/{customerId}")
    public ResponseEntity<String> changePlan(@PathVariable Long customerId, @RequestParam String newPlan) {
        backofficeService.alterPlan(customerId, newPlan);
        return ResponseEntity.ok("Plan changed successfully.");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerData(@PathVariable Long customerId) {
        CustomerDTO customerDTO = backofficeService.seeCustomerData(customerId);
        return ResponseEntity.ok(customerDTO);
    }
}








