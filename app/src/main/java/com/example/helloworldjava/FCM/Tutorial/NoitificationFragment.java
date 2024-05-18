package com.example.helloworldjava.FCM.Tutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.FCM.TopicFCM;
import com.example.helloworldjava.NotificationContractInterface.Notification;
import com.example.helloworldjava.R;
import com.example.helloworldjava.presenter.Thongbao.NotificationFCMPresenter;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoitificationFragment extends Fragment implements Notification.View {


    private NotificationFCMPresenter presenter;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private EditText sub;
    private EditText un;

    private TextView send;
    private TextView subcribe;
    private TextView unsubcribe;

    private EditText subt;
    private EditText unt;

    private EditText sendTopic;
    private static final String ACTION_FCM_NOTIFICATION = "com.example.app.ACTION_FCM_NOTIFICATION";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notitest_fragment, container, false);


        sub = view.findViewById(R.id.subname);
        un = view.findViewById(R.id.unname);
        subt = view.findViewById(R.id.tit);
        unt = view.findViewById(R.id.cont);

        subcribe = view.findViewById(R.id.sub);
        unsubcribe = view.findViewById(R.id.Un);
        send = view.findViewById(R.id.txSend);
        sendTopic = view.findViewById(R.id.sendtopic);

        recyclerView = view.findViewById(R.id.RVNotificationt);
        adapter = new NotificationAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = subt.getText().toString();
                String content = unt.getText().toString();
                String topic = sendTopic.getText().toString();
                Toast.makeText(getContext(), "Send", Toast.LENGTH_SHORT).show();
                sendFCMToSingleTopic( title, content, topic);
            }
        });


        unsubcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "UNSubcribe:"+un.getText().toString(), Toast.LENGTH_SHORT).show();
                TopicFCM topicFCM = new TopicFCM();
                topicFCM.unsubcribeTopic(un.getText().toString());
            }
        });

        subcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Subcribe:"+sub.getText().toString(), Toast.LENGTH_SHORT).show();
                TopicFCM topicFCM = new TopicFCM();
                topicFCM.subcribeTopic(sub.getText().toString());
            }
        });
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                NotificationFCM notification = adapter.getListNotification().get(position);
                ArrayList<NotificationFCM> list = new ArrayList<>();
                list.add(notification);
                ClearNotifications(list);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        presenter = new NotificationFCMPresenter(this);
        presenter.getListNotifications(getContext());

        return view;
    }

    private BroadcastReceiver broadcastReceiver  = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(ACTION_FCM_NOTIFICATION.equals(intent.getAction())){
                presenter.getListNotifications(getContext());
            }
        }
    };


    private void sendFCMToSingleTopic(String title, String content, String topic)
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        NotificationFCM notificationFCM = new NotificationFCM();
        notificationFCM.setContent(content);
        notificationFCM.setTitle(title);

        Call<Void> request = notificationService.sendFCMToSingleTopic(topic, notificationFCM);

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ACTION_FCM_NOTIFICATION);
        getContext().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void setListNotifications(ArrayList<NotificationFCM> list) {
        adapter.setListNotification(list);
    }

    @Override
    public void initData() {
        if(presenter !=null)
        {
            presenter.getListNotifications(getContext());
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void ClearNotifications(ArrayList<NotificationFCM> list)
    {
        String[] keys = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey());
            keys[i] = String.valueOf(list.get(i).getKey());
        }


        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<Void> request = notificationService.removeNotificationById("zed",keys);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    presenter.getListNotifications(getContext());
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }
}