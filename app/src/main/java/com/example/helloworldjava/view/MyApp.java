package com.example.helloworldjava.view;

import android.app.Application;

import androidx.room.Room;

import com.example.helloworldjava.model.MyAppDatabase;

public class MyApp extends Application {
    private static MyAppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                MyAppDatabase.class, "DataAppDocSach").build();
    }

    public static MyAppDatabase getDatabase() {
        return database;
    }
}
