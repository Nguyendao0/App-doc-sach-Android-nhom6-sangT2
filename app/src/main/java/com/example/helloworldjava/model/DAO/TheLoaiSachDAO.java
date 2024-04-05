package com.example.helloworldjava.model.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.helloworldjava.model.entity.TheLoaiSach;

import java.util.List;

@Dao
public interface TheLoaiSachDAO {
    @Query("SELECT * FROM TheLoaiSach")
    List<TheLoaiSach> getAllTheLoaiSach();

    @Insert
    void insertTheLoaiSach(TheLoaiSach theLoaiSach);
    // Các phương thức khác cho các thao tác khác như cập nhật, xóa, v.v.
}
