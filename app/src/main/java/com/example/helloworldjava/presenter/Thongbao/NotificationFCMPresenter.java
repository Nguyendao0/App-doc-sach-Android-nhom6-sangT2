package com.example.helloworldjava.presenter.Thongbao;

import com.example.helloworldjava.Fragment.NotificationFragment;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.Thongbao.NotificationFCM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFCMPresenter {

    private NotificationFragment view ;

    public NotificationFCMPresenter(NotificationFragment view) {
        this.view = view;
    }

    public void getListSach()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<List<NotificationFCM>> request = notificationService.findAllNotificationById("zed");
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
