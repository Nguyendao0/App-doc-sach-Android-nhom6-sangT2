package com.example.helloworldjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.mvp.model.Book;

import java.util.List;

public class AdapterHistorySearch extends BaseAdapter {
    private Context context;
    private int layoutl;
    private List<Book> listBook;

    public AdapterHistorySearch(Context context, int layoutl, List<Book> listBook) {
        this.context = context;
        this.layoutl = layoutl;
        this.listBook = listBook;
    }

    @Override
    public int getCount() {
        return listBook.size();
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
        Book book = listBook.get(position);
        TextView text_name = convertView.findViewById(R.id.name);
        TextView text_decription = convertView.findViewById(R.id.mota);
        ImageView img = convertView.findViewById(R.id.imageBook);
        text_name.setText(book.getName());
        text_decription.setText(book.getDecription());
        img.setImageResource(book.getImage());

        return convertView;
    }
}
