package com.chat.sendSMS.dto;

import com.chat.sendSMS.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class MessageDTO {
    private Long idCustomer;
    private Customer customer;
    private String numberTelephone;
    private boolean isWhatsApp = false;
    private String text;
}
