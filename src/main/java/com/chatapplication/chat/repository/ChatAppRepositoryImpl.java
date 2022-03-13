package com.chatapplication.chat.repository;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ChatAppRepositoryImpl implements ChatAppRepository {
    Logger logger = LoggerFactory.getLogger(ChatAppRepositoryImpl.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public void saveUser(User user) {
        try {
            Map userHash = new ObjectMapper().convertValue(user, Map.class);
            redisTemplate.opsForHash().put("user", String.valueOf(user.getUserID()), userHash);
        } catch (Exception e) {
            logger.error("Error saving user to Redis-> " + e.getMessage());
        }
    }

    @Override
    public void saveMessage(Message message) {
        try {
            Map messageHash = new ObjectMapper().convertValue(message, Map.class);
            redisTemplate.opsForHash().put("message", String.valueOf(message.getMessageID()), messageHash);
        } catch (Exception e) {
            logger.error("Error saving message to Redis-> " + e.getMessage());
        }
    }

    @Override
    public User fetchUser(Integer id) {
        User user = null;
        try {
            LinkedHashMap userMap = (LinkedHashMap) redisTemplate.opsForHash().get("user", String.valueOf(id));
            user = new ObjectMapper().convertValue(userMap, User.class);
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
            LinkedHashMap messageMap = (LinkedHashMap) redisTemplate.opsForHash().get("message", String.valueOf(id));
            message = new ObjectMapper().convertValue(messageMap, Message.class);
        } catch (Exception e) {
            logger.error("Message not found");
        }
        return message;
    }


    @Override
    public boolean doesReceiverExist(int receiverID) {
        User user = fetchUser(receiverID);
        if (user != null) {
            return true;
        }
        return false;
    }
}
