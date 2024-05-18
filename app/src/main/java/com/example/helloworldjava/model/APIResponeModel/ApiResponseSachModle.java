package com.example.helloworldjava.model.APIResponeModel;

import com.example.helloworldjava.model.APIModel.NgayThem;
import com.example.helloworldjava.model.APIModel.Sach;

public class ApiResponseSachModle {
    private String id;
    private String idNguoiDung;
    private Sach sach;
    private NgayThem ngayThem;
    private boolean sachYeuThich;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public NgayThem getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(NgayThem ngayThem) {
        this.ngayThem = ngayThem;
    }

    public boolean isSachYeuThich() {
        return sachYeuThich;
    }

    public void setSachYeuThich(boolean sachYeuThich) {
        this.sachYeuThich = sachYeuThich;
    }
}

