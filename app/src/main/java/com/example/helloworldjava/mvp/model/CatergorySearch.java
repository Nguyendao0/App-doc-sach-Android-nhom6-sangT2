package com.example.helloworldjava.mvp.model;

import java.util.ArrayList;

public class CatergorySearch {
    ArrayList<String> arrayCatergory ;

    public ArrayList<String> getArrayCatergory() {
        return arrayCatergory;
    }

    public void setArrayCatergory(ArrayList<String> arrayCatergory) {
        this.arrayCatergory = arrayCatergory;
    }

    public CatergorySearch() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Phiêu Lưu");
        arrayList.add("Tính Cảm");
        arrayList.add("Cuộc Sống");
        arrayList.add("Lập Trình");
        arrayList.add("Chứng Khoán");
        this.arrayCatergory = arrayList;
    }




}