package com.example.helloworldjava.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListBooksHomeRecyclerViewAdapter extends RecyclerView.Adapter<ListBooksHomeRecyclerViewAdapter.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    private ListBooksRecyclerViewAdapter.ItemClickListener mClickListener;
    private int itemLayoutResId;

    // data is passed into the constructor
    ListBooksHomeRecyclerViewAdapter(Context context, String[] data, int itemLayoutResId) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.itemLayoutResId = itemLayoutResId;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ListBooksHomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(itemLayoutResId, parent, false);
        return new ListBooksHomeRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ListBooksHomeRecyclerViewAdapter.ViewHolder holder, int position) {
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(BookCategoryRecyclerViewAdapter.ItemClickListener itemClickListener) {
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}