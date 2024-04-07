package com.example.helloworldjava.Library.LibraryInterface;

public interface EditPopupContract {
    interface View{

        void setEditPopupPresenter(Presenter editPopupPresenter);
        boolean Visible();
        void updatePopupmenu(int position);
    }
    interface Presenter{
        void showNavigationFragment(int containerViewId);
        void resetItemViewSelected();
        void displayPopupTabSelect();
    }
}
