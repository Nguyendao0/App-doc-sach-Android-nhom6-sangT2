package com.example.helloworldjava.model.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.helloworldjava.model.entity.Sach;

import java.util.List;

@Dao
public interface  SachDAO {

    @Query("SELECT * FROM Sach")
    List<Sach> getAllSach();

    @Insert
    void insertSach(Sach sach);

}
