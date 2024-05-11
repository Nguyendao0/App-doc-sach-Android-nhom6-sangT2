package com.example.helloworldjava.API;
import com.example.helloworldjava.APIEntities.ModelString;
import com.example.helloworldjava.Config.ConfigData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
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
