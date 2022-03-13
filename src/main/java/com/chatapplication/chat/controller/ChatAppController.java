package com.chatapplication.chat.controller;

import com.chatapplication.chat.model.Message;
import org.springframework.http.ResponseEntity;

public interface ChatAppController {

    ResponseEntity<Message> sendMessage(Message message);

    ResponseEntity<String> welcome();
}
