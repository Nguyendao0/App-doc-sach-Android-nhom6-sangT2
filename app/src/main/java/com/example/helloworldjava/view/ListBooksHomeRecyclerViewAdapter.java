package com.example.helloworldjava.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.APIResponeModel.ApiResponseSachModle;
import com.example.helloworldjava.R;
import com.google.gson.Gson;

import java.util.List;

public class ListBooksHomeRecyclerViewAdapter extends RecyclerView.Adapter<ListBooksHomeRecyclerViewAdapter.ViewHolder> {

    private List<ApiResponseSachModle> mData;
    private String gdata;
    private LayoutInflater mInflater;
    private ListBooksRecyclerViewAdapter.ItemClickListener mClickListener;
    private int itemLayoutResId;

    // data is passed into the constructor
    public ListBooksHomeRecyclerViewAdapter(Context context, List<ApiResponseSachModle> data, int itemLayoutResId) {
        this.mInflater = LayoutInflater.from(context);
        this.mData =  data;
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
        ApiResponseSachModle dataItem = mData.get(position);
        Log.w("Api Start","------Sussecs-------");
        Log.w("Api Start","------"+dataItem.getSach().getTenSach()+"-------");
        Log.w("Api Start","-----------------");
        if (holder.tvcontent != null && dataItem != null && dataItem.getSach() != null) {
            String tenSach = dataItem.getSach().getTenSach();
            if(tenSach != null){
                holder.tvcontent.setText(tenSach);
            }
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return (int) mData.stream().count();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvcontent, tvdatecreate;
        ViewHolder(View itemView) {
            super(itemView);
            tvcontent = itemView.findViewById(R.id.booktextbook);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    ApiResponseSachModle getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(BookCategoryRecyclerViewAdapter.ItemClickListener itemClickListener) {
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}