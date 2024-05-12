package com.example.helloworldjava.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements ListBooksHomeRecyclerViewAdapter.ItemClickListener {
    private ListBooksHomeRecyclerViewAdapter listYourLibraryAdapter;
    private ListBooksHomeRecyclerViewAdapter listTrendingBookAdapter;
    private SachService sachService;
    private FirebaseAuthManager firebaseAuthManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.activity_home, container, false);

//        View view1 = inflater.inflate(R.layout.menu_layout, container, false);
//
//        // Tìm ViewPager2 trong layout của Fragment bằng cách sử dụng getView()
//        viewPager2 = view1.findViewById(R.id.view_pager);
//
//        // Khởi tạo và gán ViewPagerAdapter cho ViewPager2
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(requireActivity(), viewPager2);
//        viewPager2.setAdapter(viewPagerAdapter);

        firebaseAuthManager = new FirebaseAuthManager(getContext());
        sachService = ServiceBuilder.buildService(SachService.class);

        ImageView iconMoreYourLibrary = (ImageView) view.findViewById(R.id.ic_more_your_library);
        ImageView iconMoreNewBooks = (ImageView) view.findViewById(R.id.ic_more_new_books);
//        ImageView iconMoreTrendingBooks = (ImageView) view.findViewById(R.id.ic_more_trending_books);

        List<ImageView> listMoreBooks = new ArrayList<>();

        listMoreBooks.add(iconMoreYourLibrary);
        listMoreBooks.add(iconMoreNewBooks);
//        listMoreBooks.add(iconMoreTrendingBooks);

        for (ImageView imageView :listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks;
                    Intent intent = new Intent(requireActivity(), ListBooksActity.class);

                    if (imageView.getId() == R.id.ic_more_your_library) {
                        titleListBooks = "THƯ VIỆN CỦA BẠN";
                    }
                    else if (imageView.getId() == R.id.ic_more_new_books) {
                        titleListBooks = "MỚI";
                    }
                    else {
                        titleListBooks = "PHỔ BIỂN";
                    }

                    intent.putExtra("titleListBooks", titleListBooks);
                    requireActivity().startActivity(intent);
//
//                    // Truyền dữ liệu vào Fragment thông qua ViewModel hoặc Constructor
//                    ListBooksFragment fragment = new ListBooksFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("titleListBooks", titleListBooks);
//                    fragment.setArguments(bundle);
//
//                     // Gọi phương thức setCurrentItem của adapter để thay đổi trang hiện tại của ViewPager2
//                    viewPagerAdapter.setCurrentItem(0); // Thay position bằng vị trí của Fragment bạn muốn hiển thị


                }
            });
        }

//        ImageView iconMoreCategoryBooks = view.findViewById(R.id.ic_more_category_books);
//        iconMoreCategoryBooks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(requireActivity(), BookCategoryActivity.class);
//                requireActivity().startActivity(intent);
//            }
//        });

        // Find recycleView
        RecyclerView listYourLibraryRV = view.findViewById(R.id.list_your_library);
        RecyclerView listNewBooksRV = view.findViewById(R.id.list_new_books);
//        RecyclerView listBooksTrendingRV = view.findViewById(R.id.list_books_trending);
//        RecyclerView listBooksCategoryRV = view.findViewById(R.id.list_books_category);

        // data to populate the RecyclerView with
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        sachService.getListSachYourLibrary(idNguoiDung).enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                List<Sach> listYourLibrarySach = response.body();
                System.out.println(listYourLibrarySach.size());
                listYourLibraryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                listYourLibraryAdapter = new ListBooksHomeRecyclerViewAdapter( requireContext(), listYourLibrarySach,  R.layout.list_books_item_home);
                listYourLibraryAdapter.setClickListener(HomeFragment.this::onItemClick);
                listYourLibraryRV.setAdapter(listYourLibraryAdapter);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable throwable) {

            }
        });


//        sachService.getListSachPhoBien(true).enqueue(new Callback<List<Sach>>() {
//            @Override
//            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
//                List<Sach> listSachPhoBien = response.body();
//                System.out.println(listSachPhoBien.size());
//                loadBookRecycleView(listBooksTrendingRV, listSachPhoBien, R.layout.list_books_item_home, listTrendingBookAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Sach>> call, Throwable throwable) {
//
//            }
//        });


        // set up the RecyclerView
//        listNewBooksRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
//        listNewBooksRV.setAdapter(adapter);
//
//        // set up the RecyclerView
//        listBooksTrendingRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
//        listBooksTrendingRV.setAdapter(adapter);
//
//        // set up the RecyclerView
//        listBooksCategoryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
//        listBooksCategoryRV.setAdapter(adapter);

        return view;
    }

    public void loadBookRecycleView(RecyclerView recyclerView, List<Sach> listSach, int layoutId,
                                    ListBooksHomeRecyclerViewAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter( requireContext(), listSach, layoutId);
        adapter.setClickListener(this::onItemClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Sach sach = listYourLibraryAdapter.getItem(position);
        Intent goToBookDetailIntent = new Intent(getContext(), BookDetailActivity.class);
        goToBookDetailIntent.putExtra("idSach", sach.getId());
        startActivity(goToBookDetailIntent);
    }
}
