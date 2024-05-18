package com.example.helloworldjava.model.APIEntities;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private String id;
    private String img;
    private String TenSach;
    private String NhaXuatBan;
    private int NamXuatBan;
    private String Mota;
    private List<BookType> ListTheLoai;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        NhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        NamXuatBan = namXuatBan;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public List<BookType> getListTheLoai() {
        return ListTheLoai;
    }
    public void setListTheLoai(List<BookType> listTheLoai) {
        ListTheLoai = listTheLoai;
    }
}

