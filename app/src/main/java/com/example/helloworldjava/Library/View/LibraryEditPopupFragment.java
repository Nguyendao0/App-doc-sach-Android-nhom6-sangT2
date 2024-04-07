package com.example.helloworldjava.Library.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;

public class LibraryEditPopupFragment extends Fragment  implements EditPopupContract.View {

    private EditPopupContract.Presenter editPopupPresenter;
    private ImageButton btnClose;
    private ImageButton btnAdd;
    private ImageButton btnStorage;
    private ImageButton btnDelete;
    private ImageButton btnPlus;
    private TextView txtLibraryPopupMenu;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_edit_popup_fragment, container, false);

        btnClose = view.findViewById(R.id.imageButtonClose);
        btnAdd = view.findViewById(R.id.imageButtonLibraryAdd);
        btnDelete = view.findViewById(R.id.imageButtonLibraryDelete);
        btnStorage = view.findViewById(R.id.imageButtonLibraryStorage);
        btnPlus = view.findViewById(R.id.imageButtonLibraryPlus);
        txtLibraryPopupMenu = view.findViewById(R.id.textViewLibraryPopupMenu);
        // Gắn sự kiện onclick cho button
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPopupPresenter.showNavigationFragment(R.id.FCV_Navigation_Library);
                editPopupPresenter.resetItemViewSelected();
            }
        });
        System.out.println("create");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editPopupPresenter.displayPopupTabSelect();
    }

    @Override
    public void setEditPopupPresenter(EditPopupContract.Presenter editPopupPresenter) {
        this.editPopupPresenter = editPopupPresenter;
    }

    @Override
    public boolean Visible()
    {
        return this.isVisible();
    }


    @Override
    public void updatePopupmenu(int position) {
        switch (position){
            case 1:
                btnAdd.setVisibility(View.INVISIBLE);
                break;
            case 2:
                btnAdd.setVisibility(View.INVISIBLE);
                btnStorage.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                btnPlus.setVisibility(View.VISIBLE);

                txtLibraryPopupMenu.setText("Sửa các danh sách đọc");
                break;
            default:
                break;
        }
    }
}
