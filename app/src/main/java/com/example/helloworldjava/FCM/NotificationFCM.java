package com.example.helloworldjava.FCM;

public class NotificationFCM {
    private String title;
    private String content;
    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public NotificationFCM(String title, String content, String key) {
        this.title = title;
        this.content = content;
        this.key = key;
    }

    public NotificationFCM() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
