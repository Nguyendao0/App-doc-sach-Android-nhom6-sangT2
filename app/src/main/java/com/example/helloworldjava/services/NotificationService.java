package com.example.helloworldjava.services;

import com.example.helloworldjava.model.FCM.NotificationFCM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NotificationService {
    @POST("notification/createIDNotification/{id}")
    Call<String> createIDNotification(@Path("id") String id);

    @POST("notification/createNotificationById/{id}")
    Call<String> createNotificationById(@Path("id") String id, @Body NotificationFCM createNotificationDto);

    @GET("notification/findAllNotificationById/{id}")
    Call<List<NotificationFCM>> findAllNotificationById(@Path("id") String id);

    @DELETE("notification/removeNotificationById/{id}")
    Call<Void> removeNotificationById(
            @Path("id") String id,
            @Query("keys") String[] keys
    );

    @POST("notification/sendFCMToTopics")
    Call<Void> sendFCMToTopics(@Query("topics") String[] topics, @Body NotificationFCM createNotificationDto);

    @POST("notification/sendFCMToSingleTopic")
    Call<Void> sendFCMToSingleTopic(@Query("topic") String topic, @Body NotificationFCM createNotificationDto);

}


