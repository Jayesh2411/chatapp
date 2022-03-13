package com.chatapplication.chat.repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.chatapplication.chat.model.Message;
import com.chatapplication.chat.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatAppRepositoryImpl implements ChatAppRepository {
	Logger logger = LoggerFactory.getLogger(ChatAppRepositoryImpl.class);
	
	@Autowired
    RedisTemplate<Object,Object> redisTemplate;

	
    @Override
    public void saveUser(User user) {
    	try {
            Map userHash = new ObjectMapper().convertValue(user, Map.class);
            redisTemplate.opsForHash().put("user", String.valueOf(user.getUserID()), userHash);
        } catch (Exception e) {
        	logger.error("Error saving user to Redis-> "+e.getMessage());
        }
    }

    @Override
    public void saveMessage(Message message) {
    	try {
            redisTemplate.opsForHash().put("message",String.valueOf(message.getMessageID()), message);
        } catch (Exception e) {
        	logger.error("Error saving message to Redis-> "+e.getMessage());
        }
    }

    @Override
    public boolean doesReceiverExist(int receiverID) {
        return true;
    }
}
