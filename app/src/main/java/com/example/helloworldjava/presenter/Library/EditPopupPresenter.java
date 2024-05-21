package com.example.helloworldjava.presenter.Library;

import com.example.helloworldjava.presenter.LibraryContractInterface.EditPopupContract;

public class EditPopupPresenter implements EditPopupContract.Presenter {
    EditPopupContract.View editPopupView;
    @Override
    public boolean checkVisible() {
        return editPopupView.visible();
    }

    @Override
    public void setEditPopupView(EditPopupContract.View editPopupView) {
        this.editPopupView = editPopupView;
    }

    @Override
    public void setItemsPopup(int position) {
        editPopupView.updatePopup(position);
    }
}