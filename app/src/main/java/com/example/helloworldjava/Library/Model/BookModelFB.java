package com.example.helloworldjava.Library.Model;

import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.helloworldjava.Library.Book;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.ValueListerner;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BookModelFB implements LibraryContract.Model {
    private static BookModelFB instance;
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef;

    private BookModelFB() {
        // Khởi tạo DatabaseReference và StorageReference
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public static synchronized BookModelFB getInstance() {
        if (instance == null) {
            instance = new BookModelFB();
        }
        return instance;
    }

    public void cleanup() {
        // Giải phóng tài nguyên liên quan đến Firebase
        instance = null;

    }


    @Override
    public void Read(ValueListerner.ListAgrument listerner) {
        List<Book> bookList = new ArrayList<>();
        mDatabase.child("BookApp").child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    Long ID = bookSnapshot.child("ID").getValue(Long.class);
                    String Image = bookSnapshot.child("Image").getValue(String.class);
                    String Source = bookSnapshot.child("Source").getValue(String.class);
                    String Title = bookSnapshot.child("Title").getValue(String.class);
                    Book book = new Book(ID, Image, Source, Title);
                    bookList.add(book);
                }
                listerner.onDataLoaded(bookList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        });
    }

//    @Override
//    public void dowloadImageFile(ValueListerner.ListAgrument listerner, List<Book> bookList) {
//        StorageReference booksRef = mStorageRef.child("Images");
//        booksRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
//            @Override
//            public void onSuccess(ListResult listResult) {
//                for (StorageReference item : listResult.getItems()) {
//                    item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri downloadUrl) {
//                            String fileUrl = downloadUrl.toString();
//                            for(Book b : bookList)
//                            {
//                                if(fileUrl.contains(b.getImage()))
//                                {
//                                    b.setDowloadedImageFilePath(fileUrl);
//                                }
//                            }
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Xử lý khi có lỗi xảy ra trong quá trình truy cập file
//                        }
//                    });
//                    listerner.onDataLoaded(bookList);
//                }
//
//            }
//        });
//    }

    @Override
    public void dowloadImageFile(ValueListerner.ListAgrument listerner, List<Book> bookList) {
        StorageReference booksRef = mStorageRef.child("Images");
        booksRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                new BookAT(bookList, listResult, listerner).execute();

            }
        });
    }


    private class BookAT extends AsyncTask<Void, Void, Void> {

        private List<Book> bookList;
        private ListResult listResult;
        private ValueListerner.ListAgrument listener;

        public BookAT(List<Book> bookList, ListResult listResult, ValueListerner.ListAgrument listener) {
            this.bookList = bookList;
            this.listResult = listResult;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            CountDownLatch latch = new CountDownLatch(listResult.getItems().size());

            for (StorageReference item : listResult.getItems()) {
                item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUrl) {
                        String fileUrl = downloadUrl.toString();
                        for (Book b : bookList) {
                            if (fileUrl.contains(b.getImage())) {
                                b.setDowloadedImageFilePath(fileUrl);
                            }
                        }
                        latch.countDown();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        latch.countDown();
                    }
                });
            }

            try {
                latch.await(); // Chờ cho tất cả các tác vụ tải xuống hoàn thành
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onDataLoaded(bookList);

        }
    }
}
