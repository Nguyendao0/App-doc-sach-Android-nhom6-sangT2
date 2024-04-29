package com.example.helloworldjava.model.Realm;


import io.realm.RealmObject;

public class LuotDoc extends RealmObject {
    private NguoiDung nguoidung;

    public LuotDoc() {
    }

    public NguoiDung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }
}
