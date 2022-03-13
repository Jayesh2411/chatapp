package com.chatapplication.chat.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Message")
public class Message implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6562132181617666523L;
    private int messageID;
    private int senderID;
    private int receiverID;
    private String encryptedMessage;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
}
