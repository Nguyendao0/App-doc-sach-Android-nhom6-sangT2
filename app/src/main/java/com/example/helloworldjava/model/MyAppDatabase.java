package com.example.helloworldjava.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.helloworldjava.model.DAO.SachDAO;
import com.example.helloworldjava.model.DAO.TheLoaiSachDAO;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.model.entity.TheLoaiSach;

@Database(entities = {Sach.class, TheLoaiSach.class}, version = 1)
public abstract class  MyAppDatabase extends RoomDatabase {
    public abstract SachDAO sachDAO();
    public abstract TheLoaiSachDAO theLoaiSachDAO();
}
