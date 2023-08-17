package com.chat.sendSMS.dto;

import com.chat.sendSMS.entity.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class CustomerDTO {
    private Long Id;

    private double limit;

    private double credit;

    private String plan;

    private double balance;

    private String customerName;

    private String customerCPF;

    private String customerCNPJ;

    private String customerCompanyName;

    private String customerNumberTelephone;

    private List<Message> messages = new ArrayList<>();
}
