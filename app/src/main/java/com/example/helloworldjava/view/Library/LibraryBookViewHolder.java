package com.example.helloworldjava.view.Library;

import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.R;

import com.example.helloworldjava.model.Realm.Sach;

import java.io.File;

public class LibraryBookViewHolder  extends RecyclerView.ViewHolder{
    private TextView textViewTitleBook;
    private ImageView imageViewBook;
    private ImageView imageViewSelect;
    private ImageButton imgButtonDowload;

    private boolean selected = false;
    private boolean isDowloaded = false;
    private Sach sach;
    private LibraryContract.Presenter itemPresenter;

    public LibraryBookViewHolder(@NonNull View itemView ,LibraryContract.Presenter itemPresenter, boolean isDowloaded) {
        super(itemView);

        textViewTitleBook = itemView.findViewById(R.id.titleBook);
        imageViewBook = itemView.findViewById(R.id.imageBook);
        imageViewSelect = itemView.findViewById(R.id.imageViewSelect);
        imgButtonDowload = itemView.findViewById(R.id.imgButtonDowload);

        this.isDowloaded = isDowloaded;
        this.itemPresenter = itemPresenter;

        setOnclickDowload();
        setImgButtonDowload();
        if(isDowloaded == false) {
            setOnclickSelectItem();
        }

    }

    private void setImgButtonDowload()
    {
        if(isDowloaded == true)
        {
            imgButtonDowload.setImageResource(R.drawable.outline_download_done_24);
        }
    }


    private void setOnclickDowload()
    {
        imgButtonDowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDowloaded == false && itemPresenter.isCurreadingVisible() == true)
                {
                    String fileName = Uri.parse(sach.getImg()).getLastPathSegment();
                    sach.setImg("/data/user/0/com.example.helloworldjava/files/"+fileName);
                    itemPresenter.addBookOffline(sach);
                }
                else if(isDowloaded == true && itemPresenter.isCurreadingVisible() == true){
                    String filePath = sach.getImg();

                    File file = new File(filePath);
                    file.delete();
                    itemPresenter.removeBookOffline(sach.getID());
                }
            }
        });
    }


    private void setOnclickSelectItem()
    {
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemPresenter.checkEditVisible()) {
                    if (selected == true) {
                        itemPresenter.selectBookItem(LibraryBookViewHolder.this, true);
                        selected = false;
                        itemPresenter.removeToListBookView(LibraryBookViewHolder.this);
                    } else {
                        itemPresenter.selectBookItem(LibraryBookViewHolder.this, false);
                        selected = true;
                        itemPresenter.addToListBookView(LibraryBookViewHolder.this);

                    }
                } else {
                }
            }
        });
    }

    public void bindData(Sach sach)
    {
        this.sach = sach;
        textViewTitleBook.setText(sach.getTenSach());
        Glide.with(this.itemView.getContext())
                .load(sach.getImg())
                .into(this.imageViewBook);
    }

    public void onClickItem(boolean selected)
    {
        if(selected == true)
        {
            this.imageViewBook.setAlpha(1.0f);
            this.imageViewSelect.setVisibility(View.INVISIBLE);
        }
        else {
            this.imageViewBook.setAlpha(0.5f);
            this.imageViewSelect.setVisibility(View.VISIBLE);
        }
    }


}