package com.example.helloworldjava.model.Realm;


import io.realm.RealmObject;

public class DanhDauChuong extends RealmObject {
    private Sach sach;
    private Chuong chuong;
    private boolean DanhDau;
    public DanhDauChuong() {
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public Chuong getChuong() {
        return chuong;
    }

    public void setChuong(Chuong chuong) {
        this.chuong = chuong;
    }

    public boolean isDanhDau() {
        return DanhDau;
    }

    public void setDanhDau(boolean danhDau) {
        DanhDau = danhDau;
    }
}
