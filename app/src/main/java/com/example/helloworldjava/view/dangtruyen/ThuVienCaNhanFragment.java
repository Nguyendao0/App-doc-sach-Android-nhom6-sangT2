package com.example.helloworldjava.view.dangtruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.view.home.HomeFragment;
import com.example.helloworldjava.view.home.ListBooksHomeRecyclerViewAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThuVienCaNhanFragment extends Fragment implements ListBooksHomeRecyclerViewAdapter.ItemClickListener {
    private static final String TAG = "ThuVienCaNhanFragment";
    private MaterialButton btnMoGiaoDienDangTruyen;
    private ListBooksHomeRecyclerViewAdapter listYourLibraryAdapter;
    private SachService sachService = ServiceBuilder.buildService(SachService.class);
    private FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager(getContext());
    private RecyclerView listYourLibraryRV;
    private TextView tvTongSoSachTrongThuVien;

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

        // data to populate the RecyclerView with
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        sachService.getListSachYourLibrary(idNguoiDung).enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                List<Sach> listYourLibrarySach = response.body();
                listYourLibraryRV.setLayoutManager(new GridLayoutManager(requireContext(), 3));
                listYourLibraryAdapter = new ListBooksHomeRecyclerViewAdapter( requireContext(), listYourLibrarySach,  R.layout.list_books_item_home, "ListYourLibrary");
                listYourLibraryAdapter.setClickListener(ThuVienCaNhanFragment.this);
                listYourLibraryRV.setAdapter(listYourLibraryAdapter);
                tvTongSoSachTrongThuVien.setText(String.valueOf(listYourLibrarySach.size()));
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });


        return view;
    }

    public void findView(View view) {
        btnMoGiaoDienDangTruyen = view.findViewById(R.id.btnMoGiaoDienDangSachMoi);
        listYourLibraryRV = view.findViewById(R.id.listYourLibraryRV);
        tvTongSoSachTrongThuVien = view.findViewById(R.id.tvTongSoSachTrongThuVien);
    }

    @Override
    public void onItemClick(View view, int position, String name) {
        Sach sach = listYourLibraryAdapter.getItem(position);
        Intent goToBookDetailIntent = new Intent(getContext(), BookDetailActivity.class);
        goToBookDetailIntent.putExtra("idSach", sach.getId());
        startActivity(goToBookDetailIntent);
    }
}
