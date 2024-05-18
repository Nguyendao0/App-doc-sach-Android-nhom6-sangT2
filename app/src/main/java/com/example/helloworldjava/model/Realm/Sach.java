package com.example.helloworldjava.model.Realm;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sach extends RealmObject {

    @PrimaryKey
    private String id;
    private RealmList<DanhGiaSach> DanhGiaSach_Items=null;
    private int luotDoc;
    private RealmList<Chuong> Chuong_Items=null;
    private String img;
    private String TenSach;
    private String NhaXuatBan;
    private int NamXuatBan;
    private String Mota;

    public Sach() {
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public RealmList<Chuong> getChuong_Items() {
        return Chuong_Items;
    }

    public void setChuong_Items(RealmList<Chuong> chuong_Items) {
        Chuong_Items = chuong_Items;
    }

    public int getLuotDoc() {
        return luotDoc;
    }

    public void setLuotDoc(int luotDoc) {
        this.luotDoc = luotDoc;
    }

    public RealmList<DanhGiaSach> getDanhGiaSach_Items() {
        return DanhGiaSach_Items;
    }

    public void setDanhGiaSach_Items(RealmList<DanhGiaSach> danhGiaSach_Items) {
        DanhGiaSach_Items = danhGiaSach_Items;
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

    @Override
    public String toString() {
        return "Sach{" +
                "id='" + id + '\'' +
                ", DanhGiaSach_Items=" + DanhGiaSach_Items +
                ", luotDoc=" + luotDoc +
                ", Chuong_Items=" + Chuong_Items +
                ", img='" + img + '\'' +
                ", TenSach='" + TenSach + '\'' +
                ", NhaXuatBan='" + NhaXuatBan + '\'' +
                ", NamXuatBan=" + NamXuatBan +
                ", Mota='" + Mota + '\'' +
                '}';
    }
}