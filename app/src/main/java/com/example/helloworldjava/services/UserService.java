package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("nguoi-dung/{id}/thu-vien-sach")
    Call<List<ThuVienSachCaNhan>> findAll(@Path("id") String id);

    @DELETE("nguoi-dung/{idUser}/thu-vien-sach/{idSach}")
    Call<Void> deleteSach(@Path("idUser")String idUser, @Path("idSach")String idSach);
}
