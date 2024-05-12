package com.example.helloworldjava.LibraryContractInterface;

import androidx.fragment.app.Fragment;


import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.view.Library.LibraryBookViewHolder;

import java.util.ArrayList;

public interface LibraryContract {
    interface View {
        void showFragmentInNavigationContainer(Fragment fragment);
        int tabSelected();

        void removeBooksLibrary();
        void addBookOffline(Sach sach);
        void addBooksOffline();
        void removeBooksOffline();
        void removeBookOffline(String IDSach);
        boolean isCurreadingVisible();
//        void removeBookLibrary(String IDSach);
    }
    interface Presenter{
        void setNavigationView(NavigationContract.View navigationFragment);
        void setEditPopupView(EditPopupContract.View editPopupFragment);
        void setLibraryView(LibraryContract.View libraryView);
        void replaceFragmentInNavigationContainer(String nameFragment);
        int getTabSelected();
        void addToListBookView(LibraryBookViewHolder libraryBookViewHolder);
        void removeOutOfListBookView(LibraryBookViewHolder libraryBookViewHolder);
        boolean checkEditVisible();
        void clearSelectBookView();
        void selectBookItem(LibraryBookViewHolder bookViewHolder, boolean selected);
        ArrayList<LibraryBookViewHolder> getSelectBookItem();

        void addBookOffline(Sach sach);
        void removeBookOffline(String IDSach);
        void addBooksOffline();
        void removeBooksOffline();

        void removeBooksLibrary();

        boolean isCurreadingVisible();

        void setIsADD(boolean isAdd);
        boolean getIsADD();
    }
}