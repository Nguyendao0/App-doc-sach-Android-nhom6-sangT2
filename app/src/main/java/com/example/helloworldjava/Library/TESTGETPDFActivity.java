package com.example.helloworldjava.Library;

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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

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
        if(localFile.exists())
        {
            read();
            System.out.println("Bố m ko tải");
        }
        else {
            System.out.println("Bố m tải");
            dowloadFile();
        }

    }


    private void read() {

        try (ParcelFileDescriptor fd = ParcelFileDescriptor.open(localFile, ParcelFileDescriptor.MODE_READ_ONLY)) {
            PdfRenderer renderer = new PdfRenderer(fd);
            PdfRenderer.Page page = renderer.openPage(19); // Mở trang đầu tiên

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

    public void speechToText(View view) {
        extractTextFromPDF();
    }

    private void extractTextFromPDF() {
        try {
            PdfReader reader = new PdfReader(localFile.getAbsolutePath());
            PdfDocument document = new PdfDocument(reader);

            int pageNumber = 20;
            PdfPage page = document.getPage(pageNumber);
            String pageContent = PdfTextExtractor.getTextFromPage(page);
            Log.i("CONTENT",pageContent);
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {

                        tts.setLanguage(Locale.US); // Sử dụng ngôn ngữ mặc định của thiết bị
                        tts.speak(pageContent, TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        Log.e("MainActivity", "TTS initialization failed");
                    }
                }
            });
            document.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private void dowloadFile()
    {
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
}
