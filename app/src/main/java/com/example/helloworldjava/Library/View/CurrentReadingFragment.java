package com.example.helloworldjava.Library.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloworldjava.Library.Book;
import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CurrentReadingFragment extends Fragment implements CurrentReadingContract.View {
    private RecyclerView recyclerView;

    private BookAdapter bookAdapter;
    private CurrentReadingContract.Presenter presenter;
    private TextView txtAmountOtherBook;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);

        recyclerView =view.findViewById(R.id.RVOthersBooks);
        txtAmountOtherBook = view.findViewById(R.id.amountBooksOnline);


        bookAdapter = new BookAdapter(presenter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        this.presenter.getBook();
        this.presenter.getAmountOtherBook();

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void showBook(List<Book> bookList) {
        bookAdapter.setBookList(bookList);
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCurrentReadingPresenter(CurrentReadingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setAmountOtherBook(int amountOtherBook) {
       txtAmountOtherBook.setText(amountOtherBook + " truyện");  ;
    }

    @Override
    public void getItemViemSelected() {
        for(BookViewHolder b : presenter.getViewHolderSelected())
        {
            b.resetSelected();
        }
    }


}