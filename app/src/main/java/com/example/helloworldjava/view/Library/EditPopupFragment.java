package com.example.helloworldjava.view.Library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.LibraryContractInterface.EditPopupContract;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.R;

public class EditPopupFragment extends Fragment implements EditPopupContract.View {
    private EditPopupContract.Presenter presenter;
    private LibraryContract.Presenter libraryPresenter;
    private ImageButton btnClose;
    private ImageButton btnAdd;
    private ImageButton btnStorage;
    private ImageButton btnDelete;
    private ImageButton btnPlus;
    private TextView txtLibraryPopupMenu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_edit_popup_fragment, container, false);

        btnClose = view.findViewById(R.id.imageButtonClose);
        btnAdd = view.findViewById(R.id.imageButtonLibraryAdd);
        btnDelete = view.findViewById(R.id.imageButtonLibraryDelete);
        btnStorage = view.findViewById(R.id.imageButtonLibraryStorage);
        btnPlus = view.findViewById(R.id.imageButtonLibraryPlus);
        txtLibraryPopupMenu = view.findViewById(R.id.textViewLibraryPopupMenu);

        presenter.setItemsPopup(libraryPresenter.getTabSelected());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libraryPresenter.replaceFragmentInNavigationContainer("NavigationFragment");
            }
        });
        return view;
    }

    @Override
    public void updatePopup(int position) {
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

    @Override
    public void setPresenter(EditPopupContract.Presenter editPopuptPresenter) {
        this.presenter = editPopuptPresenter;
    }

    @Override
    public void setLibraryPresenter(LibraryContract.Presenter libraryPresenter) {
        this.libraryPresenter = libraryPresenter;
    }

    @Override
    public boolean visible() {
         return this.isVisible();
    }
}
