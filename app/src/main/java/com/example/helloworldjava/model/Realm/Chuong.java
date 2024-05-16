package com.example.helloworldjava.model.Realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Chuong extends RealmObject {

    @PrimaryKey
    private String id;
    private String NoiDung;
    private String TenChuong;
    private int soThuTu;

    public Chuong() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    @Override
    public String toString() {
        return "Chuong{" +
                "id='" + id + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", TenChuong='" + TenChuong + '\'' +
                ", soThuTu=" + soThuTu +
                '}';
    }
}
