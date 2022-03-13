package com.chatapplication.chat.repository;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatAppRepositoryImpl implements ChatAppRepository {
    Logger logger = LoggerFactory.getLogger(ChatAppRepositoryImpl.class);

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    @Override
    public void saveUser(User user) {
        try {
            redisTemplate.opsForHash().put("user", String.valueOf(user.getUserID()), user);
        } catch (Exception e) {
            logger.error("Error saving user to Redis-> " + e.getMessage());
        }
    }

    @Override
    public void saveMessage(Message message) {
        try {
            redisTemplate.opsForHash().put("message", String.valueOf(message.getMessageID()), message);
        } catch (Exception e) {
            logger.error("Error saving message to Redis-> " + e.getMessage());
        }
    }

    @Override
    public User fetchUser(Integer id) {
        User user = null;
        try {
            user = (User) redisTemplate.opsForHash().get("user", String.valueOf(id));
            logger.info("Got the user ", user.getUserID());
        } catch (Exception e) {
            logger.error("User not found", e);
        }
        return user;
    }

    @Override
    public Message fetchMessage(Integer id) {
        Message message = null;
        try {
            message = (Message) redisTemplate.opsForHash().get("message", String.valueOf(id));
        } catch (Exception e) {
            logger.error("Message not found", e);
        }
        return message;
    }


    @Override
    public boolean doesReceiverExist(int receiverID) {
        User user = fetchUser(receiverID);
        return user != null;
    }
}
