package com.example.helloworldjava.services.API;
import com.example.helloworldjava.model.APIEntities.ModelString;
import com.example.helloworldjava.Config.ConfigData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    AccountService api = new Retrofit.Builder()
            .baseUrl("http://"+ ConfigData.IP +":7777/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AccountService.class);
    @GET("create/newaccount")
    Call<ModelString> AddNewAccount(@Body ModelString modelString);
    @POST("login")
    Call<ModelString> Login(@Body ModelString modelString);
    @POST("autologin")
    Call<ModelString> AutoLogin(@Body ModelString modelString);
    @POST("logout")
    Call<ModelString> Logout(@Body ModelString modelString);
}
