package com.example.helloworldjava.presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.helloworldjava.model.NguoiDungModel;
import com.example.helloworldjava.model.entity.NguoiDung;

public class LoginPresenter {
    public static void saveNguoiDungToSharedPref(Context context, NguoiDung nguoiDung) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NguoiDungModel.COLLECTION_PATH, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("init", true);
        editor.putString("id", nguoiDung.getID());
        editor.putString("email", nguoiDung.getEmail());
        editor.putString("tenNguoiDung", nguoiDung.getTenNguoiDung());
        editor.apply();
    }

    public static NguoiDung getNguoiDungFromSharedPref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NguoiDungModel.COLLECTION_PATH, MODE_PRIVATE);
        NguoiDung nguoiDung = null;
        if (sharedPreferences.contains("init")) {
            nguoiDung = new NguoiDung();
            nguoiDung.setID(sharedPreferences.getString("id", ""));
            nguoiDung.setEmail(sharedPreferences.getString("email", ""));
            nguoiDung.setTenNguoiDung(sharedPreferences.getString("tenNguoiDung", ""));
        }

        return nguoiDung;
    }

    public static void logoutNguoiDungFromSharedFref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NguoiDungModel.COLLECTION_PATH, MODE_PRIVATE);
        if (sharedPreferences.contains("init")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }
}
