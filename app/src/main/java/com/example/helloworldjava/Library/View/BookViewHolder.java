package com.example.helloworldjava.Library.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.Library.BookLibrary;
import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements CurrentReadingContract.View.itemView{
    private TextView textViewTitleBook;
    private ImageView imageViewBook;
    private ImageView imageViewSelect;
    private boolean selected = false;
    private CurrentReadingContract.Presenter presenter;
    private BookLibrary book;
    public BookViewHolder(@NonNull View itemView, CurrentReadingContract.Presenter presenter) {
        super(itemView);
        textViewTitleBook = itemView.findViewById(R.id.titleBook);
        imageViewBook = itemView.findViewById(R.id.imageBook);
        imageViewSelect = itemView.findViewById(R.id.imageViewSelect);

        this.presenter = presenter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickItem(BookViewHolder.this);
                if(selected == false)
                {
                    selected = true;
                    presenter.setViewHolderSelected(BookViewHolder.this, selected);
                }
                else {
                    selected = false;
                    presenter.setViewHolderSelected(BookViewHolder.this, selected);
                }
            }
        });
    }

    public void setUI() {
        this.textViewTitleBook.setText(book.getTitle());
        // Tải ảnh từ URL và hiển thị nó trong ImageView
        Glide.with(this.itemView.getContext())
                .load(book.getDowloadedImageFilePath())
                .into(this.imageViewBook);
    }

    public void setLibraryBook(BookLibrary book)
    {
        this.book = book;
    }


    public void resetSelected()
    {
        imageViewBook.setAlpha(1.0f);
        imageViewSelect.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClickItem() {
        if(selected == true)
        {
            imageViewBook.setAlpha(1.0f);
            imageViewSelect.setVisibility(View.INVISIBLE);
        }
        else {
            imageViewBook.setAlpha(0.5f);
            imageViewSelect.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public BookLibrary getBook() {
        return this.book;
    }
}
