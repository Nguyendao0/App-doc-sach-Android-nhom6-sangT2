package com.example.helloworldjava.view.welcome;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.view.login.Account_Login;
import com.example.helloworldjava.view.register.Account_Register;

public class WelcomePagerAdapter extends RecyclerView.Adapter<WelcomePagerAdapter.ViewHolder> {
    private int[] images = {R.drawable.img_welcome, R.drawable.img_dangnhap, R.drawable.img_dangky};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welcome_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageRes = images[position];
        holder.imageView.setImageResource(imageRes);

        if (position == 0){
            holder.btnLogin.setVisibility(View.GONE); // Ẩn nút trên các hình ảnh khác
            holder.btnRegister.setVisibility(View.GONE);
        }

        if (position == 1) { // Chỉ hiển thị nút trên hình ảnh thứ 2 (vị trí 1 trong mảng)
            holder.btnLogin.setVisibility(View.VISIBLE);
            holder.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý sự kiện khi nút đăng nhập được nhấn
                    // Ví dụ: Chuyển đến màn hình đăng nhập
                    Intent intent = new Intent(v.getContext(), Account_Login.class);
                    v.getContext().startActivity(intent);
                }
            });
            holder.btnRegister.setVisibility(View.GONE); // Ẩn nút trên các hình ảnh khác

        }
        
        if (position == 2) { // Chỉ hiển thị nút trên hình ảnh thứ ba (vị trí 2 trong mảng)
            holder.btnRegister.setVisibility(View.VISIBLE);
            holder.btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý sự kiện khi nút đăng nhập được nhấn
                    // Ví dụ: Chuyển đến màn hình đăng ký
                    Intent intent = new Intent(v.getContext(), Account_Register.class);
                    v.getContext().startActivity(intent);
                }
            });
            holder.btnLogin.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button btnLogin;
        Button btnRegister;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            btnLogin = itemView.findViewById(R.id.btnLogin);
            btnRegister = itemView.findViewById(R.id.btnRegister);
        }
    }
}
