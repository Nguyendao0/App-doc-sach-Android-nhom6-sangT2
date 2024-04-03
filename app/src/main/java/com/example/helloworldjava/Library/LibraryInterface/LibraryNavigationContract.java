package com.example.helloworldjava.Library.LibraryInterface;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public interface LibraryNavigationContract {
    interface View{
        // Cập nhật thay đổi các itemmenu của PopupMenu của View
        void updatePopupmenu(int position);
        // Đặt lại trạng thái ban đầu cho PopupMenu
        void resetPopupMenu();
        void setNavigationPresenter(Presenter navigationPresenter);
    }
    interface Presenter{
        void showEditPopupFragment( int containerViewId);
        void onClickTabItem(int position, int containerViewId);

    }
    interface Model{

    }
}
