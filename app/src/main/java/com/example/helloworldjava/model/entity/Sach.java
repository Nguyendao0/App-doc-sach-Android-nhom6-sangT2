package com.example.helloworldjava.model.entity;


import java.util.List;

public class Sach {
    private String id;
    private String img;
    private String TenSach;
    private String NhaXuatBan;
    private int NamXuatBan;
    private String Mota;
    private List<TheLoaiSach> ListTheLoai;

    public Sach() {
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

    public List<TheLoaiSach> getListTheLoai() {
        return ListTheLoai;
    }

    public void setListTheLoai(List<TheLoaiSach> listTheLoai) {
        ListTheLoai = listTheLoai;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "ID=" + id +
                ", img='" + img + '\'' +
                ", TenSach='" + TenSach + '\'' +
                ", NhaXuatBan='" + NhaXuatBan + '\'' +
                ", NamXuatBan=" + NamXuatBan +
                ", Mota='" + Mota + '\'' +
                ", ListTheLoai='" + ListTheLoai + "'"+
                '}';
    }

    public Sach() {
    }

    public Sach(String moTa, String tenSach, String urlImage) {
        this.urlImage = urlImage;
        this.TenSach = tenSach;
        this.MoTa = moTa;
    }
}
