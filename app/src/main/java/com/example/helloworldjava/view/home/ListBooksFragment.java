package com.example.helloworldjava.view.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.R;

public class ListBooksFragment extends Fragment {
    ListBooksRecyclerViewAdapter adapter;
    int numberOfColumns = 3;
    public ListBooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_list_books, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();
        if (extras != null) {
            String titleListBooks = extras.getString("titleListBooks");
            TextView txtListBooks = view.findViewById(R.id.txtListMore);
            txtListBooks.setText(titleListBooks);
        }

        String[] listArrangeBooks = getResources().getStringArray(R.array.listArrangeBooks);
        ArrayAdapter<String> listArrangeBooksAdapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, listArrangeBooks);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.listArrangeBookAutoCompleTextView);
        autoCompleteTextView.setAdapter(listArrangeBooksAdapter);

        ImageView btnBackHome = view.findViewById(R.id.iconBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().finish();
            }
        });

        // Data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.list_book);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), numberOfColumns));
        adapter = new ListBooksRecyclerViewAdapter(requireContext(), data);
        recyclerView.setAdapter(adapter);
    }

}
