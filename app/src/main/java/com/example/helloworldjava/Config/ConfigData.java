package com.example.helloworldjava.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigData {

    /* renamed from: IP */
    public static String IP = "domain";
    public static String Token = "firebasetoken";
    public static String Domain = "https://backend-app-doc-sach-android-nhom6-sangt2.onrender.com/";
    public static final String Mail = "mailKey";
    public static final String profilePreferences = "profilepref";

    public String userId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(profilePreferences, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        return sharedPreferences.getString(Token, (String) null);
    }
}