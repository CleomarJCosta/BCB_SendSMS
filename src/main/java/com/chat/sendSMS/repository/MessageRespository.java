package com.chat.sendSMS.repository;

import com.chat.sendSMS.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRespository extends JpaRepository<Message, Long> {
}
