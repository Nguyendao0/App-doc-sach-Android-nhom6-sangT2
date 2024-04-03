package com.example.helloworldjava.Library;

public class Book {
    private Long ID;
    private String Image;
    private String Source;
    private String Title;
    private String DowloadedPDFFilePath;
    private String DowloadedImageFilePath;

    public Book(Long ID, String image, String source, String title) {
        this.ID = ID;
        this.Image = image;
        this.Source = source;
        this.Title = title;
    }

    public Long getID() {
        return ID;
    }

    public String getImage() {
        return Image;
    }

    public String getSource() {
        return Source;
    }

    public String getTitle() {
        return Title;
    }

    public void setDowloadedPDFFilePath(String dowloadedPDFFilePath) {
        this.DowloadedPDFFilePath = dowloadedPDFFilePath;
    }

    public String getDowloadedPDFFilePath() {
        return DowloadedPDFFilePath;
    }

    public String getDowloadedImageFilePath() {
        return DowloadedImageFilePath;
    }

    public void setDowloadedImageFilePath(String dowloadedImageFilePath) {
        DowloadedImageFilePath = dowloadedImageFilePath;
    }
}
