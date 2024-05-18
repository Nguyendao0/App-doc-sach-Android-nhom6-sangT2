package com.example.helloworldjava.view.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListBooksHomeRecyclerViewAdapter extends RecyclerView.Adapter<ListBooksHomeRecyclerViewAdapter.ViewHolder> {

    private List<Sach> mData;
    private LayoutInflater mInflater;
    private ListBooksHomeRecyclerViewAdapter.ItemClickListener mClickListener;
    private int itemLayoutResId;
    private String name;

    // data is passed into the constructor
    public ListBooksHomeRecyclerViewAdapter(Context context, List<Sach> data, int itemLayoutResId, String name) {
        this.mInflater = LayoutInflater.from(context);
        this.mData =  data;
        this.itemLayoutResId = itemLayoutResId;
        this.name = name;
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
        Sach sach = mData.get(position);
        Log.w("Api Start","------Sussecs-------");
        Log.w("Api Start","------"+sach.getTenSach()+"-------");
        Log.w("Api Start","-----------------");
        if (holder.tvcontent != null) {
            String tenSach = sach.getTenSach();
            if(tenSach != null){
                holder.tvcontent.setText(tenSach);
            }
        }

        if (sach.getImg() != null) {
            Picasso.get().load(sach.getImg()).into(holder.imageViewBookHome);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvcontent, tvdatecreate;
        ImageView imageViewBookHome;
        ViewHolder(View itemView) {
            super(itemView);
            tvcontent = itemView.findViewById(R.id.booktextbook);
            imageViewBookHome = itemView.findViewById(R.id.imageViewBookHome);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(), name);
        }
    }
    // convenience method for getting data at click position
    public Sach getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ListBooksHomeRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position, String name);
    }
}