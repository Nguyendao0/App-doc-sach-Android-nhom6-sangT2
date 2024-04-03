package com.example.helloworldjava.Library.LibraryInterface;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.Book;

import java.util.List;

public interface LibraryContract {
    interface View{
        void replaceFragmentToFragmentContainerView(Fragment fragment, int containerView);
    }

    interface Model{
            void Read(ValueListerner.ListAgrument listerner);
            void dowloadImageFile(ValueListerner.ListAgrument listerner, List<Book> bookList);

    }
}
