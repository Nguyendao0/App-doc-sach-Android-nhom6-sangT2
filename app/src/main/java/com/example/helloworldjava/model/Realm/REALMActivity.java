package com.example.helloworldjava.model.Realm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class REALMActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realmactivity);
        mStorageRef = FirebaseStorage.getInstance().getReference();


    }
    public void Dowload(View view) {
        File localFile = new File(getFilesDir(), "file.pdf");

        mStorageRef.child("Books").child("Level_up_your_code_with_Game_Programming_Pattern.pdf").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Tệp PDF đã được tải xuống thành công
                // localFile chứa đường dẫn đến tệp PDF trong không gian lưu trữ riêng của ứng dụng

                String filePath = localFile.getAbsolutePath(); // Lấy đường dẫn tuyệt đối của tệp PDF
                Toast.makeText(REALMActivity.this, "Đã tải xuống tệp PDF. Đường dẫn: " + filePath, Toast.LENGTH_LONG).show();
                System.out.println(filePath);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xảy ra lỗi trong quá trình tải xuống tệp PDF
                Toast.makeText(REALMActivity.this, "Lỗi tải xuống tệp PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}