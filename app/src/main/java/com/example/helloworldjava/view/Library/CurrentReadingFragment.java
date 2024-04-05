package com.example.helloworldjava.view.Library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworldjava.R;

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