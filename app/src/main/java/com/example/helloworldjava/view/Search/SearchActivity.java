package com.example.helloworldjava.view.Search;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.google.firebase.Firebase;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    GridView gridView;
    AdapterListBook adapterListBook;
    EditText editText;
    Button btn;
    List<Sach> listSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        //getListSachFromFirebase();
        anhXa();
        fetchData();
        setUp();
        setClick();

    }
    private void init(){
        listSach = new ArrayList<>();

        listSach.add(new Sach("TỰ CẨM","Đông Thiên Đích Liễu Diệp","https://lh3.googleusercontent.com/pw/AIL4fc-BV58s_mFbXosakwhGx7lqwtD4g9zQ7_TLjLkSvx5zejJ2hzpbr6oWKD8LPs0NLJCwZpdLy_Rr5e7aDX6Q0PCAruHj8V6oh7J8K7v5AxQKbdkvANhGuafTrUUsS8hNSeHjX3rHXUF4drVko4g2rWBq=w215-h322-s-no?authuser=0"));
        listSach.add(new Sach("LINH VŨ THIÊN HẠ","Vũ Phong","https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg"));
        listSach.add(new Sach("KHÔNG PHỤ THÊ DUYÊN","Vụ Thỉ Dực","https://static.8cache.com/cover/eJzLyTDWN3SrKg53L8nIC9HVDQ4O86o0NDILK7N0dQrNNTNwKsvIMjMwC9A1N_bMTcvzyXf1rXIpNfPzSI0PcsvNLw0Mdk4MiUiMTC2oCDKMz6zwLPH3dLQtNzI01c0wNjICAM9tHpk=/khong-phu-the-duyen.jpg"));
        listSach.add(new Sach("KIỀU SỦNG VI THƯỢNG","Diệp Kiến Tinh","https://static.8cache.com/cover/eJzLyTDWTw2ICiwrDHatNMx3D65I0fWxsPRMdPMLznMJzXPPCMoqDMkwT_bLy_IKdEr193RPy67IN092THI1Skp087XMLQ4zy85M8cxOck3zNMt3NzEJDbQtNzI01c0wNjICABeeHyk=/kieu-sung-vi-thuong.jpg"));
        listSach.add(new Sach("ANH ĐÀO HỔ PHÁCH","Vân Trụ","https://static.8cache.com/cover/eJwFwbsOQDAUANAvanApNRjQYPBoRG7F1qi3GAzo3zvnXF1r8Rsi8JO8aS81Z7d51B7U0hZbjuyoRpP0oAULtaymsrgV9MBnm_J4oq0RQ3elCA6y7sj9r8CQePBGLziUrC7AD_z0Hfw=/anh-dao-ho-phach.jpg"));
        listSach.add(new Sach("VỢ CHỒNG SIÊU SAO HƠI NGỌT","Đồ Dạng Tiên Sâm","https://static.8cache.com/cover/eJzLyTDWt_Q3CfX31Q0pyDf3zC93SinIjTSqzC5P9DGO9ArJC8mwzHTTzfNIMywJSY8qDA2PjIoKSU5KLcoqrjAvy45IdgzJ8XXOS3cvtKwwNy0O8Q50SrctNzI01c0wNjICACXjIAA=/vo-chong-sieu-sao-hoi-ngot.jpg"));
        listSach.add(new Sach("THIẾU TƯỚNG, VỢ NGÀI NỔI GIẬN RỒI","Ngoc","https://static.8cache.com/cover/eJwFwcEOQCAAANAvMlOsk4NMtmZyYbi0EkIsYwtf7z2joZ8DXtXe3EjLSJmRc9lUn9vm0xoxzDlhprLdAfkbFjeW6jNOpXMSDPtKoXuu1gmBorpEo9kRncQZOxBEnoYA_O9UH4k=/thieu-tuong-vo-ngai-noi-gian-roi-543143.jpg"));
        listSach.add(new Sach("MÊ ĐẮM","Kim Họa","https://static.8cache.com/cover/eJzLyTDWD8o2Cyspda_MTwuxKPFNzjGuKDJ0LikKCTELy0538c4Ny3Uxr_QI8E7Oz3JJswwscCvMN3MJ8Cr09QuqKEpxL_EN9gwKTa0ydvFy9zcyKU7JTbctNzI01c0wNjICAERyH_0=/me-dam.jpg"));
        listSach.add(new Sach("TỰ CẨM","Đông Thiên Đích Liễu Diệp","https://lh3.googleusercontent.com/pw/AIL4fc-BV58s_mFbXosakwhGx7lqwtD4g9zQ7_TLjLkSvx5zejJ2hzpbr6oWKD8LPs0NLJCwZpdLy_Rr5e7aDX6Q0PCAruHj8V6oh7J8K7v5AxQKbdkvANhGuafTrUUsS8hNSeHjX3rHXUF4drVko4g2rWBq=w215-h322-s-no?authuser=0"));
        listSach.add(new Sach("LINH VŨ THIÊN HẠ","Vũ Phong","https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg"));
        listSach.add(new Sach("KHÔNG PHỤ THÊ DUYÊN","Vụ Thỉ Dực","https://static.8cache.com/cover/eJzLyTDWN3SrKg53L8nIC9HVDQ4O86o0NDILK7N0dQrNNTNwKsvIMjMwC9A1N_bMTcvzyXf1rXIpNfPzSI0PcsvNLw0Mdk4MiUiMTC2oCDKMz6zwLPH3dLQtNzI01c0wNjICAM9tHpk=/khong-phu-the-duyen.jpg"));
        listSach.add(new Sach("KIỀU SỦNG VI THƯỢNG","Diệp Kiến Tinh","https://static.8cache.com/cover/eJzLyTDWTw2ICiwrDHatNMx3D65I0fWxsPRMdPMLznMJzXPPCMoqDMkwT_bLy_IKdEr193RPy67IN092THI1Skp087XMLQ4zy85M8cxOck3zNMt3NzEJDbQtNzI01c0wNjICABeeHyk=/kieu-sung-vi-thuong.jpg"));
        listSach.add(new Sach("ANH ĐÀO HỔ PHÁCH","Vân Trụ","https://static.8cache.com/cover/eJwFwbsOQDAUANAvanApNRjQYPBoRG7F1qi3GAzo3zvnXF1r8Rsi8JO8aS81Z7d51B7U0hZbjuyoRpP0oAULtaymsrgV9MBnm_J4oq0RQ3elCA6y7sj9r8CQePBGLziUrC7AD_z0Hfw=/anh-dao-ho-phach.jpg"));
        listSach.add(new Sach("VỢ CHỒNG SIÊU SAO HƠI NGỌT","Đồ Dạng Tiên Sâm","https://static.8cache.com/cover/eJzLyTDWt_Q3CfX31Q0pyDf3zC93SinIjTSqzC5P9DGO9ArJC8mwzHTTzfNIMywJSY8qDA2PjIoKSU5KLcoqrjAvy45IdgzJ8XXOS3cvtKwwNy0O8Q50SrctNzI01c0wNjICACXjIAA=/vo-chong-sieu-sao-hoi-ngot.jpg"));
        listSach.add(new Sach("THIẾU TƯỚNG, VỢ NGÀI NỔI GIẬN RỒI","Ngoc","https://static.8cache.com/cover/eJwFwcEOQCAAANAvMlOsk4NMtmZyYbi0EkIsYwtf7z2joZ8DXtXe3EjLSJmRc9lUn9vm0xoxzDlhprLdAfkbFjeW6jNOpXMSDPtKoXuu1gmBorpEo9kRncQZOxBEnoYA_O9UH4k=/thieu-tuong-vo-ngai-noi-gian-roi-543143.jpg"));
        listSach.add(new Sach("MÊ ĐẮM","Kim Họa","https://static.8cache.com/cover/eJzLyTDWD8o2Cyspda_MTwuxKPFNzjGuKDJ0LikKCTELy0538c4Ny3Uxr_QI8E7Oz3JJswwscCvMN3MJ8Cr09QuqKEpxL_EN9gwKTa0ydvFy9zcyKU7JTbctNzI01c0wNjICAERyH_0=/me-dam.jpg"));
        listSach.add(new Sach("TỰ CẨM","Đông Thiên Đích Liễu Diệp","https://lh3.googleusercontent.com/pw/AIL4fc-BV58s_mFbXosakwhGx7lqwtD4g9zQ7_TLjLkSvx5zejJ2hzpbr6oWKD8LPs0NLJCwZpdLy_Rr5e7aDX6Q0PCAruHj8V6oh7J8K7v5AxQKbdkvANhGuafTrUUsS8hNSeHjX3rHXUF4drVko4g2rWBq=w215-h322-s-no?authuser=0"));
        listSach.add(new Sach("LINH VŨ THIÊN HẠ","Vũ Phong","https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg"));
        listSach.add(new Sach("KHÔNG PHỤ THÊ DUYÊN","Vụ Thỉ Dực","https://static.8cache.com/cover/eJzLyTDWN3SrKg53L8nIC9HVDQ4O86o0NDILK7N0dQrNNTNwKsvIMjMwC9A1N_bMTcvzyXf1rXIpNfPzSI0PcsvNLw0Mdk4MiUiMTC2oCDKMz6zwLPH3dLQtNzI01c0wNjICAM9tHpk=/khong-phu-the-duyen.jpg"));
        listSach.add(new Sach("KIỀU SỦNG VI THƯỢNG","Diệp Kiến Tinh","https://static.8cache.com/cover/eJzLyTDWTw2ICiwrDHatNMx3D65I0fWxsPRMdPMLznMJzXPPCMoqDMkwT_bLy_IKdEr193RPy67IN092THI1Skp087XMLQ4zy85M8cxOck3zNMt3NzEJDbQtNzI01c0wNjICABeeHyk=/kieu-sung-vi-thuong.jpg"));
        listSach.add(new Sach("ANH ĐÀO HỔ PHÁCH","Vân Trụ","https://static.8cache.com/cover/eJwFwbsOQDAUANAvanApNRjQYPBoRG7F1qi3GAzo3zvnXF1r8Rsi8JO8aS81Z7d51B7U0hZbjuyoRpP0oAULtaymsrgV9MBnm_J4oq0RQ3elCA6y7sj9r8CQePBGLziUrC7AD_z0Hfw=/anh-dao-ho-phach.jpg"));
        listSach.add(new Sach("VỢ CHỒNG SIÊU SAO HƠI NGỌT","Đồ Dạng Tiên Sâm","https://static.8cache.com/cover/eJzLyTDWt_Q3CfX31Q0pyDf3zC93SinIjTSqzC5P9DGO9ArJC8mwzHTTzfNIMywJSY8qDA2PjIoKSU5KLcoqrjAvy45IdgzJ8XXOS3cvtKwwNy0O8Q50SrctNzI01c0wNjICACXjIAA=/vo-chong-sieu-sao-hoi-ngot.jpg"));
        listSach.add(new Sach("THIẾU TƯỚNG, VỢ NGÀI NỔI GIẬN RỒI","Ngoc","https://static.8cache.com/cover/eJwFwcEOQCAAANAvMlOsk4NMtmZyYbi0EkIsYwtf7z2joZ8DXtXe3EjLSJmRc9lUn9vm0xoxzDlhprLdAfkbFjeW6jNOpXMSDPtKoXuu1gmBorpEo9kRncQZOxBEnoYA_O9UH4k=/thieu-tuong-vo-ngai-noi-gian-roi-543143.jpg"));
        listSach.add(new Sach("MÊ ĐẮM","Kim Họa","https://static.8cache.com/cover/eJzLyTDWD8o2Cyspda_MTwuxKPFNzjGuKDJ0LikKCTELy0538c4Ny3Uxr_QI8E7Oz3JJswwscCvMN3MJ8Cr09QuqKEpxL_EN9gwKTa0ydvFy9zcyKU7JTbctNzI01c0wNjICAERyH_0=/me-dam.jpg"));



        adapterListBook = new AdapterListBook(this,R.layout.item_book,listSach);
    }
    private void anhXa(){
        gridView = findViewById(R.id.grid_book);
        editText =  findViewById(R.id.search_text);

    }
    private void setUp(){
        gridView.setAdapter(adapterListBook);
    }
    private void setClick(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = String.valueOf(editText.getText());
                adapterListBook.sortBook(s);
            }
        });
    }

    public void getListSachFromFirebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef = db.collection("Sach").document("NVGGDJnCQhAkLe5UscYu");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()){
//                    DocumentSnapshot documentSnapshot = task.getResult();
//                    if (documentSnapshot.exists()){
//                        Toast.makeText(SearchActivity.this, "Data : " + documentSnapshot.getData() + documentSnapshot.getData().getClass(), Toast.LENGTH_SHORT).show();
//                    }else Toast.makeText(SearchActivity.this, "No such document", Toast.LENGTH_SHORT).show();
//                }else  Log.d(TAG,"Get Failed with ", task.getException());
//            }
//        });
        List<String> listTen = new ArrayList<>();
        List<String> listNxb = new ArrayList<>();
        List<String> listImg = new ArrayList<>();
        db.collection("Sach").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult() ){
                        listTen.add((String) document.getData().get("TenSach"));
                        listNxb.add((String) document.getData().get("NhaXuatBan"));
                        listImg.add((String) document.getData().get("img"));
                    }
               //     Toast.makeText(SearchActivity.this, listImg.get(0) + listNxb.get(0) + listTen.get(0), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SearchActivity.this, "Error getting documents:" + task.getException(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
//        listSach = new ArrayList<>();
//        for (int i = 0; i < listImg.size(); i++){
//            listSach.get(i).setTenSach(listTen.get(i));
//            listSach.get(i).setNhaXuatBan(listNxb.get(i));
//            listSach.get(i).setImg(listImg.get(i));
//        }
        Toast.makeText(SearchActivity.this, "List sach : " + listTen.get(2), Toast.LENGTH_SHORT).show();
    }
     public void fetchData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listSach = new ArrayList<>();
        db.collection("Sach").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        listSach.add(new Sach((String)doc.getData().get("TenSach"),(String) doc.getData().get("NhaXuatBan"),(String) doc.getData().get("img")));
                    }
                }else Toast.makeText(SearchActivity.this, "No such Document", Toast.LENGTH_SHORT).show();
            }
        });

        adapterListBook = new AdapterListBook(SearchActivity.this,R.layout.item_book,listSach);


     }
}