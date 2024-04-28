package com.example.helloworldjava.view.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.view.BookCategoryActivity;
import com.example.helloworldjava.view.HomeActivity;
import com.example.helloworldjava.view.ListBooksActity;
import com.example.helloworldjava.view.ListBooksFragment;
import com.example.helloworldjava.view.ListBooksHomeRecyclerViewAdapter;
import com.example.helloworldjava.view.Menu.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ListBooksHomeRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.activity_home, container, false);

//        View view1 = inflater.inflate(R.layout.menu_layout, container, false);
//
//        // Tìm ViewPager2 trong layout của Fragment bằng cách sử dụng getView()
//        ViewPager2 viewPager2 = view1.findViewById(R.id.view_pager);
//
//        // Khởi tạo và gán ViewPagerAdapter cho ViewPager2
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(requireActivity(), viewPager2);
//        viewPager2.setAdapter(viewPagerAdapter);

        ImageView iconMoreYourLibrary = (ImageView) view.findViewById(R.id.ic_more_your_library);
        ImageView iconMoreNewBooks = (ImageView) view.findViewById(R.id.ic_more_new_books);
        ImageView iconMoreTrendingBooks = (ImageView) view.findViewById(R.id.ic_more_trending_books);

        List<ImageView> listMoreBooks = new ArrayList<>();

        listMoreBooks.add(iconMoreYourLibrary);
        listMoreBooks.add(iconMoreNewBooks);
        listMoreBooks.add(iconMoreTrendingBooks);

        for (ImageView imageView :listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks;
                    if (imageView.getId() == R.id.ic_more_your_library) {
                        titleListBooks = "THƯ VIỆN CỦA BẠN";
                    }
                    else if (imageView.getId() == R.id.ic_more_new_books) {
                        titleListBooks = "MỚI";
                    }
                    else {
                        titleListBooks = "PHỔ BIỂN";
                    }

                    // Truyền dữ liệu vào Fragment thông qua ViewModel hoặc Constructor
//                    ListBooksFragment fragment = new ListBooksFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("titleListBooks", titleListBooks);
//                    fragment.setArguments(bundle);
//
//                     // Gọi phương thức setCurrentItem của adapter để thay đổi trang hiện tại của ViewPager2
//                    viewPagerAdapter.setCurrentItem(1); // Thay position bằng vị trí của Fragment bạn muốn hiển thị
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

        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4"};

        RecyclerView listYourLibraryRV = view.findViewById(R.id.list_your_library);
        listYourLibraryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter( requireContext(), data, R.layout.list_your_library_item);
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

        return view;
    }


}
