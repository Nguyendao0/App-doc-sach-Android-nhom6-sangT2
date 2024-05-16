package com.example.helloworldjava.FCM;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.helloworldjava.MainActivity;
import com.example.helloworldjava.MyApplication;
import com.example.helloworldjava.R;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFireBaseMessagingService extends FirebaseMessagingService {


    public static final String ACTION_FCM_NOTIFICATION = "com.example.app.ACTION_FCM_NOTIFICATION";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);



        String title;
        String content;

        Map<String, String> data = remoteMessage.getData();
        if (data != null) {
            title = data.get("title");
            content = data.get("content");
        } else {
            return;
        }



        createNotification(title, content);
        sendNoti(title, content);
    }

    private void createNotification(String title, String content)
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        NotificationFCM notificationFCM = new NotificationFCM();
        notificationFCM.setContent(content);
        notificationFCM.setTitle(title);

        Call<String> request = notificationService.createNotificationById("zed", notificationFCM);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Intent broadcastIntent = new Intent(ACTION_FCM_NOTIFICATION);
                    sendBroadcast(broadcastIntent);

                    String thongbao = response.body();
                    System.out.println("response: " + thongbao);
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }

    private void sendNoti(String title, String content)
    {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = notificationBuilder.build();
        notificationManager.notify(String.valueOf(1), 0, notification);
    }
}
