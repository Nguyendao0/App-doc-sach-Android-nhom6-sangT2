package com.example.helloworldjava;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import io.realm.RealmList;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sach s = new Sach();
        s.setTenSach("abc");
        s.setNhaXuatBan("asd");
        s.setImg("sadas");
        s.setLuotDoc_Items(null);
        s.setDanhGiaSach_Items(null);
        s.setMota("dfvbajvdjqvujevqw");

        AddSachTask addSachTask = new AddSachTask();
        addSachTask.execute(s);

        FirebaseMessaging.getInstance().subscribeToTopic("sach")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        System.out.println("dasdas");
                        if(task.isSuccessful())
                        {
                            msg = "Failed";
                            System.out.println("abasdas");
                        }
                    }
                });
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