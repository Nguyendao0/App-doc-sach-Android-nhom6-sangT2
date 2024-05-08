package com.example.helloworldjava.view.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        TextView text_name = convertView.findViewById(R.id.name_book);
        TextView text_author = convertView.findViewById(R.id.name_author);
        ImageView img = convertView.findViewById(R.id.img_book);

        text_name.setText(book.getTenSach());
        text_author.setText("Tác giả : "+ book.getTacGia());
        Glide.with(this.context).load(book.getUrlImage()).into(img);

        return convertView;
    }

    public void sortBook(String s){
        s = s.toUpperCase();
        int k  = 0;
        for (int i = 0; i < listSach.size(); i ++){
            Sach sach = listSach.get(i);
            String ten = sach.getTenSach().toUpperCase();
            if (ten.indexOf(s)>=0){
                listSach.set(i,listSach.get(k));
                listSach.set(k, sach);
                k++;
            }
        }
        this.notifyDataSetChanged();
    }

}
