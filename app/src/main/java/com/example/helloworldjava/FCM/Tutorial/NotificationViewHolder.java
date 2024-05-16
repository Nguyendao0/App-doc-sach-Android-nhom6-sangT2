package com.example.helloworldjava.FCM.Tutorial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.R;

public class NotificationViewHolder  extends RecyclerView.ViewHolder{
    private TextView title;
    private TextView content;
    private NotificationFCM notificationFCM;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.txtTitle);
        content = itemView.findViewById(R.id.txtContent);


    }

    public void BindData(NotificationFCM notificationFCM)
    {
        this.notificationFCM = notificationFCM;
        title.setText(notificationFCM.getTitle());
        content.setText(notificationFCM.getContent());
    }

    public NotificationFCM getNotification()
    {
        return this.notificationFCM;
    }
}
