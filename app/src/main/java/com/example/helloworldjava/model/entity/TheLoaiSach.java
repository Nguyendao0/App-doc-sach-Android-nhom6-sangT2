package com.example.helloworldjava.model.entity;

public class TheLoaiSach {
    public String id;

    public String TenTheLoai;
    public String MoTaTheLoai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public String getMoTaTheLoai() {
        return MoTaTheLoai;
    }

    public void setMoTaTheLoai(String moTaTheLoai) {
        MoTaTheLoai = moTaTheLoai;
    }

    @Override
    public String toString() {
        return "TheLoaiSach{" +
                "id='" + id + '\'' +
                ", TenTheLoai='" + TenTheLoai + '\'' +
                ", MoTaTheLoai='" + MoTaTheLoai + '\'' +
                '}';
    }
}
