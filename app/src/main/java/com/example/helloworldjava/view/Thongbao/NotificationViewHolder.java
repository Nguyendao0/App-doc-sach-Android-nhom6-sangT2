package com.example.helloworldjava.view.Thongbao;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;

public class NotificationViewHolder  extends RecyclerView.ViewHolder{
    private TextView title;
    private TextView content;
    private ImageView icon;
    private ImageButton delete;
    private NotificationFCM notificationFCM;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.txtTitle);
        content = itemView.findViewById(R.id.txtContent);
        icon = itemView.findViewById(R.id.imgIcon);
        delete = itemView.findViewById(R.id.imgBtnDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void BindData(NotificationFCM notificationFCM)
    {
        this.notificationFCM = notificationFCM;
        title.setText(notificationFCM.getTitle());
        title.setText(notificationFCM.getContent());
    }
}
