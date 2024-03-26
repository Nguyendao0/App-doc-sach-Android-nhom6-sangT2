package com.example.helloworldjava.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.helloworldjava.Library.View.LibraryInterface;
import com.example.helloworldjava.Library.View.libraryEditPopupFragment;
import com.example.helloworldjava.Library.View.libraryFragmentNavigation;
import com.example.helloworldjava.R;
import com.example.helloworldjava.databinding.LibraryEditPopupFragmentBinding;
import com.google.android.material.tabs.TabLayout;

public class LibraryActivity extends AppCompatActivity  implements LibraryInterface.View {

    private TabLayout tabLayoutLibrary;
    private libraryEditPopupFragment popupEditFragment;
    private libraryFragmentNavigation navigationFragment;

    private FragmentContainerView fcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        popupEditFragment = new libraryEditPopupFragment();

        tabLayoutLibrary = findViewById(R.id.tabLayoutLibrary);
        fcv = findViewById(R.id.fragmentContainerViewNavigation);
        navigationFragment = fcv.getFragment();
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

        if(popupEditFragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerViewNavigation, navigationFragment, null)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public Fragment returnF() {
        return popupEditFragment;
    }
}