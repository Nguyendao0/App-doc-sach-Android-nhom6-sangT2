package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.Realm.NguoiDung;

import java.util.List;

import io.realm.Realm;

public class NguoiDungDAO {
    private Realm realm;

    public NguoiDungDAO() {
        realm = Realm.getDefaultInstance();
    }

    private int getNextId(Realm realm) {
        Number maxId = realm.where(NguoiDung.class).max("ID");
        return maxId != null ? maxId.intValue() + 1 : 1;
    }

    public void add(NguoiDung nguoiDung) {
        realm.executeTransaction(realm -> {
            nguoiDung.setID(getNextId(realm));
            realm.copyToRealmOrUpdate(nguoiDung);
        });
    }

    public void delete(int IDNguoiDung) {
        realm.executeTransaction(realm -> {
            NguoiDung nguoiDung = realm.where(NguoiDung.class).equalTo("ID", IDNguoiDung).findFirst();
            if (nguoiDung != null) {
                nguoiDung.deleteFromRealm();
            }
        });
    }

    public void update(NguoiDung nguoiDung) {
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(nguoiDung));
    }

    public NguoiDung getById(int IDNguoiDung) {
        return realm.where(NguoiDung.class).equalTo("ID", IDNguoiDung).findFirst();
    }

    public List<NguoiDung> getAll() {
        return realm.where(NguoiDung.class).findAll();
    }
}
