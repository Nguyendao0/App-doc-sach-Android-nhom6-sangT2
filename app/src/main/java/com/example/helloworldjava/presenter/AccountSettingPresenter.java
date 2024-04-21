package com.example.helloworldjava.presenter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.example.helloworldjava.model.FirebaseStorageHelper;
import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.view.user.AccountSettingView;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class AccountSettingPresenter {
    private final UserModel userModel;
    private final AccountSettingView accountSettingView;

    public AccountSettingPresenter(AccountSettingView accountSettingView) {
        this.accountSettingView = accountSettingView;
        this.userModel = new UserModel();
    }


    public void fillUserDataToEditView() {
        String userId = "3lxkrwmfj4qEV9WowcSX";
        userModel.getUserById(userId, new UserModel.GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                accountSettingView.fillUserDataToEditView(user);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void updateUserData(User user) {
        userModel.updateUser(user, new UserModel.UpdateUserCallback() {
            @Override
            public void onUserUpdated(User user) {
                accountSettingView.fillUserDataToEditView(user);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void uploadImage(User user, Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();

        FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
        StorageReference storageRef = firebaseStorageHelper.getReference();
        StorageReference imagesRef = storageRef.child("images/" + user.getId() + ".jpg");
        firebaseStorageHelper.uploadImageAndGetDownloadUrl(byteArray, imagesRef, new FirebaseStorageHelper.UploadImageCallback() {
            @Override
            public void onUploadSuccess(Uri uri) {
                accountSettingView.onUploadSuccess(uri.toString());
            }

            @Override
            public void onUploadFailure(Exception e) {
                Log.e("AccountSettingPresenter", "onUploadFailure: ", e);
            }
        });
    }
}
