package com.example.helloworldjava.model.Realm;

import java.util.Date;



public class LichSuCaNhan  {
    private Sach sach;
    private Date ThoiGianTruyCapCuoi;
    private int SoChuongDaDoc;

    public LichSuCaNhan() {
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public Date getThoiGianTruyCapCuoi() {
        return ThoiGianTruyCapCuoi;
    }

    public void setThoiGianTruyCapCuoi(Date thoiGianTruyCapCuoi) {
        ThoiGianTruyCapCuoi = thoiGianTruyCapCuoi;
    }

    public int getSoChuongDaDoc() {
        return SoChuongDaDoc;
    }

    public void setSoChuongDaDoc(int soChuongDaDoc) {
        SoChuongDaDoc = soChuongDaDoc;
    }
}
