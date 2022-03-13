package com.chatapplication.chat.repository;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import org.springframework.stereotype.Component;

@Component
public class ChatAppRepositoryImpl implements ChatAppRepository {
    @Override
    public void saveUser(User user) {

    }

    @Override
    public void saveMessage(Message message) {

    }

    @Override
    public boolean doesReceiverExist(int receiverID) {
        return true;
    }
}
