package com.example.helloworldjava.presenter.Library;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
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

    public CurrentReadingPresenter(CurrentReadingContract.View currentReadingView) {
        CurrentReadingView = currentReadingView;
        sachService = ServiceBuilder.buildService(SachService.class);
        userService = ServiceBuilder.buildService(UserService.class);
    }

    @Override
    public void readSach() {

        Call<List<ThuVienSachCaNhan>> request = userService.findAll("qRZuZFlnE6jxJcdP6HRk");
        request.enqueue(new Callback<List<ThuVienSachCaNhan>>() {
            @Override
            public void onResponse(Call<List<ThuVienSachCaNhan>> call, Response<List<ThuVienSachCaNhan>> response) {

                List<ThuVienSachCaNhan> thuVienSachCaNhanList = response.body();
                ArrayList<Sach> listSach = new ArrayList<>();
                for (ThuVienSachCaNhan tvs: thuVienSachCaNhanList)
                {
                    System.out.println(tvs.getSach().getImg());
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