package com.example.helloworldjava.services;

import com.example.helloworldjava.view.Thongbao.NotificationFCM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationService {
    @POST("notification/createIDNotification/{id}")
    Call<String> createIDNotification(@Path("id") String id);


    @POST("notification/createNotificationById/{id}")
    Call<String> createNotificationById(@Path("id") String id, @Body NotificationFCM createNotificationDto);

    @GET("notification/findAllNotificationById/{id}")
    Call<List<NotificationFCM>> findAllNotificationById(@Path("id") String id);



}
