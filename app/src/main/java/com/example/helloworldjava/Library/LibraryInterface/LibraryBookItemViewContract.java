package com.example.helloworldjava.Library.LibraryInterface;

public interface LibraryBookItemViewContract {
    interface View{
        void selectedItem();
    }

    interface Presenter{
        void onClickItem();
    }
}
