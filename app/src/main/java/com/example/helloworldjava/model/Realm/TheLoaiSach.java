package com.example.helloworldjava.model.Realm;



public class TheLoaiSach {

    private int ID;

    private String TenTheLoai;
    private String MoTaTheLoai;

    public TheLoaiSach() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
