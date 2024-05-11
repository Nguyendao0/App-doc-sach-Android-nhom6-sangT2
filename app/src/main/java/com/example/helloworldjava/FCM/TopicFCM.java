package com.example.helloworldjava.FCM;

import com.google.firebase.messaging.FirebaseMessaging;

public class TopicFCM {
    public TopicFCM() {
    }

    public void SubcribeTopic(String TenTopic)
    {
        FirebaseMessaging.getInstance().subscribeToTopic(TenTopic);
    }

    public void UnsubscibeTopic(String TenTopic)
    {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TenTopic);
    }
}
