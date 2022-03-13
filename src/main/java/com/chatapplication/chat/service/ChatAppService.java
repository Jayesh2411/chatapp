package com.chatapplication.chat.service;

import com.chatapplication.chat.exceptions.UserNotFoundException;
import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import com.chatapplication.chat.repository.ChatAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatAppService {
    @Autowired
    ChatAppRepository chatAppRepository;

    public void send(Message message) throws UserNotFoundException {
        if (!chatAppRepository.doesReceiverExist(message.getReceiverID())) {
            throw new UserNotFoundException("receiver not found!! ");
        }
        chatAppRepository.saveMessage(message);
    }

    public void saveUser(User user) {
        chatAppRepository.saveUser(user);
    }

    public User fetchUser(Integer id) {
        return chatAppRepository.fetchUser(id);
    }

    public Message fetchMessage(Integer id) {
        return chatAppRepository.fetchMessage(id);
    }
}
