package com.example.helloworldjava.Library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworldjava.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CurrentReadingFragment extends Fragment {
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        rv=view.findViewById(R.id.RVOfflineBooks);
        BookAdapter ba = new BookAdapter(inflater);
        this.rv.setAdapter(ba);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rv.setLayoutManager(layoutManager);
        // Inflate the layout for this fragment
        return view;
    }


}