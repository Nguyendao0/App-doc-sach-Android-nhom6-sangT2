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
        c.setNoiDung("zzzaa");
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


        NguoiDung n = new NguoiDung();
        n.setTenNguoiDung("fasoihoang");


        AddSachTask at = new AddSachTask();
        at.execute(s);
        Add1Task and = new Add1Task();
        and.execute(n);
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


    private class Add1Task extends AsyncTask<NguoiDung, Void, Void> {

        @Override
        protected Void doInBackground(NguoiDung... nguoiDungs) {
            NguoiDungDAO nd = new NguoiDungDAO();
            DanhDauChuong dc = new DanhDauChuong();
            DanhDauChuong dc1 = new DanhDauChuong();
            SachDAO sd = new SachDAO();
            dc.setSach(sd.getById(2));
            dc1.setSach(sd.getById(3));

            RealmList r = new RealmList<DanhDauChuong>();
            r.add(dc);
            r.add(dc1);
            nguoiDungs[0].setDanhDauChuong_Items(r);

            nd.add(nguoiDungs[0]);

            for(NguoiDung n : nd.getAll())
            {
                System.out.println(n.toString());
                for(DanhDauChuong d : n.getDanhDauChuong_Items())
                {
                    System.out.println(d.toString());
                    System.out.println(d.getSach().toString());
                }
            }
            return null;
        }
    }

    private class Add2Task extends AsyncTask<TheLoaiSach, Void, Void> {

        @Override
        protected Void doInBackground(TheLoaiSach... theLoaiSaches) {

            return null;
        }
    }
}