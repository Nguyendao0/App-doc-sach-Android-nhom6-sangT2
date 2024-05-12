package com.example.helloworldjava.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.API.BookService;
import com.example.helloworldjava.APIResponeModel.ApiResponseSachModle;
import com.example.helloworldjava.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ListBooksHomeRecyclerViewAdapter adapter;
    List<ApiResponseSachModle> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        ImageView iconMoreYourLibrary = view.findViewById(R.id.ic_more_your_library);
        ImageView iconMoreNewBooks = view.findViewById(R.id.ic_more_new_books);
        ImageView iconMoreTrendingBooks = view.findViewById(R.id.ic_more_trending_books);

        List<ImageView> listMoreBooks = new ArrayList<>();

        BookService.api.ListBook("wVtlXbDWiRmCmETfixgd").enqueue(new Callback<List<ApiResponseSachModle>>() {
            @Override
            public void onResponse(Call<List<ApiResponseSachModle>> call, Response<List<ApiResponseSachModle>> response) {
                if (response.isSuccessful()) {
                    Log.w("Api Start", "------Sussecs-------");
                    Log.w("Api Start", "------" + data.stream().count() + "-------");
                    Log.w("Api Start", "-----------------");
                    data = response.body();

                    RecyclerView listYourLibraryRV = view.findViewById(R.id.list_your_library);
                    listYourLibraryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_your_library_item);
                    listYourLibraryRV.setAdapter(adapter);

                    // set up the RecyclerView
                    RecyclerView listNewBooksRV = view.findViewById(R.id.list_new_books);
                    listNewBooksRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
                    listNewBooksRV.setAdapter(adapter);

                    // set up the RecyclerView
                    RecyclerView listBooksTrendingRV = view.findViewById(R.id.list_books_trending);
                    listBooksTrendingRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
                    listBooksTrendingRV.setAdapter(adapter);

                    // set up the RecyclerView
                    RecyclerView listBooksCategoryRV = view.findViewById(R.id.list_books_category);
                    listBooksCategoryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), data, R.layout.list_books_item_home);
                    listBooksCategoryRV.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ApiResponseSachModle>> call, Throwable throwable) {
                Log.w("Api Start", "------errorr-------");
                Log.w("Api Start", "------" + throwable.toString() + "-------");
                Log.w("Api Start", "-----------------");
            }
        });

        listMoreBooks.add(iconMoreYourLibrary);
        listMoreBooks.add(iconMoreNewBooks);
        listMoreBooks.add(iconMoreTrendingBooks);

        for (ImageView imageView : listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks;
                    if (imageView.getId() == R.id.ic_more_your_library) {
                        titleListBooks = "THƯ VIỆN CỦA BẠN";
                    } else if (imageView.getId() == R.id.ic_more_new_books) {
                        titleListBooks = "MỚI";
                    } else if(imageView.getId() == R.id.ic_more_new_books) {
                        titleListBooks = "PHỔ BIỂN";
                    }
                    else{
                        titleListBooks = "DANH MỤC SÁCH";
                    }

                    Intent intent = new Intent(requireActivity(), ListBooksActity.class);
                    intent.putExtra("titleListBooks", titleListBooks);
                    requireActivity().startActivity(intent);
                }
            });
        }

        ImageView iconMoreCategoryBooks = view.findViewById(R.id.ic_more_category_books);
        iconMoreCategoryBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), BookCategoryActivity.class);
                requireActivity().startActivity(intent);
            }
        });


        return view;
    }
}