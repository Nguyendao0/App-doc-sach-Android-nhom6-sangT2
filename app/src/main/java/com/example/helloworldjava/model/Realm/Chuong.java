package com.example.helloworldjava.model.Realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Chuong extends RealmObject {

    @PrimaryKey
    private int ID;
    private String NoiDung;
    private String TenChuong;

    public Chuong() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTenChuong() {
        return TenChuong;
    }

    public void setTenChuong(String tenChuong) {
        TenChuong = tenChuong;
    }

    @Override
    public String toString() {
        return "Chuong{" +
                "ID=" + ID +
                ", NoiDung='" + NoiDung + '\'' +
                ", TenChuong='" + TenChuong + '\'' +
                '}';
    }
}
