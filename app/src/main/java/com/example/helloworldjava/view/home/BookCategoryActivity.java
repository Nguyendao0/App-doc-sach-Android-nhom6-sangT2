package com.example.helloworldjava.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworldjava.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookCategoryActivity extends AppCompatActivity implements BookCategoryRecyclerViewAdapter.ItemClickListener {
    BookCategoryRecyclerViewAdapter adapter;
    int numberOfColumns = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);

        ImageView btnBackHome = findViewById(R.id.iconBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // data to populate the RecyclerView with
        List<BookCategory> listBookCategory = new ArrayList<>(Arrays.asList(
                new BookCategory("Sách Văn Học", R.drawable.image_lap_trinh_android),
                new BookCategory("Sách Kinh Tế", R.drawable.image_30s_ai_va_robot_hoc),
                new BookCategory("Sách Thiếu Nhi", R.drawable.image_giet_con_chim_nhai),
                new BookCategory("Sách Kỹ Năng Sống", R.drawable.image_ai_lay_mieng_pho_mat_cua_toi),
                new BookCategory("Sách Mẹ Và Bé", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Giáo Khoa - Giáo Trình", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Học Ngoại Ngữ", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Tham Khảo", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Từ Điển", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Kiến Thức Tổng Hợp", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Khoa Học - Kỹ Thuật", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Lịch Sử", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Điện Ảnh - Nhạc - Hoạ", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Truyện Tranh - Manga - Comic", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Tôn Giáo - Tâm Linh", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Văn Hoá - Địa Lý - Du Lịch", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Chính Trị - Pháp Lý", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Nông - Lâm - Ngư Nghiệp", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Công Nghệ Thông Tin", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Y Học", R.drawable.image_biet_va_thay),
                new BookCategory("Tạp Chí - Catalogue", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Tâm Lý - Giới Tính", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Thường Thức - Gia Đình", R.drawable.image_biet_va_thay),
                new BookCategory("Sách Thể Dục - Thể Thao", R.drawable.image_biet_va_thay)
        ));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_book_category);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new BookCategoryRecyclerViewAdapter(this, listBookCategory);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ListBooksActity.class);
        intent.putExtra("titleListBooks", adapter.getItem(position).getTitle().toUpperCase());
        startActivity(intent);
    }
}