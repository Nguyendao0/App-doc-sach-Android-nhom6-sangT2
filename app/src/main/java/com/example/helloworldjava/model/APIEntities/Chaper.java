package com.example.helloworldjava.model.APIEntities;

import java.io.Serializable;

public class Chaper  implements Serializable  {
    private String id;
    private String NoiDung;
    private String TenChuong;
    private String idSach;
    private int soThuTu;

    // Getters and setters (or use Lombok annotations for automatic generation)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getTenChuong() {
        return TenChuong;
    }

    public void setTenChuong(String TenChuong) {
        this.TenChuong = TenChuong;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }
}
