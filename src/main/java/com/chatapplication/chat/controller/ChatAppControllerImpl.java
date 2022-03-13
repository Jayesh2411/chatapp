package com.chatapplication.chat.controller;

import com.chatapplication.chat.exceptions.UserNotFoundException;
import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.service.ChatAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatAppControllerImpl implements ChatAppController {
    Logger logger = LoggerFactory.getLogger(ChatAppControllerImpl.class);
    @Autowired
    ChatAppService chatAppService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        logger.info("sending message to " + message.getReceiverID() + " from " + message.getSenderID());
        try {
            chatAppService.send(message);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        logger.info("index page");
        return new ResponseEntity<>("Welcome to Chat Application", HttpStatus.CREATED);
    }
}
