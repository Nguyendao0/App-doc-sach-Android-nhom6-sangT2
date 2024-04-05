package com.example.helloworldjava.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TheLoaiSach")
public class TheLoaiSach {
    @PrimaryKey(autoGenerate = true)
    public int idTheLoaiSach;

    public String TenTheLoai;
    public String MoTaTheLoai;

    public int getIdTheLoaiSach() {
        return idTheLoaiSach;
    }

    public void setIdTheLoaiSach(int idTheLoaiSach) {
        this.idTheLoaiSach = idTheLoaiSach;
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
