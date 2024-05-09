package com.example.helloworldjava.view.Library;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.LibraryContractInterface.LibraryBookItem;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;

public class LibraryBookViewHolder  extends RecyclerView.ViewHolder{
    private TextView textViewTitleBook;
    private ImageView imageViewBook;
    private ImageView imageViewSelect;
    private boolean selected = false;
    private LibraryBookItem.Presenter presenter;
    public LibraryBookViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitleBook = itemView.findViewById(R.id.titleBook);
        imageViewBook = itemView.findViewById(R.id.imageBook);
        imageViewSelect = itemView.findViewById(R.id.imageViewSelect);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == false)
                {
                    presenter.setSelected(true);
                }
                else {
                    presenter.setSelected(false);
                }
            }
        });
    }

    public void bindData(Sach sach)
    {
        textViewTitleBook.setText(sach.getTenSach());
        Glide.with(this.itemView.getContext())
                .load(sach.getImg())
                .into(this.imageViewBook);
    }

    public void onClickItem(boolean selected)
    {
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

    public void setPresenter(LibraryBookItem.Presenter presenter)
    {
        this.presenter = presenter;
    }

}