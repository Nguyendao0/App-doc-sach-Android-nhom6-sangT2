package com.example.helloworldjava.model.Realm;



public class NguoiDung  {

    private int ID;

    private String TenNguoiDung;
    private String Email;
    private String MatKhau;
    private String PhanCap;
    private String Avatar;

    public NguoiDung() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
}
