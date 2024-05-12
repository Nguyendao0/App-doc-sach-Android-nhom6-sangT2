package com.example.helloworldjava.LibraryContractInterface;

import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.view.Library.LibraryBookAdapter;

import java.util.ArrayList;
import java.util.List;

public interface CurrentReadingContract {
    interface View{
        void setLibraryPresenter( LibraryContract.Presenter presenter);
        void setSachList(ArrayList<Sach> list);
        void setSachOfflineList(ArrayList<Sach> list);
        void setCurrentPresenter( Presenter presenter);
        void addBookOffline(Sach sach);
        void removeBookOffline(String IDSach);
        boolean Visible();
    }

    interface Presenter{
        void readSach();
        void readSachOffline();
    }
}