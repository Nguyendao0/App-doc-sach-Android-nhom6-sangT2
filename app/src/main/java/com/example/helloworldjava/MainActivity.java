package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.*;

import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.Library.LibraryActivity;
import com.example.helloworldjava.Library.TESTGETPDFActivity;
import com.example.helloworldjava.Menu.MenuActivity;
import com.example.helloworldjava.Thongbao.Noitification;
import com.example.helloworldjava.view.Account_Login;
import com.example.helloworldjava.view.Account_Register;
import com.example.helloworldjava.view.ReadBookActivity;
import com.example.helloworldjava.view.UserActivity;

public class MainActivity extends AppCompatActivity {
    //this is van phuoc
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


    }


    public  void gotoLogin(View view ){
        Intent intent = new Intent(this, Account_Login.class);
        startActivity(intent);
    }

    public  void gotoSearch(View view ){
        Intent intent = new Intent(this, SearchActivity.class);
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
    public void goToSignin(View view) {
        Intent intent = new Intent(this, Account_Register.class);
        startActivity(intent);
    }
    public void ThongBao(View view) {

        Intent intent = new Intent(this, Noitification.class);
        startActivity(intent);
    }
    public void GoToLibraryActivity(View view)
    {
        Intent intent = new Intent(this, LibraryActivity.class);
        startActivity(intent);
    }
    public void goToPDF(View view)
    {
        Intent intent = new Intent(this, TESTGETPDFActivity.class);
        startActivity(intent);
    }



    protected void runArrayList(){
        CatergorySearch catergorySearch = new CatergorySearch();
        HistorySearch historySearch = new HistorySearch();

        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,catergorySearch.getArrayCatergory());
        spinner.setAdapter(arrayAdapter);

        ListView listView;
        listView = (ListView) findViewById(R.id.listBook);
        AdapterListBook adapterListBook = new AdapterListBook(this, R.layout.modelbook,historySearch.getArrayBook());
        listView.setAdapter(adapterListBook);
    }


}