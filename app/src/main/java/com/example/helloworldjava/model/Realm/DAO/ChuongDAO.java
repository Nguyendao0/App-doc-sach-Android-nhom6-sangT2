package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.Chuong;

import java.util.List;

import io.realm.Realm;

public class ChuongDAO {
    private Realm realm;

    public ChuongDAO() {
        realm = Realm.getDefaultInstance();
    }

    private int getNextId(Realm realm) {
        Number maxId = realm.where(Chuong.class).max("ID");
        return maxId != null ? maxId.intValue() + 1 : 1;
    }

    public void add(Chuong chuong) {
        realm.executeTransaction(realm -> {
            chuong.setId(String.valueOf(getNextId(realm)));
            realm.copyToRealmOrUpdate(chuong);
        });
    }

    public void delete(int IDChuong) {
        realm.executeTransaction(realm -> {
            Chuong chuong = realm.where(Chuong.class).equalTo("ID", IDChuong).findFirst();
            if (chuong != null) {
                chuong.deleteFromRealm();
            }
        });
    }

    public void update(Chuong chuong) {
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(chuong));
    }

    public Chuong getById(int IDChuong) {
        return realm.where(Chuong.class).equalTo("ID", IDChuong).findFirst();
    }

    public List<Chuong> getAll() {
        return realm.where(Chuong.class).findAll();
    }
}
