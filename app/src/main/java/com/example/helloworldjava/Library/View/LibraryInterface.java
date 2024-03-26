package com.example.helloworldjava.Library.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public interface LibraryInterface {
    interface View {
//        void showFragment(Fragment fragment);
    Fragment returnF();
    }

    interface Presenter {
//        void loadInitialFragment();
//        void changeFragment();
void onViewHolderClick(RecyclerView.ViewHolder viewHolder);
    }
}
