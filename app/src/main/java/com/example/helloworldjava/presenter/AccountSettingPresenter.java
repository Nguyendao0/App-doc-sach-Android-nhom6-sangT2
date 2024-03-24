package com.example.helloworldjava.presenter;

import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.view.AccountSettingView;

public class AccountSettingPresenter {
    private UserModel userModel;
    private AccountSettingView accountSettingView;

    public AccountSettingPresenter(AccountSettingView accountSettingView) {
        this.accountSettingView = accountSettingView;
        this.userModel = new UserModel();
    }


    public void fillUserDataToEditView() {
        User user = userModel.getUser();
        accountSettingView.fillUserDataToEditView(user);
    }
}
