package com.example.helloworldjava.presenter.NotificationContractInterface;

import android.content.Context;

import com.example.helloworldjava.model.FCM.NotificationFCM;

import java.util.ArrayList;

public interface Notification {
    interface View{
        void setListNotifications(ArrayList<NotificationFCM> list);
        void initData();
    }

    interface Presenter{
        void getListNotifications(Context context);
    }
}
