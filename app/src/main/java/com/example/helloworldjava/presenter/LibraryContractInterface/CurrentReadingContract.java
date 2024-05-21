package com.example.helloworldjava.presenter.LibraryContractInterface;

import android.content.Context;

import com.example.helloworldjava.model.Realm.Sach;

import java.util.ArrayList;
import java.util.List;

public interface CurrentReadingContract {
    interface View{
        void setLibraryPresenter( LibraryContract.Presenter presenter);
        void setSachList(ArrayList<Sach> list);
        void setSachOfflineList(ArrayList<Sach> list);
        void setCurrentPresenter( Presenter presenter);
        boolean Visible();
        void setProgressBar(Sach sach);
    }

    interface Presenter{
        void readSach();
        void readSachOffline();
        void addBookOffline(Sach sach, Context context);
        void removeBookOffline(String IDSach);

        void addBooksOffline(List<Sach> sachList, Context context);
        void removesBookOffline(List<String> sachIDs);

        void removeBooksLibrary(List<String> sachIDs);
    }
}