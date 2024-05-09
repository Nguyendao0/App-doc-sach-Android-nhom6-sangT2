package com.example.helloworldjava.view.user;

import com.example.helloworldjava.model.entity.User;

public interface AccountSettingView {
    public void fillUserDataToEditView(User user);

    public void onUploadSuccess(String uri);
}
