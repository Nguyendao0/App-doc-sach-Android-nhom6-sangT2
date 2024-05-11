package com.example.helloworldjava.FCM;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.MainActivity;
import com.example.helloworldjava.MyApplication;
import com.example.helloworldjava.R;
import com.example.helloworldjava.Services.ServiceBuilder;
import com.example.helloworldjava.Services.Token;
import com.example.helloworldjava.Services.TokenService;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String newToken) {
        super.onNewToken(newToken);
        TokenService tokenService = ServiceBuilder.buildService(TokenService.class);
        Call<String> request = tokenService.createIDToken("LTN");

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("LỖI" + throwable.getMessage());
            }
        });
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title;
        String content;
        String strIcon;
        String strID;

        Map<String, String> data = remoteMessage.getData();
        if (data != null) {
            title = data.get("title");
            content = data.get("content");
//            strIcon = data.get("icon");
//            strID = data.get("idNotification");
        } else {
            return;
        }

         sendNoti(title, content);
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
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setSmallIcon(R.drawable.baseline_check_circle_24)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = notificationBuilder.build();
        notificationManager.notify(String.valueOf(1), 0, notification);
    }


    private void sendNotification(String strTitle, String strMessage, String notificationId, String iconUrl) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Tạo builder cho thông báo
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strMessage)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setSmallIcon(R.drawable.baseline_check_circle_24)
                .setAutoCancel(true);

        // Tải hình ảnh từ URL và đặt làm icon cho thông báo
        try {
            Bitmap iconBitmap = Glide.with(this)
                    .asBitmap()
                    .load(iconUrl)
                    .submit()
                    .get();

            // Đặt hình ảnh tải được làm icon cho thông báo
            notificationBuilder.setLargeIcon(iconBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = notificationBuilder.build();
        notificationManager.notify(notificationId, 0, notification);
    }
}
