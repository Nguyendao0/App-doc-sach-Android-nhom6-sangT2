package com.example.helloworldjava;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.TokenService;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.view.home.HomeActivity;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.login.Account_Login;
import com.example.helloworldjava.view.register.Account_Register;
import com.example.helloworldjava.view.QRGen;
import com.example.helloworldjava.view.Search.SearchActivity;
import com.example.helloworldjava.view.ReadBookActivity;
import com.example.helloworldjava.view.SpeechBookTest.SpeechActivity;
import com.example.helloworldjava.view.login.WebViewGoogleActivity;
import com.example.helloworldjava.view.user.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private void findAllNotification()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<List<NotificationFCM>> request = notificationService.findAllNotificationById("zed");
        request.enqueue(new Callback<List<NotificationFCM>>() {
            @Override
            public void onResponse(Call<List<NotificationFCM>> call, Response<List<NotificationFCM>> response) {
                if (response.isSuccessful()) {
                    List<NotificationFCM> notis = response.body();

                    for (NotificationFCM n:notis)
                    {
                        System.out.println(notis.toString());
                    }
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NotificationFCM>> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }


    private void sendNotificationToFB()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        NotificationFCM notificationFCM = new NotificationFCM();
        notificationFCM.setContent("yasuasdaso");
        notificationFCM.setTitle("milkitsadasa");

        Call<String> request = notificationService.createNotificationById("zed", notificationFCM);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String thongbao = response.body();
                    System.out.println("response: " + thongbao);
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }

    private void sendNotificaction()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<String> request = notificationService.createIDNotification("zed");

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String thongbao = response.body();
                    System.out.println("response: " + thongbao);
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }

    private void sendTokenToFCM() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", this.MODE_PRIVATE);
        boolean isSettoken = sharedPreferences.getBoolean("isSettoken", false);

        if (!isSettoken) {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("Fetching FCM registration token failed: " + task.getException());
                        return;
                    }

                    String token = task.getResult();

                    TokenService tokenService = ServiceBuilder.buildService(TokenService.class);
                    Call<String> request = tokenService.createTokenById(token, "zed");

                    request.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                String thongbao = response.body();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isSettoken", true); // Lưu giá trị true khi token đã được thiết lập
                                editor.apply();
                                System.out.println("response: " + thongbao);
                            } else {
                                System.out.println("Response failed: " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable throwable) {
                            System.out.println("Error: " + throwable.getMessage());
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sendNotificaction();
//        findAllNotification();
        sendTokenToFCM();
//        sendNotificationToFB();

        Button button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MainActivity" ,"Hello world");

//         tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    // Set language (US English in this case)
//                    tts.setLanguage(Locale.US);
//                    String text = "Hello, this is a sample text.";
//                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//
//                } else {
//                    Log.e("MainActivity", "TTS initialization failed");
//                }
//            }
//        });


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


//        Button btnReadBook = findViewById(R.id.btbReadBook);
//        btnReadBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(MainActivity.this, ReadBookActivity.class);
//                startActivity(myIntent);
//            }
//        });

        Button btnReadBook = findViewById(R.id.btbReadBook);
        btnReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ReadBookActivity.class);
                startActivity(myIntent);
            }
        });
        Button btnopenWebview = findViewById(R.id.btn_openwebview);
        btnopenWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, WebViewGoogleActivity.class);
                startActivity(myIntent);
            }
        });

        Button btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Account_Login.class);
                startActivity(myIntent);
            }
        });

        Button btnQRGen = findViewById(R.id.btnQRGen);
        btnQRGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, QRGen.class);
                startActivity(myIntent);
            }
        });




//        theLoaiSachPresenter = new TheLoaiSachPresenter(database.theLoaiSachDAO());

//        // Thêm sách và thể loại sách vào cơ sở dữ liệu
//        Sach sach = new Sach();
//        sach.idTheLoaiSach = 1; // Đặt id thể loại sách tùy ý
//        sach.urlImage = "url_image";
//        sach.TenSach = "Tên sách";
//        sach.TacGia = "Tác giả";
//        sach.NhaSanXuat = "Nhà sản xuất";
//        sach.NamSanXuat = "Năm sản xuất";
//        sach.MoTa = "Mô tả";
//        sach.DanhGiaSach = 5; // Đánh giá sách
//        sachPresenter.addSach(sach);
//
//        TheLoaiSach theLoaiSach = new TheLoaiSach();
//        theLoaiSach.TenTheLoai = "Tên thể loại";
//        theLoaiSach.MoTaTheLoai = "Mô tả thể loại";
//        theLoaiSachPresenter.addTheLoaiSach(theLoaiSach);

        // Hiển thị danh sách sách và thể loại sách trong giao diện người dùng
//        List<Sach> sachList = sachPresenter.getAllSach();
//        //List<TheLoaiSach> theLoaiSachList = theLoaiSachPresenter.getAllTheLoaiSach();
//
//        // Hiển thị tên sách trên log
//        for (Sach sach1 : sachList) {
//            Log.d("MainActivity", "Tên sách: " + sach1.TenSach);
//        }
    }

    // tái sử chuyển activity
    public void goToActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public  void gotoLogin(View view ){
        Intent intent = new Intent(this, Account_Login.class);
        startActivity(intent);
    }

    public  void gotoQRGen(View view ){
        Intent intent = new Intent(this, QRGen.class);
        startActivity(intent);
    }

    public  void gotoSearch(View view ){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public  void gotoSpeech(View view ){
        Intent intent = new Intent(this, SpeechActivity.class);
        startActivity(intent);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToGioiThieuSach(View view) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        startActivity(intent);
    }
    //goToSignin
    public void goToSignup(View view) {
        Intent intent = new Intent(this, Account_Register.class);
        startActivity(intent);
    }
//    public void ThongBao(View view) {
//
//        Intent intent = new Intent(this, NoitificationActivity.class);
//        startActivity(intent);
//    }
//    public void GoToLibraryActivity(View view)
//    {
//        Intent intent = new Intent(this, LibraryActivity.class);
//        startActivity(intent);
//    }
//    public void goToPDF(View view)
//    {
//        Intent intent = new Intent(this, TESTGETPDFActivity.class);
//        startActivity(intent);
//    }



//    protected void runArrayList(){
//        CatergorySearch catergorySearch = new CatergorySearch();
//        HistorySearch historySearch = new HistorySearch();
//
//        Spinner spinner;
//        spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,catergorySearch.getArrayCatergory());
//        spinner.setAdapter(arrayAdapter);
//
//        ListView listView;
//        listView = (ListView) findViewById(R.id.listBook);
//        AdapterListBook adapterListBook = new AdapterListBook(this, R.layout.modelbook,historySearch.getArraySach());
//        listView.setAdapter(adapterListBook);
//    }


}