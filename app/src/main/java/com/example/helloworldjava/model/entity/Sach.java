package com.example.helloworldjava.model.entity;


import java.util.List;

public class Sach {
    private String id;
    private String img;
    private String TenSach;
    private String NhaXuatBan;
    private int NamXuatBan;
    private String Mota;
    private List<TheLoaiSach> ListTheLoai;
    private int tongSoLuotDoc;
    private String idNguoiDung;

    private List<String> ListTheLoaiId;

    public List<String> getListTheLoaiId() {
        return ListTheLoaiId;
    }

    public void setListTheLoaiId(List<String> listTheLoaiId) {
        ListTheLoaiId = listTheLoaiId;
    }

    public Sach() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        NhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        NamXuatBan = namXuatBan;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public List<TheLoaiSach> getListTheLoai() {
        return ListTheLoai;
    }

    public void setListTheLoai(List<TheLoaiSach> listTheLoai) {
        ListTheLoai = listTheLoai;
    }

    public Sach(String tenSach, String nxb, String img, String id){
        this.TenSach = tenSach;
        this.NhaXuatBan = nxb;
        this.img = img;
        this.id = id;
    }
    public int getTongSoLuotDoc() {
        return tongSoLuotDoc;
    }

    public void setTongSoLuotDoc(int tongSoLuotDoc) {
        this.tongSoLuotDoc = tongSoLuotDoc;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "ID=" + id +
                ", img='" + img + '\'' +
                ", TenSach='" + TenSach + '\'' +
                ", NhaXuatBan='" + NhaXuatBan + '\'' +
                ", NamXuatBan=" + NamXuatBan +
                ", Mota='" + Mota + '\'' +
                ", ListTheLoai='" + ListTheLoai + "'"+
                '}';
    }


}
