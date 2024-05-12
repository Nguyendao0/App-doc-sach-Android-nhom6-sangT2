package com.example.helloworldjava.view.Search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    GridView gridView;
    AdapterListBook adapterListBook;
    EditText editText;
    Button btn;
    List<Sach> listSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout

        View view = inflater.inflate(R.layout.search_layout, container, false);
        // Find views


        // Perform setup (similar to onCreate in Activity)

        //Anh xa
        gridView = view.findViewById(R.id.grid_book);
        editText =  view.findViewById(R.id.search_text);
        //Init Adapter
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listSach = new ArrayList<>();
        db.collection("Sach").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        listSach.add(new Sach((String)doc.getData().get("TenSach"),(String) doc.getData().get("NhaXuatBan"),(String) doc.getData().get("img")));
                    }
                }else Toast.makeText(getContext(), "No such Document", Toast.LENGTH_SHORT).show();
            }
        });

        adapterListBook = new AdapterListBook(getContext(),R.layout.item_book,listSach);
        gridView.setAdapter(adapterListBook);
        //Xu li onClick
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
        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        anhXa();
//        setClick();
//
//    }



    private void anhXa(){
        gridView = gridView.findViewById(R.id.grid_book);
        editText =  editText.findViewById(R.id.search_text);

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
}
