package com.example.helloworldjava.view.SpeechBookTest;

import androidx.room.PrimaryKey;

public class SachContent {
    @PrimaryKey(autoGenerate = true)
    public int id_Sach;
    public String Content;
    public String Name_chapter;
    public int numberofpage;

    public int numberofchapter;

    public int getNumberofchapter() {
        return numberofchapter;
    }

    public void setNumberofchapter(int numberofchapter) {
        this.numberofchapter = numberofchapter;
    }

    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getName_chapter() {
        return Name_chapter;
    }

    public void setName_chapter(String name_chapter) {
        Name_chapter = name_chapter;
    }

    public int getNumberofpage() {
        return numberofpage;
    }

    public void setNumberofpage(int numberofpage) {
        this.numberofpage = numberofpage;
    }

    public SachContent(String content) {
        this.Content = content;
    }

    public SachContent(){}
}
