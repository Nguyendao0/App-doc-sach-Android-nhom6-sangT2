package com.example.helloworldjava.view.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;

import java.util.List;

public class AdapterListBook extends BaseAdapter {
    private Context context;
    private int layoutl;
    private List<Sach> listSach;

    public AdapterListBook(Context context, int layoutl, List<Sach> listSach) {
        this.context = context;
        this.layoutl = layoutl;
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layoutl, null);
        Sach book = listSach.get(position);
        TextView text_name = convertView.findViewById(R.id.name);
        TextView text_decription = convertView.findViewById(R.id.mota);
        ImageView img = convertView.findViewById(R.id.imageBook);
        text_name.setText(book.getTenSach());
        text_decription.setText(book.getMoTa());
        img.setImageResource(Integer.parseInt(book.getUrlImage()));

        return convertView;
    }
}
