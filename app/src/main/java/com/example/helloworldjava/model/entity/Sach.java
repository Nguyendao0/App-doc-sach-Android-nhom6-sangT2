package com.example.helloworldjava.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sach", foreignKeys = @ForeignKey(entity = TheLoaiSach.class,
        parentColumns = "idTheLoaiSach",
        childColumns = "idTheLoaiSach",
        onDelete = ForeignKey.CASCADE))
public class Sach {
    @PrimaryKey(autoGenerate = true)
    public int id_Sach;
    public int idTheLoaiSach; // Khóa ngoại đến bảng TheLoaiSach
    public String urlImage;
    public String TenSach;
    public String TacGia;
    public String NhaSanXuat;

    public String NamSanXuat;

    public String MoTa;

    public int DanhGiaSach;

    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public int getIdTheLoaiSach() {
        return idTheLoaiSach;
    }

    public void setIdTheLoaiSach(int idTheLoaiSach) {
        this.idTheLoaiSach = idTheLoaiSach;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getNhaSanXuat() {
        return NhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        NhaSanXuat = nhaSanXuat;
    }

    public String getNamSanXuat() {
        return NamSanXuat;
    }

    public void setNamSanXuat(String namSanXuat) {
        NamSanXuat = namSanXuat;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getDanhGiaSach() {
        return DanhGiaSach;
    }

    public void setDanhGiaSach(int danhGiaSach) {
        DanhGiaSach = danhGiaSach;
    }
}
