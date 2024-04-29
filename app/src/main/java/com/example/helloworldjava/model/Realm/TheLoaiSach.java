package com.example.helloworldjava.model.Realm;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TheLoaiSach extends RealmObject {

    @PrimaryKey
    private int ID;
    private RealmList<Sach> Sach_Items;
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


    public RealmList<Sach> getSach_Items() {
        return Sach_Items;
    }

    public void setSach_Items(RealmList<Sach> sach_Items) {
        Sach_Items = sach_Items;
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
