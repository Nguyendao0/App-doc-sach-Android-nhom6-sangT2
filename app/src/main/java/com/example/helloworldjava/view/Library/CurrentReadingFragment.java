package com.example.helloworldjava.view.Library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.helloworldjava.presenter.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.presenter.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;

import java.util.ArrayList;

public class CurrentReadingFragment extends Fragment implements CurrentReadingContract.View {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private LibraryBookAdapter bookAdapter;
    private LibraryBookAdapter bookAdapter1;

    private TextView amountOthersBook;
    private TextView amountOfflineBook;
    private LibraryContract.Presenter presenter;

    private CurrentReadingContract.Presenter currentReadingPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        recyclerView = view.findViewById(R.id.RVOthersBooks);
        recyclerView1 = view.findViewById(R.id.RVOfflineBooks);
        amountOthersBook = view.findViewById(R.id.amountBooksOnline);
        amountOfflineBook = view.findViewById(R.id.amountBooksOffline);

        bookAdapter = new LibraryBookAdapter(requireContext(), presenter, false);
        bookAdapter1 = new LibraryBookAdapter(requireContext(), presenter, true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(), 3);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(bookAdapter1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        currentReadingPresenter.readSach();
        currentReadingPresenter.readSachOffline();
        return view;
    }

    public void setLibraryPresenter( LibraryContract.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setSachList(ArrayList<Sach> list)
    {
        bookAdapter.setSachList(list);
        amountOthersBook.setText(list.size() +" truyện");
    }

    @Override
    public void setSachOfflineList(ArrayList<Sach> list) {
        bookAdapter1.setSachList(list);
        amountOfflineBook.setText(list.size() +" truyện");
    }

    @Override
    public void setCurrentPresenter(CurrentReadingContract.Presenter currentReadingPresenterresenter) {
        this.currentReadingPresenter = currentReadingPresenterresenter;
    }



    @Override
    public boolean Visible() {
        return this.isVisible();
    }

    @Override
    public void setProgressBar(Sach sach) {
        ArrayList<Sach> list = new ArrayList<>();
       for(Sach s : bookAdapter.getSachList())
       {
           if(s.getID() == sach.getID())
           {
                s.setChuong_Items(sach.getChuong_Items());
           }
           list.add(s);
       }

       setSachList(list);
    }


}