package com.example.helloworldjava.services.API;
import com.example.helloworldjava.model.Config.ConfigData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(ConfigData.Domain).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()).build();
    }
    public static AccountService getService() {
        return (AccountService) getRetrofit().create(AccountService.class);
    }
}
