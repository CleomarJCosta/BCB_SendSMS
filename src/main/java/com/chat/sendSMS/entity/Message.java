package com.chat.sendSMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column
    private String numberTelephone;
    @Column
    private boolean isWhatsApp;

    public boolean isWhatsApp() {
        return isWhatsApp;
    }

    public void setWhatsApp(boolean whatsApp) {
        isWhatsApp = whatsApp;
    }

    @Column
    private String text;



}
