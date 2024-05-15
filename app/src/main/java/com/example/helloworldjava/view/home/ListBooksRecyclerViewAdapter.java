package com.example.helloworldjava.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBooksRecyclerViewAdapter extends RecyclerView.Adapter<ListBooksRecyclerViewAdapter.ViewHolder> {

    private List<Sach> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ListBooksRecyclerViewAdapter(Context context, List<Sach> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_books_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = mData.get(position);
        Picasso.get().load(sach.getImg()).into(holder.imageViewBookItem);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewBookItem;

        ViewHolder(View itemView) {
            super(itemView);
            imageViewBookItem = itemView.findViewById(R.id.imageViewBookItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Sach getItem(int i) {
        return mData.get(i);
    }

    // allows clicks events to be caught
    void setClickListener(ListBooksRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

