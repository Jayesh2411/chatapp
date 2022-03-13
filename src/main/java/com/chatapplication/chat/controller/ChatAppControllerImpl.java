package com.chatapplication.chat.controller;

import com.chatapplication.chat.exceptions.UserNotFoundException;
import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import com.chatapplication.chat.service.ChatAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("creating user " + user.getUserID());
        chatAppService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam Integer id) {
        logger.info("fetching user " + id);
        User user = chatAppService.fetchUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/message")
    public ResponseEntity<Message> getMessage(@RequestParam Integer id) {
        logger.info("fetching message " + id);
        Message message = chatAppService.fetchMessage(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        logger.info("index page");
        return new ResponseEntity<>("Welcome to Chat Application", HttpStatus.CREATED);
    }
}
