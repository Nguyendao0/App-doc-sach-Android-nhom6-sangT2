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
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        RemoteMessage.Notification notification = remoteMessage.getNotification();
//        if(notification == null)
//        {
//            return;
//        }
//        String strTitle = notification.getTitle();
//        String strMessage = notification.getBody();
//        System.out.println("dữ liệu:" + strTitle + strMessage);
//        sendNotification(strTitle, strMessage);
//    }
//
//    private void sendNotification(String strTitle,String strMessage)
//    {
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
//                .setContentTitle(strTitle)
//                .setContentText(strMessage)
//                .setSmallIcon(R.drawable.baseline_check_circle_24);
//    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification = message.getNotification();

        String strTitle = notification.getTitle();
        String strMessage = notification.getBody();
        System.out.println("dữ liệu:" + strTitle + strMessage);
        getFireaseMessage(strTitle, strMessage);
    }

    public void getFireaseMessage(String title, String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                MyApplication.CHANNEL_ID).setSmallIcon(R.drawable.baseline_check_circle_24)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        Notification notification = builder.build();
        notificationManager.notify(notificationId, notification);
    }
}
