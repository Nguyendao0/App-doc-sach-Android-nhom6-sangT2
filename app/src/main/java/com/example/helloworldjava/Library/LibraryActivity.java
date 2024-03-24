package com.example.helloworldjava.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.helloworldjava.Library.View.libraryFragmentNavigation;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;

public class LibraryActivity extends AppCompatActivity  {

    private TabLayout tabLayoutLibrary;
    private ImageButton imageButtonMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        tabLayoutLibrary = findViewById(R.id.tabLayoutLibrary);

        tabLayoutLibrary.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                displayFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Không cần xử lý khi TabItem không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Không cần xử lý khi TabItem được chọn lại
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerViewNavigation, libraryFragmentNavigation.class, null)
                    .commit();
        }

    }

    private void displayFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new CurrentReadingFragment();
                break;
            case 1:
                fragment = new BookStorageFragment();
                break;
            case 2:
                fragment = new ReadingListFragment();
                break;
            default:
                return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerViewLibraryContent , fragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerViewNavigation, libraryFragmentNavigation.class, null)
                .commit();
    }



}