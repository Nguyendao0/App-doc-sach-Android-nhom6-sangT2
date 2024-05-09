package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.NguoiDung;
import com.example.helloworldjava.model.Realm.TheLoaiSach;

import java.util.List;

import io.realm.Realm;

public class TheLoaiSachDAO {
    private Realm realm;

    public TheLoaiSachDAO() {
        realm = Realm.getDefaultInstance();
    }

    private int getNextId(Realm realm) {
        Number maxId = realm.where(TheLoaiSach.class).max("ID");
        return maxId != null ? maxId.intValue() + 1 : 1;
    }

    public void add(TheLoaiSach theLoaiSach) {
        realm.executeTransaction(realm -> {
            theLoaiSach.setID(getNextId(realm));
            realm.copyToRealmOrUpdate(theLoaiSach);
        });
    }

    public void delete(int IDTheLoaiSach) {
        realm.executeTransaction(realm -> {
            TheLoaiSach theLoaiSach = realm.where(TheLoaiSach.class).equalTo("ID", IDTheLoaiSach).findFirst();
            if (theLoaiSach != null) {
                theLoaiSach.deleteFromRealm();
            }
        });
    }

    public void update(TheLoaiSach theLoaiSach) {
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(theLoaiSach));
    }

    public TheLoaiSach getById(int IDTheLoaiSach) {
        return realm.where(TheLoaiSach.class).equalTo("ID", IDTheLoaiSach).findFirst();
    }

    public List<TheLoaiSach> getAll() {
        return realm.where(TheLoaiSach.class).findAll();
    }
}
