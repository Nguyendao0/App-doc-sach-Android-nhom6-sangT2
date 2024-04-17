package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.NguoiDung;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.List;

import io.realm.Realm;

public class SachDAO {
    private Realm realm;

    public SachDAO() {
        realm = Realm.getDefaultInstance();
    }

    private int getNextId(Realm realm) {
        Number maxId = realm.where(Sach.class).max("ID");
        return maxId != null ? maxId.intValue() + 1 : 1;
    }

    public void add(Sach sach) {
        realm.executeTransaction(realm -> {
            sach.setID(getNextId(realm));
            realm.copyToRealmOrUpdate(sach);
        });
    }

    public void delete(int IDSach) {
        realm.executeTransaction(realm -> {
            Sach sach = realm.where(Sach.class).equalTo("ID", IDSach).findFirst();
            if (sach != null) {
                sach.deleteFromRealm();
            }
        });
    }

    public void update(Sach sach) {
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(sach));
    }

    public Sach getById(int IDSach) {
        return realm.where(Sach.class).equalTo("ID", IDSach).findFirst();
    }

    public List<Sach> getAll() {
        return realm.where(Sach.class).findAll();
    }
}
