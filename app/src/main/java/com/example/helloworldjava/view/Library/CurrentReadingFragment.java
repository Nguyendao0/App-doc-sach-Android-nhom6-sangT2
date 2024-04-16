package com.example.helloworldjava.view.Library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.R;

public class CurrentReadingFragment extends Fragment implements CurrentReadingContract.View {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        return view;
    }
}
