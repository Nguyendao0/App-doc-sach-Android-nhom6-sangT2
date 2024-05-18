package com.example.helloworldjava;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    public static final String CHANNEL_ID = "push_notification_id";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannelNotification();
        createRealm();
    }

    private void createChannelNotification()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "PushNotification",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private void createRealm()
    {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("test.db")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
