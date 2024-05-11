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

    public Sach getById(int IDSach) {
        return realm.where(Sach.class).equalTo("ID", IDSach).findFirst();
    }

    public List<Sach> getAll() {
        return realm.where(Sach.class).findAll();
    }


    public void add(Sach sach, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                sach.setID(getNextId(realm));
                realm.copyToRealmOrUpdate(sach);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onFail(error);
            }
        });
    }

    public void delete(int IDSach, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Sach sach = realm.where(Sach.class).equalTo("ID", IDSach).findFirst();
                if (sach != null) {
                    sach.deleteFromRealm();
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onFail(error);
            }
        });
    }

    public void update(Sach sach, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(sach);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onFail(error);
            }
        });
    }
}