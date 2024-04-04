package com.example.helloworldjava.view.Library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class TESTGETPDFActivity extends AppCompatActivity {
    private ImageView textView;
    private File localFile;
    private TextToSpeech tts;
    private StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgetpdfactivity);
        textView = findViewById(R.id.content);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://fir-demo-b4527.appspot.com/Book1/bwb_KS-365-216.pdf");
        localFile = new File(getApplicationContext().getFilesDir(), "downloaded_file.pdf");
//        read();
    }


    private void read() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://fir-demo-b4527.appspot.com/Book1/bwb_KS-365-216.pdf");
        localFile = new File(getApplicationContext().getFilesDir(), "downloaded_file.pdf");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                try (ParcelFileDescriptor fd = ParcelFileDescriptor.open(localFile, ParcelFileDescriptor.MODE_READ_ONLY)) {
                    PdfRenderer renderer = new PdfRenderer(fd);
                    PdfRenderer.Page page = renderer.openPage(20); // Mở trang đầu tiên

                    // Lấy bitmap từ trang PDF
                    Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                    // Hiển thị bitmap trong ImageView hoặc xử lý khác

                    textView.setImageBitmap(bitmap);

                    page.close();
                    renderer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xảy ra lỗi trong quá trình tải xuống
            }
        });
    }

    public void speechToText(View view) {

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                try {
                    // Đọc tệp PDF
                    PDDocument document = PDDocument.load(localFile);

                    // Tạo một PDFTextStripper để trích xuất nội dung văn bản từ tệp PDF
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();

                    // Chỉ định trang cần trích xuất (trang 1)
                    pdfTextStripper.setStartPage(20);
                    pdfTextStripper.setEndPage(20);

                    // Trích xuất nội dung văn bản từ trang 1
                    String page1Content = pdfTextStripper.getText(document);

                    // In ra nội dung của trang 1
                    System.out.println("Nội dung trang 1:");
                    System.out.println(page1Content);

                    // Đóng tệp PDF
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Xảy ra lỗi khi tải xuống tệp PDF
            }
        });

//        try (PDDocument document = PDDocument.load(localFile)) {
////            int targetPageNumber = 20; // Số trang mong muốn
////
////            PDFTextStripper stripper = new PDFTextStripper();
////            stripper.setStartPage(targetPageNumber);
////            stripper.setEndPage(targetPageNumber);
////
////            String pageContent = stripper.getText(document);
//           // showText(pageContent); // Sử dụng một hàm để xử lý việc hiển thị văn bản
//
//            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//                @Override
//                public void onInit(int status) {
//                    if (status == TextToSpeech.SUCCESS) {
//                        tts.setLanguage(Locale.getDefault()); // Sử dụng ngôn ngữ mặc định của thiết bị
//                        tts.speak("RONALDO", TextToSpeech.QUEUE_FLUSH, null);
//                    } else {
//                        Log.e("MainActivity", "TTS initialization failed");
//                    }
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void showText(String text) {
        // Thực hiện logic cắt ngắn văn bản hoặc hiển thị trong giao diện cuộn ở đây
    }
}
