package com.example.helloworldjava.Library.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.LibraryActivity;
import com.example.helloworldjava.MainActivity;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;

public class libraryEditPopupFragment extends Fragment {

    public libraryEditPopupFragment() {
        super(R.layout.library_edit_popup_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutLibrary);
        int position = tabLayout.getSelectedTabPosition();

        switch (position){
            case 1:
                view.findViewById(R.id.imageButtonLibraryAdd).setVisibility(View.INVISIBLE);
                break;
            case 2:
                view.findViewById(R.id.imageButtonLibraryAdd).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.imageButtonLibraryStorage).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.imageButtonLibraryDelete).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.imageButtonLibraryPlus).setVisibility(View.VISIBLE);

                TextView textView = view.findViewById(R.id.textViewLibraryPopupMenu);
                textView.setText("Sửa các danh sách đọc");
                break;
            default:
                break;
        }

        ImageButton button = view.findViewById(R.id.imageButtonClose);

        // Gắn sự kiện onclick cho button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainerViewNavigation, libraryFragmentNavigation.class, null)
                        .commit();
            }
        });
    }

}
