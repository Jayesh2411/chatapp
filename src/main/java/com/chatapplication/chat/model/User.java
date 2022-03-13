package com.chatapplication.chat.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
public class User {
    private int userID;
    private String userName;
    private byte[] profilePicture;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
