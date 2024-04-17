package com.example.helloworldjava.model.Realm;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NguoiDung  extends RealmObject {

    @PrimaryKey
    private int ID;
    private RealmList<ThuVienSachCaNhan> ThuVienSachCaNhan_Items;
    private RealmList<LichSuCaNhan> LichSuCaNhan_Items;
    private RealmList<DanhDauChuong> DanhDauChuong_Items;
    private String TenNguoiDung;
    private String Email;
    private String MatKhau;
    private String PhanCap;
    private String Avatar;

    public NguoiDung() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public RealmList<ThuVienSachCaNhan> getThuVienSachCaNhan_Items() {
        return ThuVienSachCaNhan_Items;
    }

    public void setThuVienSachCaNhan_Items(RealmList<ThuVienSachCaNhan> thuVienSachCaNhan_Items) {
        ThuVienSachCaNhan_Items = thuVienSachCaNhan_Items;
    }

    public RealmList<DanhDauChuong> getDanhDauChuong_Items() {
        return DanhDauChuong_Items;
    }

    public void setDanhDauChuong_Items(RealmList<DanhDauChuong> danhDauChuong_Items) {
        DanhDauChuong_Items = danhDauChuong_Items;
    }

    public RealmList<LichSuCaNhan> getLichSuCaNhan_Items() {
        return LichSuCaNhan_Items;
    }

    public void setLichSuCaNhan_Items(RealmList<LichSuCaNhan> lichSuCaNhan_Items) {
        LichSuCaNhan_Items = lichSuCaNhan_Items;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getPhanCap() {
        return PhanCap;
    }

    public void setPhanCap(String phanCap) {
        PhanCap = phanCap;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "ID=" + ID +
                ", ThuVienSachCaNhan_Items=" + ThuVienSachCaNhan_Items +
                ", LichSuCaNhan_Items=" + LichSuCaNhan_Items +
                ", DanhDauChuong_Items=" + DanhDauChuong_Items +
                ", TenNguoiDung='" + TenNguoiDung + '\'' +
                ", Email='" + Email + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", PhanCap='" + PhanCap + '\'' +
                ", Avatar='" + Avatar + '\'' +
                '}';
    }
}
