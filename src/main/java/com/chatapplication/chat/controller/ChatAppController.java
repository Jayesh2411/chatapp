package com.chatapplication.chat.controller;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import org.springframework.http.ResponseEntity;

public interface ChatAppController {

    ResponseEntity<Message> sendMessage(Message message);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<String> welcome();

    ResponseEntity<Message> getMessage(Integer id);

    ResponseEntity<User> getUser(Integer id);
}
