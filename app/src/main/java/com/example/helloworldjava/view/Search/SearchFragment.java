package com.example.helloworldjava.view.Search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.R;

public class SearchFragment extends Fragment {
    private Spinner spinner;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.search_layout, container, false);

        // Find views
        spinner = (Spinner) view.findViewById(R.id.spinner);
        listView = (ListView) view.findViewById(R.id.listBook);

        // Perform setup (similar to onCreate in Activity)
        runArrayList();

        return view;
    }

    private void runArrayList() {
        CatergorySearch catergorySearch = new CatergorySearch();
        HistorySearch historySearch = new HistorySearch();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                catergorySearch.getArrayCatergory());
        spinner.setAdapter(arrayAdapter);

        AdapterListBook adapterListBook = new AdapterListBook(getActivity(), R.layout.modelbook,
                historySearch.getArraySach());
        listView.setAdapter(adapterListBook);
    }
}
