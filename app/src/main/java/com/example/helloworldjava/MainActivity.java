package com.example.helloworldjava;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworldjava.Services.ServiceBuilder;
import com.example.helloworldjava.Services.UserService;

import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.presenter.SachPresenter;
import com.example.helloworldjava.presenter.TheLoaiSachPresenter;
import com.example.helloworldjava.view.Account_Login;
import com.example.helloworldjava.view.Account_Register;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.view.HomeActivity;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.Account_Login;
import com.example.helloworldjava.view.Account_Register;
import com.example.helloworldjava.view.QRGen;
import com.example.helloworldjava.view.Search.SearchActivity;
import com.example.helloworldjava.view.Thongbao.NoitificationActivity;
import com.example.helloworldjava.view.ReadBookActivity;
import com.example.helloworldjava.view.SpeechBookTest.SpeechActivity;
import com.example.helloworldjava.view.user.UserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MainActivity" ,"Hello world");

        UserService userService = ServiceBuilder.buildService(UserService.class);
        Call<User> request = userService.getNguoiDungById("LTN");


        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println(user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                System.out.println("Lỗi main" + throwable.getMessage());
            }
        });


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
    public void ThongBao(View view) {

        Intent intent = new Intent(this, NoitificationActivity.class);
        startActivity(intent);
    }
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