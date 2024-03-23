package com.example.helloworldjava.model.entity;

public class User {
    private String username;
    private String email;
    private String password;
    private String avatarImage;

    public User() {}

    public User(String username, String email, String password, String avatarImage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatarImage = avatarImage;
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

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
