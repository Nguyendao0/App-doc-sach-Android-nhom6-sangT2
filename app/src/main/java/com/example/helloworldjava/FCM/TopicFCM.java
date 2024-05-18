package com.example.helloworldjava.FCM;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class TopicFCM {
    public void subcribeTopic(String nameTopic)
    {
        FirebaseMessaging.getInstance().subscribeToTopic(nameTopic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("FCM", "Đăng ký topic thành công");
                        } else {
                            Log.d("FCM", "Đăng ký topic thất bại");
                        }
                    }
                });
    }

    public void unsubcribeTopic(String nameTopic)
    {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(nameTopic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("FCM", "Hủy đăng ký topic thành công");
                        } else {
                            Log.d("FCM", "Hủy đăng ký topic thất bại");
                        }
                    }
                });
    }
}
