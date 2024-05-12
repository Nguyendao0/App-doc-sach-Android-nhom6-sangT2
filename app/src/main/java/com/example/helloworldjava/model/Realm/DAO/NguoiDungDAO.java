package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.Realm.NguoiDung;
import com.example.helloworldjava.model.Realm.Sach;

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

    public void add(NguoiDung nguoiDung, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                nguoiDung.setID(getNextId(realm));
                realm.copyToRealmOrUpdate(nguoiDung);
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

    public void delete(int IDNguoiDung, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NguoiDung nguoiDung = realm.where(NguoiDung.class).equalTo("ID", IDNguoiDung).findFirst();
                if (nguoiDung != null) {
                    nguoiDung.deleteFromRealm();
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

    public void update(NguoiDung nguoiDung, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(nguoiDung);
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

    public NguoiDung getById(int IDNguoiDung) {
        return realm.where(NguoiDung.class).equalTo("ID", IDNguoiDung).findFirst();
    }

    public List<NguoiDung> getAll() {
        return realm.where(NguoiDung.class).findAll();
    }
}