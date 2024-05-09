package com.example.helloworldjava.LibraryContractInterface;

import com.example.helloworldjava.model.Realm.Sach;

public interface LibraryBookItem {
    interface View{
        void bindData(Sach sach);
        void onClickItem(boolean selected);
        void setPresenter(Presenter presenter);
    }

    interface Presenter{
        void setSelected(boolean selected);
    }
}
