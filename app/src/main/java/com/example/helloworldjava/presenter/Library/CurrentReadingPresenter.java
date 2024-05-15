package com.example.helloworldjava.presenter.Library;

import android.content.Context;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.UserService;
import com.example.helloworldjava.model.Realm.DAO.SachDAO;
import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;
import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.services.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentReadingPresenter implements CurrentReadingContract.Presenter {
    private CurrentReadingContract.View CurrentReadingView;
    private  SachService sachService;
    private UserService userService;
    private Context context;

    public CurrentReadingPresenter(CurrentReadingContract.View currentReadingView, Context context) {
        CurrentReadingView = currentReadingView;
        sachService = ServiceBuilder.buildService(SachService.class);
        userService = ServiceBuilder.buildService(UserService.class);
        this.context = context;
    }

    @Override
    public void readSach() {
        FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager(context);
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        Call<List<ThuVienSachCaNhan>> request = userService.findAll(idNguoiDung);
        request.enqueue(new Callback<List<ThuVienSachCaNhan>>() {
            @Override
            public void onResponse(Call<List<ThuVienSachCaNhan>> call, Response<List<ThuVienSachCaNhan>> response) {
                List<ThuVienSachCaNhan> thuVienSachCaNhanList = response.body();
                ArrayList< com.example.helloworldjava.model.Realm.Sach> listSach = new ArrayList<>();
                for (ThuVienSachCaNhan tvs: thuVienSachCaNhanList)
                {
                    listSach.add(tvs.getSach());
                }
                CurrentReadingView.setSachList((ArrayList<Sach>) listSach);
            }

            @Override
            public void onFailure(Call<List<ThuVienSachCaNhan>> call, Throwable throwable) {
                System.out.println("Lỗi main" + throwable.getMessage());
            }
        });


    }


    @Override
    public void readSachOffline() {

        ArrayList<Sach> listsach = new ArrayList<>();
        SachDAO sachDAO = new SachDAO();
        for (Sach s : sachDAO.getAll())
        {
            listsach.add(s);
        }
        CurrentReadingView.setSachOfflineList(listsach);

    }
}