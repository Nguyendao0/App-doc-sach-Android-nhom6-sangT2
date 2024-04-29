package com.example.helloworldjava.view.Library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.List;

public class CurrentReadingFragment extends Fragment implements CurrentReadingContract.View {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private LibraryBookAdapter bookAdapter;
    private LibraryBookAdapter bookAdapter1;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        recyclerView = view.findViewById(R.id.RVOthersBooks);
        recyclerView1 = view.findViewById(R.id.RVOfflineBooks);

        bookAdapter = new LibraryBookAdapter(requireContext());
        bookAdapter1 = new LibraryBookAdapter(requireContext());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(), 3);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(bookAdapter1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        System.out.println("LẠI LÀ TAO ĐÂY");
        // Inflate the layout for this fragment
        return view;
    }



    public void setSachList()
    {


    }
}