package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("nguoi-dung/{id}/thu-vien-sach")
    Call<List<ThuVienSachCaNhan>> findAll(@Path("id") String id);

    @POST("nguoi-dung/{idUser}/thu-vien-sach/{idSach}")
    Call<ThuVienSachCaNhan> addSachToThuVienSach(@Path("idUser") String idUser, @Path("idSach") String idSach);

}
