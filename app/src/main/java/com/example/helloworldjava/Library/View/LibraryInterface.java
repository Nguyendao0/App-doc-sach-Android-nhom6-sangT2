package com.example.helloworldjava.Library.View;

import androidx.fragment.app.Fragment;

public interface LibraryInterface {
    interface View {
        void showFragment(Fragment fragment);
    }

    interface Presenter {
        void loadInitialFragment();
        void changeFragment();
    }
}
