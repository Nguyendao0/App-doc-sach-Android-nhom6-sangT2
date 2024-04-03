package com.example.helloworldjava.Library.LibraryInterface;

import com.example.helloworldjava.Library.Book;
import com.example.helloworldjava.Library.View.BookViewHolder;

import java.util.List;

public interface CurrentReadingContract {
    interface View{
       void showBook(List<Book> bookList);
       void setCurrentReadingPresenter(Presenter presenter);
       void setAmountOtherBook(int amountOtherBook);
       void getItemViemSelected();
       interface itemView{
           void onClickItem();
           Book getBook();
       }
    }
    interface Presenter{
        void getBook();
        void getAmountOtherBook();
        void onClickItem(View.itemView itemView);
        List<BookViewHolder> getViewHolderSelected();
        void setViewHolderSelected(View.itemView itemView, boolean selected);
    }

}
