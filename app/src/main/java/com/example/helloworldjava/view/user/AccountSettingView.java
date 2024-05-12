package com.example.helloworldjava.view.user;

import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.model.entity.User;

public interface AccountSettingView {
    public void fillUserDataToEditView(NguoiDung nguoiDung);

    public void onUploadSuccess(String uri);
}
