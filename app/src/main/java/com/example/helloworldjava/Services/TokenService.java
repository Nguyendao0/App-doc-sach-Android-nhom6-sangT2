package com.example.helloworldjava.Services;



import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TokenService {

    @POST("createIDToken/{id}")
    Call<String> createIDToken(@Path("id") String id);
}
