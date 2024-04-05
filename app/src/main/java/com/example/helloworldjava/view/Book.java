package com.example.helloworldjava.view;

public class Book {
    private String Decription;
    private String Name;
    private int Image;

    public Book(String decription, String name, int image) {
        Decription = decription;
        Name = name;
        Image = image;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String decription) {
        Decription = decription;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
