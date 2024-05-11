package com.example.helloworldjava.APIModel;

import java.util.List;

public class Sach {
    private String id;
    private String img;
    private String TenSach;
    private String NhaXuatBan;
    private int NamXuatBan;
    private String Mota;
    private List<TheLoai> ListTheLoai;
    private int tongSoLuotDoc;

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
        this.TenSach = tenSach;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.NhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.NamXuatBan = namXuatBan;
    }

    public String getMoTa() {
        return Mota;
    }

    public void setMoTa(String moTa) {
        this.Mota = moTa;
    }

    public List<TheLoai> getListTheLoai() {
        return ListTheLoai;
    }

    public void setListTheLoai(List<TheLoai> listTheLoai) {
        this.ListTheLoai = listTheLoai;
    }

    public int getTongSoLuotDoc() {
        return tongSoLuotDoc;
    }

    public void setTongSoLuotDoc(int tongSoLuotDoc) {
        this.tongSoLuotDoc = tongSoLuotDoc;
    }
}

