package com.example.helloworldjava.view.Library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookAdapter extends RecyclerView.Adapter<LibraryBookViewHolder>{
    private ArrayList<Sach> sachList;
    private LayoutInflater minflater;
    private LibraryContract.Presenter presenter;
    private boolean isDowloaded;
    public LibraryBookAdapter(Context context,  LibraryContract.Presenter presenter, boolean isDowloaded) {
        this.presenter = presenter;
        this.minflater = LayoutInflater.from(context);
        this.isDowloaded = isDowloaded;
    }


    @NonNull
    @Override
    public LibraryBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = minflater.inflate(R.layout.book_library_item, parent, false);
        LibraryBookViewHolder libraryBookViewHolder = new LibraryBookViewHolder(itemView, presenter, isDowloaded);
        return libraryBookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryBookViewHolder holder, int position) {
        holder.bindData(sachList.get(position));
    }

    @Override
    public int getItemCount() {
        return sachList != null ? sachList.size() : 0;
    }

    public void setSachList(ArrayList<Sach> list)
    {
        this.sachList = list;
        notifyDataSetChanged();
    }
}