package com.example.helloworldjava.APIEntities;


public class BookType {
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
}