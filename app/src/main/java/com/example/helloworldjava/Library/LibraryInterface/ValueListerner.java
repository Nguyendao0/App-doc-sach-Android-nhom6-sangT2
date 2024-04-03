package com.example.helloworldjava.Library.LibraryInterface;

import com.example.helloworldjava.Library.Book;

import java.util.List;

public interface ValueListerner {
    interface ListAgrument{
        void onDataLoaded(List<Book> bookList);
    }

    interface  StringAgrument{
        void onDataLoaded(String s);
    }
}
