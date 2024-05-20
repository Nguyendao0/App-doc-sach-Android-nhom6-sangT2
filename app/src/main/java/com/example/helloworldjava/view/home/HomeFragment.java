package com.example.helloworldjava.view.home;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.user.UserActivity;
import com.google.firebase.auth.FirebaseUser;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements ListBooksHomeRecyclerViewAdapter.ItemClickListener {
    private ListBooksHomeRecyclerViewAdapter listYourLibraryAdapter;
    private ListBooksHomeRecyclerViewAdapter listNewBookAdapter;
    private SachService sachService;
    private FirebaseAuthManager firebaseAuthManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

//        ImageView iconMoreYourLibrary = (ImageView) view.findViewById(R.id.ic_more_your_library);
        ImageView iconMoreNewBooks = (ImageView) view.findViewById(R.id.ic_more_list_books);
//        ImageView iconMoreTrendingBooks = (ImageView) view.findViewById(R.id.ic_more_trending_books);

        ImageView imgUser = view.findViewById(R.id.img_user);

        TextView tvUser = view.findViewById(R.id.tv_username);
        FirebaseUser user = firebaseAuthManager.getCurrentUser();
        if (user != null) {
            String username = user.getDisplayName(); // Lấy tên người dùng từ Firebase
            Uri photoUri = user.getPhotoUrl(); // Lấy URL ảnh đại diện người dùng từ Firebase

            tvUser.setText(username);

            if (photoUri != null) {
                String photoUrl = photoUri.toString(); // Chuyển đổi Uri thành chuỗi String

                // Sử dụng thư viện Glide để tải và hiển thị hình ảnh người dùng
                Glide.with(requireContext())
                        .load(photoUrl)
                        .into(imgUser);
            }
        }

        List<ImageView> listMoreBooks = new ArrayList<>();

//        listMoreBooks.add(iconMoreYourLibrary);
        listMoreBooks.add(iconMoreNewBooks);
//        listMoreBooks.add(iconMoreTrendingBooks);

        for (ImageView imageView : listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks = "";
                    Intent intent = new Intent(requireActivity(), ListBooksActity.class);

                    if (imageView.getId() == R.id.ic_more_list_books) {
                        titleListBooks = "KHO SÁCH";
                    }
//                    else if(imageView.getId() == R.id.ic_more_new_books) {
//                        titleListBooks = "PHỔ BIỂN";
//                    }

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
//        RecyclerView listYourLibraryRV = view.findViewById(R.id.list_your_library);
        RecyclerView listNewBooksRV = view.findViewById(R.id.list_books);
//        RecyclerView listBooksTrendingRV = view.findViewById(R.id.list_books_trending);
//        RecyclerView listBooksCategoryRV = view.findViewById(R.id.list_books_category);

        // data to populate the RecyclerView with
//        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
//        sachService.getListSachYourLibrary(idNguoiDung).enqueue(new Callback<List<Sach>>() {
//            @Override
//            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
//                List<Sach> listYourLibrarySach = response.body();
//                listYourLibraryRV.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//                listYourLibraryAdapter = new ListBooksHomeRecyclerViewAdapter( requireContext(), listYourLibrarySach,  R.layout.list_books_item_home, "ListYourLibrary");
//                listYourLibraryAdapter.setClickListener(HomeFragment.this::onItemClick);
//                listYourLibraryRV.setAdapter(listYourLibraryAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Sach>> call, Throwable throwable) {
//
//            }
//        });


        sachService.getListSachs().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                List<Sach> listYourLibrarySach = response.body();
                listNewBooksRV.setLayoutManager(new GridLayoutManager(getContext(), 3));
                listNewBookAdapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), listYourLibrarySach, R.layout.list_books_item_home, "ListBook");
                listNewBookAdapter.setClickListener(HomeFragment.this::onItemClick);
                listNewBooksRV.setAdapter(listNewBookAdapter);
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


        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), UserActivity.class);
                startActivity(intent);
            }
        });

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

        Button btnOpenQRMenu = view.findViewById(R.id.btnOpenQRMenu);
        btnOpenQRMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQRMenuDialog();
            }
        });

        return view;
    }

//    public void loadBookRecycleView(RecyclerView recyclerView, List<Sach> listSach, int layoutId,
//                                    ListBooksHomeRecyclerViewAdapter adapter) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        adapter = new ListBooksHomeRecyclerViewAdapter(requireContext(), listSach, layoutId);
//        adapter.setClickListener(this::onItemClick);
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public void onItemClick(View view, int position, String name) {
        Sach sach = listNewBookAdapter.getItem(position);
        Intent goToBookDetailIntent = new Intent(getContext(), BookDetailActivity.class);
        goToBookDetailIntent.putExtra("idSach", sach.getId());
        startActivity(goToBookDetailIntent);
    }

    public void openQRMenuDialog() {
        Dialog builder = new Dialog(getContext());
        builder.setTitle("Chọn cách quét mã QR");
        // Tạo layout cho hộp thoại
        View view = getLayoutInflater().inflate(R.layout.dialog_scan_qr_code, null);
        builder.setContentView(view);
        Button btnScanQRCodeFromCamera = view.findViewById(R.id.btnScanQRCodeFromCamera);
        Button btnScanQRCodeFromImage = view.findViewById(R.id.btnScanQRCodeFromImage);

        btnScanQRCodeFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open camera intent
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(takePictureIntent);
            }
        });

        btnScanQRCodeFromImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                galleryLauncher.launch(photoPickerIntent);
            }
        });

        builder.show();
    }

    public String decodeQR(Bitmap bitmap) {
        int[] intArray = new int[bitmap.getWidth() * bitmap.getHeight()];
        // Copy pixel data from the Bitmap into the 'intArray' array
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        LuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), intArray);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();
        try {
            Result result = reader.decode(binaryBitmap);
            return result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Get the image URI
                        // Set the image to the ImageView
                        try {
                            final Uri imageUri = data.getData();
                            final InputStream imageStream;
                            imageStream = getContext().getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            // Decode QR
                            String idSach = decodeQR(selectedImage);
                            // Go to book detail
                            Intent intent = new Intent(getContext(), BookDetailActivity.class);
                            intent.putExtra("idSach", idSach);
                            startActivity(intent);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Get the image URI
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    // Decode QR
                    String idSach = decodeQR(photo);
                    // Go to book detail
                    Intent intent = new Intent(getContext(), BookDetailActivity.class);
                    intent.putExtra("idSach", idSach);
                    startActivity(intent);
                }
            });
}
