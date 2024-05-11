package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.NguoiDung;
import com.example.helloworldjava.model.Realm.Sach;
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

    public void add(TheLoaiSach theLoaiSach, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                theLoaiSach.setID(getNextId(realm));
                realm.copyToRealmOrUpdate(theLoaiSach);
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

    public void delete(int IDTheLoaiSach, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TheLoaiSach theLoaiSach = realm.where(TheLoaiSach.class).equalTo("ID", IDTheLoaiSach).findFirst();
                if (theLoaiSach != null) {
                    theLoaiSach.deleteFromRealm();
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

    public void update(TheLoaiSach theLoaiSach, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(theLoaiSach);
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

    public TheLoaiSach getById(int IDTheLoaiSach) {
        return realm.where(TheLoaiSach.class).equalTo("ID", IDTheLoaiSach).findFirst();
    }

    public List<TheLoaiSach> getAll() {
        return realm.where(TheLoaiSach.class).findAll();
    }
}