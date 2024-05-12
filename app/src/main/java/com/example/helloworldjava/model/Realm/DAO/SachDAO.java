package com.example.helloworldjava.model.Realm.DAO;

import com.example.helloworldjava.model.Realm.Sach;
import io.realm.RealmResults;
import java.util.List;

import io.realm.Realm;

public class SachDAO {
    private Realm realm;

    public SachDAO() {
        realm = Realm.getDefaultInstance();
    }


    public Sach getById(String IDSach) {

        return realm.where(Sach.class).equalTo("id", IDSach).findFirst();
    }

    public List<Sach> getAll() {
        return realm.where(Sach.class).findAll();
    }


    public void add(Sach sach, Interface_Success_Fail callback) {
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

    public void addBooks(List<Sach> sachList, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Sach sach : sachList) {
                    realm.copyToRealmOrUpdate(sach);
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
    public void delete(String IDSach, Interface_Success_Fail callback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Sach sach = realm.where(Sach.class).equalTo("id", IDSach).findFirst();
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

    public void deleteBooks(List<String> sachIDs, Interface_Success_Fail callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Sach> sachResults = realm.where(Sach.class).in("id", sachIDs.toArray(new String[0])).findAll();
                sachResults.deleteAllFromRealm();
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