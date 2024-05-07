package com.example.helloworldjava.model;

import com.example.helloworldjava.R;
import com.example.helloworldjava.view.user.AccountSettingActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileFeatureModel {
    List<ProfileFeature> profileFeatures;

    public ProfileFeatureModel() {
        profileFeatures = new ArrayList<>();
        profileFeatures.add(new ProfileFeature(R.drawable.notifications, "Thông báo"));
        profileFeatures.add(new ProfileFeature(R.drawable.bookmark, "Danh sách yêu thích"));
        profileFeatures.add(new ProfileFeature(R.drawable.settings_24px, "Thông tin tài khoản", AccountSettingActivity.class));
        profileFeatures.add(new ProfileFeature(R.drawable.logout, "Đăng xuất"));
    }

    public List<ProfileFeature> getListFeatures() {
        return profileFeatures;
    }
}
