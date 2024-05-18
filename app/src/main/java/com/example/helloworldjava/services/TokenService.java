package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TokenService {
    @GET("token/createIDToken/{id}")
    Call<String> createIDToken(@Path("id") String id);


    @POST("token/createTokenById/{token}/userID/{id}")
    Call<String> createTokenById(@Path("token") String token,@Path("id") String id);

}
