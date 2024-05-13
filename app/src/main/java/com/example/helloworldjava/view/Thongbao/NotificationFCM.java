package com.example.helloworldjava.view.Thongbao;

public class NotificationFCM {
    private String title;
    private String content;
    private String icon;

    public NotificationFCM(String title, String content, String icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "NotificationFCM{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
