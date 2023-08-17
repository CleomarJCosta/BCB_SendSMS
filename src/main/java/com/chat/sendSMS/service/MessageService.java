package com.chat.sendSMS.service;

import com.chat.sendSMS.dto.CustomerDTO;
import com.chat.sendSMS.dto.MessageDTO;
import com.chat.sendSMS.entity.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    public void sendSMS(Message message);
}
