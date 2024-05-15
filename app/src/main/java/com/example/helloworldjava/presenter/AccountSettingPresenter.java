package com.example.helloworldjava.presenter;

import static java.security.AccessController.getContext;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.example.helloworldjava.model.FirebaseStorageHelper;
import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.NguoiDungService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.user.AccountSettingView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSettingPresenter {
    private final UserModel userModel;
    private final AccountSettingView accountSettingView;
    private FirebaseAuthManager firebaseAuthManager;

    private NguoiDungService nguoiDungService = ServiceBuilder.buildService(NguoiDungService.class);


    public AccountSettingPresenter(AccountSettingView accountSettingView) {
        this.accountSettingView = accountSettingView;
        this.userModel = new UserModel();
    }


    public void fillUserDataToEditView() {
        String userId;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Lấy id người dùng từ Firebase
            userId = user.getUid();

        }
        else{
            userId = "fYw3HzyQGqdME6zPzDF6YSXPtPu1";
        }


        nguoiDungService.getNguoiDungById(userId).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful()) {
                    NguoiDung nguoiDung = response.body();
                    accountSettingView.fillUserDataToEditView(nguoiDung);
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable throwable) {

            }
        });
    }

    public void updateUserData(NguoiDung nguoiDung) {
        NguoiDungService nguoiDungService = ServiceBuilder.buildService(NguoiDungService.class);
        nguoiDungService.updateNguoiDung(nguoiDung.getId(), nguoiDung).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful()) {
                    NguoiDung nguoiDung1 = response.body();
                    accountSettingView.fillUserDataToEditView(nguoiDung1);
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable throwable) {

            }
        });
    }

    public void uploadImage(NguoiDung nguoiDung, Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();

        FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
        StorageReference storageRef = firebaseStorageHelper.getReference();
        StorageReference imagesRef = storageRef.child("images/users/" + nguoiDung.getId() + " .jpg");
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
