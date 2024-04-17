package com.example.helloworldjava.model.Realm;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class MyApplication extends Application  {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("test.db")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }


}
