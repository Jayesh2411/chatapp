package com.chatapplication.chat.repository;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;

public interface ChatAppRepository {
    void saveUser(User user);

    void saveMessage(Message message);

    boolean doesReceiverExist(int receiverID);
}
