package com.example.helloworldjava.model.FCM.Tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.model.FCM.NotificationFCM;
import com.example.helloworldjava.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder>{
    ArrayList<NotificationFCM> listNotification ;
    private LayoutInflater minflater;
    public NotificationAdapter(Context context) {
        this.minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = minflater.inflate(R.layout.notification_item, parent, false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(itemView);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.BindData(listNotification.get(position));
    }

    @Override
    public int getItemCount() {
        if(listNotification == null)
        {
            return 0;
        }
        return listNotification.size();
    }

    public ArrayList<NotificationFCM> getListNotification()
    {
        return this.listNotification;
    }

    public void setListNotification(ArrayList<NotificationFCM> list)
    {
        this.listNotification = list;
        notifyDataSetChanged();
    }
}
