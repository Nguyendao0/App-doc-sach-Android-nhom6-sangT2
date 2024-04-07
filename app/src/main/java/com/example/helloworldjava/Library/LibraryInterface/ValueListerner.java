package com.example.helloworldjava.Library.LibraryInterface;

import com.example.helloworldjava.Library.BookLibrary;

import java.util.List;

public interface ValueListerner {
    interface ListAgrument{
        void onDataLoaded(List<BookLibrary> bookList);
    }

    interface  StringAgrument{
        void onDataLoaded(String s);
    }
}
