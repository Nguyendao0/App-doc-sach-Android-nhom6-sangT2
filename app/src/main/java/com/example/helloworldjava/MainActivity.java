package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.Realm.DAO.ChuongDAO;
import com.example.helloworldjava.model.Realm.DAO.NguoiDungDAO;
import com.example.helloworldjava.model.Realm.DAO.SachDAO;
import com.example.helloworldjava.model.Realm.DanhDauChuong;
import com.example.helloworldjava.model.Realm.DanhGiaSach;
import com.example.helloworldjava.model.Realm.NguoiDung;
import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.model.Realm.TheLoaiSach;

import io.realm.RealmList;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SachDAO sd = new SachDAO();
        Sach s = new Sach();

        Chuong c = new Chuong();
        c.setNoiDung("zzzaaaaaaaaaaaaaaaaaaa");
        c.setTenChuong("2");
        RealmList r = new RealmList<Chuong>();
        r.add(c);


        s.setChuong_Items(r);
        s.setDanhGiaSach_Items(null);
        s.setLuotDoc_Items(null);
        s.setImg("png");
        s.setNamXuatBan(2022);
        s.setNhaXuatBan("xuân đồng");
        s.setTenSach("clb wibi");




        AddSachTask at = new AddSachTask();
        at.execute(s);
    }

    private class AddSachTask extends AsyncTask<Sach, Void, Void> {

        @Override
        protected Void doInBackground(Sach... sach) {
            SachDAO sd = new SachDAO();
            sd.add(sach[0]);

            for(Sach s : sd.getAll())
            {
                System.out.println(s.toString());
                for (Chuong c: s.getChuong_Items())
                {
                    System.out.println(c.toString());
                }
            }
            return null;
        }
    }




}