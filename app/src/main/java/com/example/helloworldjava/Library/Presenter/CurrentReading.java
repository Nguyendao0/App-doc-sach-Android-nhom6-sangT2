package com.example.helloworldjava.Library.Presenter;

import com.example.helloworldjava.Library.Book;
import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.ValueListerner;
import com.example.helloworldjava.Library.Model.BookModelFB;
import com.example.helloworldjava.Library.View.BookViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CurrentReading implements CurrentReadingContract.Presenter {
    CurrentReadingContract.View currentReadingView;
    EditPopupContract.View editPopupView;
    LibraryContract.Model bookModelFB;
    private List<BookViewHolder> selectedViewHolders = new ArrayList<>();
    public CurrentReading(CurrentReadingContract.View currentReadingView,EditPopupContract.View editPopupView) {
        this.currentReadingView = currentReadingView;
        this.editPopupView = editPopupView;
    }

    @Override
    public void getBook() {
        bookModelFB = BookModelFB.getInstance();
        bookModelFB.Read(new ValueListerner.ListAgrument() {
            @Override
            public void onDataLoaded(List<Book> readBookList) {
                bookModelFB.dowloadImageFile(new ValueListerner.ListAgrument() {
                    @Override
                    public void onDataLoaded(List<Book> bookList) {
//                        currentReadingView.setRecyclerView(bookList);
                        currentReadingView.showBook(bookList);
                    }
                }, readBookList);
            }
        });
    }

    @Override
    public void getAmountOtherBook() {
        bookModelFB = BookModelFB.getInstance();
        bookModelFB.Read(new ValueListerner.ListAgrument() {
            @Override
            public void onDataLoaded(List<Book> bookList) {
                currentReadingView.setAmountOtherBook(bookList.size());
            }
        });
    }

    @Override
    public void onClickItem(CurrentReadingContract.View.itemView itemView) {
        if(editPopupView.Visible() == true)
        {
            itemView.onClickItem();
        }
    }

    @Override
    public void setViewHolderSelected(CurrentReadingContract.View.itemView itemView, boolean selected)
    {
        if(selected == false && selectedViewHolders.contains(itemView) == true)
        {
            selectedViewHolders.remove((BookViewHolder) itemView);
        }
        else {
            selectedViewHolders.add((BookViewHolder) itemView);
        }
    }

    @Override
    public List<BookViewHolder> getViewHolderSelected()
    {
        return selectedViewHolders;
    }
}
