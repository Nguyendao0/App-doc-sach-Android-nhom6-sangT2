package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.List;

import io.realm.Realm;

public class ChuongDAO {
    private Realm realm;

    public ChuongDAO() {
        realm = Realm.getDefaultInstance();
    }



    public void add(Chuong chuong, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(chuong);
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

    public void delete(String IDChuong, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Chuong chuong = realm.where(Chuong.class).equalTo("id", IDChuong).findFirst();
                if (chuong != null) {
                    chuong.deleteFromRealm();
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

    public void update(Chuong chuong, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(chuong);
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

    public Chuong getById(String IDChuong) {
        return realm.where(Chuong.class).equalTo("id", IDChuong).findFirst();
    }

    public List<Chuong> getAll() {
        return realm.where(Chuong.class).findAll();
    }
}