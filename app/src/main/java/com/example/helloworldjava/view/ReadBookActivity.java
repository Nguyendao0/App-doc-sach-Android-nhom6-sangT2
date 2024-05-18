package com.example.helloworldjava.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.view.SpeechBookTest.SpeechActivity;
import com.example.helloworldjava.view.dangtruyen.DangChuongActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadBookActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private Sach sach;
    private SachService sachService = ServiceBuilder.buildService(SachService.class);
    private Menu menu;
    private FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager(this);
    private Chuong chuong;
    private ImageView imageViewDanhDauChuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        // setToolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đọc truyện");

        imageViewDanhDauChuong = findViewById(R.id.imageViewDanhDauChuong);

//        TextView tv_TenChuong = findViewById(R.id.tv_tenchuong);
        TextView tv_noidungChuong = findViewById(R.id.tv_noidungChuong);
//        TextView tv_sochuong = findViewById(R.id.tvchuong);
        Intent intent =  getIntent();
        String idSach = intent.getStringExtra("idSach");
        String idChuong = intent.getStringExtra("idChuong");
        String tensach = intent.getStringExtra("TenSach");

        sachService.getSach(idSach).enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                sach = response.body();
                if (sach.getIdNguoiDung().equals(firebaseAuthManager.getCurrentUser().getUid())) {
                    menu.findItem(R.id.action_sua_chuong).setVisible(true);
                    menu.findItem(R.id.action_xoa_chuong).setVisible(true);
                }
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable t) {
                t.printStackTrace();
            }
        });

        checkSavedFont(tv_noidungChuong);
        setFontSizeFromSharedPreferences(tv_noidungChuong);

        View btnDSChuong = findViewById(R.id.btnDSChuong);
        btnDSChuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(ReadBookActivity.this, DanhsachchuongActivity.class);
                myintent.putExtra("idSach", idSach);
                startActivity(myintent);
            }
        });

        ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
        Call<Chuong> request = chuongService.getChuong(idChuong, firebaseAuthManager.getCurrentUser().getUid());

        request.enqueue(new Callback<Chuong>() {
            @Override
            public void onResponse(@NonNull Call<Chuong> call, @NonNull Response<Chuong> response) {
                chuong = response.body();
                System.out.println(chuong);
//                tv_TenChuong.setText(chuong.getTenChuong());
                tv_noidungChuong.setText(chuong.getNoiDung());
//                tv_sochuong.setText("Chương " + chuong.getSoThuTu() + ":");
                getSupportActionBar().setTitle("CHƯƠNG " + chuong.getSoThuTu() + ": " + chuong.getTenChuong().toUpperCase());
                if (chuong.isDanhDau()) {
                    imageViewDanhDauChuong.setImageResource(R.drawable.star);
                }
            }

            @Override
            public void onFailure(Call<Chuong> call, Throwable throwable) {

            }
        });

        ImageView imageViewComment = findViewById(R.id.imageView_comment);
        ImageView imageViewSpeech = findViewById(R.id.imageView_Speech);
        imageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ReadBookActivity.this, CommentActivity.class);
                ReadBookActivity.this.startActivity(myIntent);
            }
        });

        imageViewSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ReadBookActivity.this, SpeechActivity.class);
                myIntent.putExtra("TenSach", tensach);
                myIntent.putExtra("TenChuong", chuong.getTenChuong());
                myIntent.putExtra("NoiDung", tv_noidungChuong.getText());

                ReadBookActivity.this.startActivity(myIntent);
            }
        });

        ImageView imgSetting = findViewById(R.id.img_seting);
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingDialog(tv_noidungChuong);
            }
        });

        imageViewDanhDauChuong.setOnClickListener(v -> {
            danhDauChuong();
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_sua_chuong){
            // Go to DangChuongActivity
            Intent intent = new Intent(ReadBookActivity.this, DangChuongActivity.class);
            intent.putExtra("idSach", sach.getId());
            intent.putExtra("idChuong", chuong.getId());
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.action_xoa_chuong){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            // Delete chuong
                            ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
                            Call<Void> request = chuongService.deleteChuong(chuong.getId());
                            request.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(ReadBookActivity.this, "Xóa chương thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có thật sự muốn xóa chương này không?").setPositiveButton("Có", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_readbook, menu);
        menu.findItem(R.id.action_sua_chuong).setVisible(false);
        menu.findItem(R.id.action_xoa_chuong).setVisible(false);
        return true;
    }

    private void showSettingDialog(TextView tv_noidungChuong) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cài đặt");

        // Tạo layout cho hộp thoại
        View view = getLayoutInflater().inflate(R.layout.dialog_setting, null);
        builder.setView(view);

        // Tìm các view trong layout
        SeekBar seekBarFontSize = view.findViewById(R.id.seekBar_fontSize);
        Spinner spinnerFontFamily = view.findViewById(R.id.spinner_fontFamily);

        // Thiết lập giá trị mặc định cho các view

        // Thiết lập sự kiện khi người dùng thay đổi cỡ chữ
        int defaultValue = 25; // Giá trị mặc định (25sp)
        int minValue = 0; // Giá trị tối thiểu của SeekBar
        int maxValue = 100; // Giá trị tối đa của SeekBar

        int progress = (int) (((float) defaultValue - minValue) / (maxValue - minValue) * (seekBarFontSize.getMax() - seekBarFontSize.getMin())) + seekBarFontSize.getMin();
        seekBarFontSize.setProgress(progress);
        seekBarFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Thay đổi cỡ chữ cho TextView
                // Tính toán giá trị kích thước chữ tương ứng
                int minValue = 10; // Giá trị tối thiểu của SeekBar (10sp)
                int maxValue = 100; // Giá trị tối đa của SeekBar (100sp)
                int range = maxValue - minValue; // Phạm vi giá trị của SeekBar

                float fontSize = (float) (minValue + progress * (range / (float) seekBar.getMax())); // Tính toán kích thước chữ
                tv_noidungChuong.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);

                SharedPreferences sharedPreferences = getSharedPreferences("fontSize", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("selectedFontSize", fontSize);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        // Thiết lập sự kiện khi người dùng thay đổi font chữ
        String[] fontFamilies = {"Alex brush", "Basic","Bad script"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fontFamilies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFontFamily.setAdapter(adapter);
        spinnerFontFamily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Thay đổi font chữ cho TextView
                String fontFamily = parent.getItemAtPosition(position).toString();
                if(fontFamily.equals("Bad script")){
                    savefont(tv_noidungChuong, "bad_script", "Bad script" );
                }
                if(fontFamily.equals("Basic")){
                    savefont(tv_noidungChuong, "annie_use_your_telescope", "Basic" );
                }
                if(fontFamily.equals("Alex brush")){
                    savefont(tv_noidungChuong, "alex_brush", "Alex brush" );
                }

                Log.d("FontChange", "Đã chọn font chữ: " + fontFamily);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Thiết lập nút "Đồng ý" trong hộp thoại
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng ấn nút "Đồng ý"
            }
        });

        // Thiết lập nút "Hủy" trong hộp thoại
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng ấn nút "Hủy"
                // ...
            }
        });

        // Hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void savefont(TextView tv_noidungChuong , String font, String fontname){
        // Lưu thông tin font vào Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedFontFamily", fontname);
        editor.apply();

        // Lưu thông tin font vào Shared Preferences
        SharedPreferences sharedPreferencesfont = getSharedPreferences("fonts", MODE_PRIVATE);
        SharedPreferences.Editor editorf = sharedPreferencesfont.edit();
        editorf.putString("selectedFontName", font);
        editorf.apply();

        int fontResourceId = getResources().getIdentifier(font, "font", getPackageName());
        if (fontResourceId != 0) {
            Typeface typeface = ResourcesCompat.getFont(ReadBookActivity.this, fontResourceId);
            tv_noidungChuong.setTypeface(typeface);
        }
    }

    public void checkSavedFont(TextView v) {
        // Truy xuất thông tin font từ Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String selectedFontFamily = sharedPreferences.getString("selectedFontFamily", null);

        SharedPreferences sharedPreferencesFont = getSharedPreferences("fonts", MODE_PRIVATE);
        String selectedFontName = sharedPreferencesFont.getString("selectedFontName", null);

        if (selectedFontFamily != null && selectedFontName != null) {
            // Kiểm tra thông tin font đã lưu
            Log.d("Font", "Selected Font Family: " + selectedFontFamily);
            Log.d("Font", "Selected Font Name: " + selectedFontName);
            savefont(v, selectedFontName, selectedFontFamily );
        } else {
            // Xử lý trường hợp không có dữ liệu font được lưu trữ
        }
    }
    // Thiết lập kích thước font từ Shared Preferences
    public void setFontSizeFromSharedPreferences(TextView textView) {
        // Truy xuất thông tin kích thước font từ Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("fontSize", MODE_PRIVATE);
        float fontSize = sharedPreferences.getFloat("selectedFontSize", 0);

        if (fontSize != 0) {
            // Thiết lập kích thước font cho TextView
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void danhDauChuong() {
        // Call API danhDauChuong
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        String idChuong = chuong.getId();
        if (chuong.isDanhDau()) {
            ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
            chuongService.boDanhDauChuong(idChuong, idNguoiDung).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(ReadBookActivity.this, "Bỏ đánh dấu chương thành công", Toast.LENGTH_SHORT).show();
                    imageViewDanhDauChuong.setImageResource(R.drawable.star_outline);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            return;
        }


        ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
        chuongService.danhDauChuong(idChuong, idNguoiDung).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ReadBookActivity.this, "Đánh dấu chương thành công", Toast.LENGTH_SHORT).show();
                imageViewDanhDauChuong.setImageResource(R.drawable.star);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}