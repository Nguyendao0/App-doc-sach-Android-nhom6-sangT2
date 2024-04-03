package com.example.helloworldjava.Library.LibraryInterface;

public interface EditPopupContract {
    interface View{

        void setEditPopupPresenter(Presenter editPopupPresenter);
        boolean Visible();
    }
    interface Presenter{
        void showNavigationFragment(int containerViewId);
        void resetItemViewSelected();
    }
}
