package com.example.helloworldjava.Library;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{
    private String imageUrl;
    private String title;
    private LayoutInflater minflater;
    private  DatabaseReference mDatabase;
    private StorageReference storageRef;
    private int fileCount = 0;
    public BookAdapter(LayoutInflater minflater) {
        this.minflater = minflater;
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.storageRef = FirebaseStorage.getInstance().getReference();
        getAmountBook();
        this.readData();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_library_item,parent,false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setUI(title, imageUrl);

        // Đăng ký bộ lắng nghe sự kiện click cho item

                // Hiển thị số thứ tự của item bằng Toast
                ImageView imageView = holder.itemView.findViewById(R.id.imageBook);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imageView.setAlpha(0.5f);
                    }
                });

    }

    private void readData() {

        mDatabase.child("BookApp").child("Books").child("BOOK1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String image = dataSnapshot.child("Image").getValue(String.class);
                    title = dataSnapshot.child("Title").getValue(String.class);
                    StorageReference imageRef = storageRef.child("Images/" + image);
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageUrl = uri.toString();
                            notifyDataSetChanged();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý lỗi khi không thể lấy URL của ảnh
                        }
                    });

                } else {
                    Log.d("firebase", "Data does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("firebase", "Error getting data", databaseError.toException());
            }
        });
    }

    private void getAmountBook()
    {
        storageRef.child("Books").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {


                // Duyệt qua danh sách file và thư mục
                for (StorageReference item : listResult.getItems()) {
                    // Kiểm tra xem item là thư mục hay không
                    fileCount++;
                }

                System.out.println("Số lượng file:"+ fileCount);
            }
        });

    }


    @Override
    public int getItemCount() {
        return  fileCount;
    }
}


