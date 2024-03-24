package com.example.helloworldjava.Library;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.R;

public class BookViewHolder extends RecyclerView.ViewHolder{
    private TextView textViewTitleBook;
    private ImageView imageViewBook;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitleBook = itemView.findViewById(R.id.titleBook);
        imageViewBook = itemView.findViewById(R.id.imageBook);
    }

    public void setUI(String txt, String imageUrl) {
        this.textViewTitleBook.setText(txt);

        // Tải ảnh từ URL và hiển thị nó trong ImageView
        Glide.with(this.itemView.getContext())
                .load(imageUrl)
                .into(this.imageViewBook);
    }
}
