package com.example.helloworldjava.presenter.Thongbao;

import android.content.Context;

import com.example.helloworldjava.presenter.NotificationContractInterface.Notification;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.model.FCM.NotificationFCM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFCMPresenter implements Notification.Presenter {

    private Notification.View view ;
    private FirebaseAuthManager firebaseAuthManager;
    public NotificationFCMPresenter(Notification.View view) {
        this.view = view;
    }


    @Override
    public void getListNotifications(Context context) {
        firebaseAuthManager = new FirebaseAuthManager(context);
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();

        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<List<NotificationFCM>> request = notificationService.findAllNotificationById(idNguoiDung);
        request.enqueue(new Callback<List<NotificationFCM>>() {
            @Override
            public void onResponse(Call<List<NotificationFCM>> call, Response<List<NotificationFCM>> response) {
                if (response.isSuccessful()) {
                    ArrayList<NotificationFCM> notis = (ArrayList<NotificationFCM>) response.body();
                    ArrayList<NotificationFCM> list = new ArrayList<>();
                    for(NotificationFCM n : notis)
                    {
                        list.add(n);
                    }

                view.setListNotifications(list);

                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NotificationFCM>> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }
}
