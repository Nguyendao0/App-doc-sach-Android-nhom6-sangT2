package com.example.helloworldjava.model.entity;

import com.example.helloworldjava.Services.Notification;

import java.util.List;

public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private String avatar;


    public User() {}

    public User(String username, String email, String password, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {

        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatarImage='" + avatar + '\'' +
                '}';
    }
}
