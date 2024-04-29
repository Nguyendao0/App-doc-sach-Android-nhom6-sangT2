package com.example.helloworldjava.view.Library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.List;

public class LibraryBookAdapter extends RecyclerView.Adapter<LibraryBookViewHolder>{
    private List<Sach> sachList;
    private LayoutInflater minflater;

    public LibraryBookAdapter(Context context) {
//        this.sachList = sachList;
        this.minflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public LibraryBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = minflater.inflate(R.layout.book_library_item, parent, false);
        LibraryBookViewHolder libraryBookViewHolder = new LibraryBookViewHolder(itemView);
        return libraryBookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryBookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}