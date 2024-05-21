package com.example.helloworldjava.view.Library;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.presenter.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.R;

import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;

public class LibraryBookViewHolder  extends RecyclerView.ViewHolder{
    private TextView textViewTitleBook;
    private ImageView imageViewBook;
    private ImageView imageViewSelect;
    private ImageButton imgButtonDowload;
    private ProgressBar progressBar;
    private boolean selected = false;
    private boolean isDowloaded = false;
    private Sach sach;
    private LibraryContract.Presenter presenter;

    public LibraryBookViewHolder(@NonNull View itemView , LibraryContract.Presenter presenter, boolean isDowloaded) {
        super(itemView);

        textViewTitleBook = itemView.findViewById(R.id.titleBook);
        imageViewBook = itemView.findViewById(R.id.imageBook);
        imageViewSelect = itemView.findViewById(R.id.imageViewSelect);
        imgButtonDowload = itemView.findViewById(R.id.imgButtonDowload);
        progressBar = itemView.findViewById(R.id.progress);

        this.isDowloaded = isDowloaded;
        this.presenter = presenter;

        setOnclickDowload();
        setImgButtonDowload();
        setOnclickSelectItem();

    }

    private void setProgressBar()
    {
        if(sach.getChuong_Items() != null) {
            int tongChuongDaDoc = 0;
//            for (Chuong c : sach.getChuong_Items()) {
//                if (c.isDanhDau() == true) {
//                    tongChuongDaDoc++;
//                }
//            }
            int tongChuong = this.sach.getChuong_Items().size();
            int prog = (int) ((tongChuongDaDoc / (float) tongChuong) * 100);
            progressBar.setProgress(prog);
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
                if(isDowloaded == false && presenter.isCurreadingVisible() == true)
                {
                    presenter.addBookOffline(sach);
                }
                else if(isDowloaded == true && presenter.isCurreadingVisible() == true){
//                    String filePath = sach.getImg();
//
//                    File file = new File(filePath);
//                    file.delete();
                    presenter.removeBookOffline(sach.getID());
                }
            }
        });
    }


    private void setOnclickSelectItem()
    {
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.checkEditVisible()) {
                    if (selected == true  && presenter.getIsADD() == true && isDowloaded == false) {
                        removeOutOfListBookView();
                    } else if(selected == false  && presenter.getIsADD() == true && isDowloaded == false){
                        addToListBookView();
                    }

                    if(selected == true  && presenter.getIsADD() == false && isDowloaded == true)
                    {
                        removeOutOfListBookView();
                    }
                    else if(selected == false  && presenter.getIsADD() == false && isDowloaded == true)
                    {
                        addToListBookView();
                    }
                    else if (selected == true  && presenter.getIsADD() == false) {
                        removeOutOfListBookView();
                    } else if(selected == false  && presenter.getIsADD() == false ){
                        addToListBookView();
                    }
                }
                else {
                    Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                    intent.putExtra("idSach", sach.getID());
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    public boolean getIsDowloaded()
    {
        return isDowloaded;
    }
    private void addToListBookView()
    {
        presenter.selectBookItem(LibraryBookViewHolder.this, false);
        this.selected = true;
        presenter.addToListBookView(LibraryBookViewHolder.this);
    }

    private void removeOutOfListBookView(){
        presenter.selectBookItem(LibraryBookViewHolder.this, true);
        this.selected = false;
        presenter.removeOutOfListBookView(LibraryBookViewHolder.this);
    }

    public void bindData(Sach sach)
    {
        this.sach = sach;
        textViewTitleBook.setText(sach.getTenSach());
        Glide.with(this.itemView.getContext())
                .load(sach.getImg())
                .into(this.imageViewBook);
        setProgressBar();
    }

    public Sach getSach()
    {
        return this.sach;
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