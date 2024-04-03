package com.example.helloworldjava.Library.View;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.Library.Book;
import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
    private List<Book> bookList;

    private CurrentReadingContract.Presenter presenter;
    public BookAdapter(CurrentReadingContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_library_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(itemView, presenter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setLibraryBook(bookList.get(position));
        holder.setUI();
    }

    @Override
    public int getItemCount() {
        if(bookList == null)
        {
            return 0;
        }
        return  bookList.size();
    }


    public void setBookList(List<Book> bookList)
    {
        this.bookList = bookList;
    }

}


