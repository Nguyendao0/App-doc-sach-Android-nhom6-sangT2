package com.example.helloworldjava.view.dangtruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.R;
import com.google.android.material.button.MaterialButton;

public class ThuVienCaNhanFragment extends Fragment {
    private static final String TAG = "ThuVienCaNhanFragment";
    private MaterialButton btnMoGiaoDienDangTruyen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thu_vien_ca_nhan, container, false);

        // Find view
        findView(view);

        // Set event
        btnMoGiaoDienDangTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               requireActivity().startActivity(new Intent(requireActivity(), DangSachActivity.class));
            }
        });

        return view;
    }

    public void findView(View view) {
        btnMoGiaoDienDangTruyen = view.findViewById(R.id.btnMoGiaoDienDangSachMoi);
    }
}
