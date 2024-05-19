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
                        listSach.add(new Sach((String)doc.getData().get("TenSach"),(String) doc.getData().get("NhaXuatBan"),(String) doc.getData().get("img"),(String) doc.getData().get("id")));
                    }
                }else Toast.makeText(SearchActivity.this, "No such Document", Toast.LENGTH_SHORT).show();
            }
        });

        adapterListBook = new AdapterListBook(SearchActivity.this,R.layout.item_book,listSach);


     }
}