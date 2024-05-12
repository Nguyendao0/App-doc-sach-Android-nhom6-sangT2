package com.example.helloworldjava.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;

import java.util.List;

public class DanhSachChuongAdapter extends RecyclerView.Adapter<DanhSachChuongAdapter.ViewHolder> {
    private List<Chuong> mData;
    private LayoutInflater mInflater;
    private DanhSachChuongAdapter.ItemClickListener mClickListener;

    public DanhSachChuongAdapter(Context context, List<Chuong> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_chuong_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chuong chuong = mData.get(position);
        holder.myTextView.setText("Chương " + chuong.getSoThuTu() + ": " + chuong.getTenChuong());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.list_chuong_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Chuong getChuong(int position) {
        return mData.get(position);
    }

    void setClickListener(DanhSachChuongAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
