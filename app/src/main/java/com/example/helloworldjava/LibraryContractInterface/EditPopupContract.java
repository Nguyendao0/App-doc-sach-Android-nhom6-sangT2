package com.example.helloworldjava.LibraryContractInterface;

public interface EditPopupContract {
    interface View{
        void updatePopup(int position);
        void setPresenter(EditPopupContract.Presenter editPopuptPresenter);
        void setLibraryPresenter(LibraryContract.Presenter libraryPresenter);
        boolean visible();
    }

    interface Presenter{
        boolean checkVisible();
        void setEditPopupView(EditPopupContract.View editPopupView);
        void setItemsPopup(int position);
    }
}
