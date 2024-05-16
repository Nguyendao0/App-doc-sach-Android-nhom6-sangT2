package com.example.helloworldjava.NotificationContractInterface;

import com.example.helloworldjava.FCM.NotificationFCM;

import java.util.ArrayList;

public interface Notification {
    interface View{
        void setListNotifications(ArrayList<NotificationFCM> list);
        void initData();
    }

    interface Presenter{
        void getListNotifications();
    }
}
