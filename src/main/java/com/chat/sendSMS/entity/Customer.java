package com.chat.sendSMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
    @Column
    private double limit;
    @Column
    private double credit;
    @Column
    private String plan;
    @Column
    private double balance;
    @Column
    private String customerName;
    @Column
    private String customerCPF;
    @Column
    private String customerCNPJ;
    @Column
    private String customerCompanyName;
    @Column
    private String customerNumberTelephone;

}
