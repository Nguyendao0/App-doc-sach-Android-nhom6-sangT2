package com.example.helloworldjava.model.Realm;

import java.util.Date;


public class ThuVienSachCaNhan  {
private Sach sach;
private Date NgayThem;
private Boolean SachYeuThich;

    public ThuVienSachCaNhan() {
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public Date getNgayThem() {
        return NgayThem;
    }

    public void setNgayThem(Date ngayThem) {
        NgayThem = ngayThem;
    }

    public Boolean getSachYeuThich() {
        return SachYeuThich;
    }

    public void setSachYeuThich(Boolean sachYeuThich) {
        SachYeuThich = sachYeuThich;
    }
}
