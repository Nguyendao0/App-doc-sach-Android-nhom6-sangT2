package com.example.helloworldjava.model.Realm;



public class DanhGiaSach{
    private NguoiDung nguoidung;
    private int SoSaoDanhGia;
    private String MoTaDanhGia;

    public DanhGiaSach() {
    }

    public NguoiDung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }

    public int getSoSaoDanhGia() {
        return SoSaoDanhGia;
    }

    public void setSoSaoDanhGia(int soSaoDanhGia) {
        SoSaoDanhGia = soSaoDanhGia;
    }

    public String getMoTaDanhGia() {
        return MoTaDanhGia;
    }

    public void setMoTaDanhGia(String moTaDanhGia) {
        MoTaDanhGia = moTaDanhGia;
    }
}
