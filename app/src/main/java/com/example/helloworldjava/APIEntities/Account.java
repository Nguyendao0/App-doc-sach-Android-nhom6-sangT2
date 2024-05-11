package com.example.helloworldjava.APIEntities;

import java.io.Serializable;

public class Account  implements Serializable {

    private String ID;
    private String TenNguoiDung;
    private String Email;
    private String MatKhau;
    private String PhanCap;
    private String Avatar;
    private String googleId;
    public Account() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getPhanCap() {
        return PhanCap;
    }

    public void setPhanCap(String phanCap) {
        PhanCap = phanCap;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
