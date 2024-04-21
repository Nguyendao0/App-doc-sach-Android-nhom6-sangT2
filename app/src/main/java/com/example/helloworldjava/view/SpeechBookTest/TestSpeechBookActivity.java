//package com.example.helloworldjava.view.SpeechBookTest;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.PopupMenu;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.speech.tts.TextToSpeech;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.helloworldjava.R;
//import com.example.helloworldjava.view.CommentActivity;
//import com.example.helloworldjava.view.ReadBookActivity;
//
//import java.util.Locale;
//import java.util.UUID;
//
//public class TestSpeechBookActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
//    TextView ViewContent;
//    TextToSpeech textToSpeech;
//    Button btnSpeech;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_read_book);
//        TestTextToSpeech();
//        CommentAction();
//    }
//
//    public void TestTextToSpeech(){
//        ViewContent = findViewById(R.id.bookContent);
//        btnSpeech = findViewById(R.id.btnSpeech);
//
//        SachContent sachtest = new SachContent("Thư nhấn máy gọi chị Thanh. Tình cờ quen nhau trong một lớp tập huấn cách đây hơn năm, hai chị em quý mến nhau nên thường hay nhắn tin qua lại. Khi Thư bảo muốn xin về học việc ở bệnh viện của chị, chị còn ngỡ Thư nói đùa. Nhưng nghe cô rỉ rả tâm sự, chị nhỏ nhẹ: \"Em cứ về đây làm hợp đồng một thời gian với các chị rồi tính tiếp nhé!\".\n" +
//                "\n" +
//                "Chị dẫn Thư đến căn phòng cuối cùng của khoa Ngoại. Đây là phòng chờ dành cho khách, cũng là phòng nghỉ của nhân viên liền ca mà nhà ở xa. Mở cửa phòng, mùi cồn sát khuẩn vẩn cả mùi ẩm mốc xộc lên khiến mắt mũi Thư cay xè. Chị Thanh đưa cho cô chiếc chìa khóa, dặn dò cẩn thận rồi tất tả đi vì có điện thoại của trưởng khoa. Đặt chiếc va-li vào góc phòng, Thư ngồi xuống chiếc ghế lạnh ngắt gọi điện thoại về cho mẹ, vừa nói được hai câu đã bật khóc rưng rức. Mẹ cô, người đàn bà có khả năng lớn nhất là phục tùng chồng, nghe điện thoại của con mà phải cố nén nỗi thương vào lồng ngực.");
//        ViewContent.setText(sachtest.getContent());
//        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if(status == TextToSpeech.SUCCESS) {
//                    int lang = textToSpeech.setLanguage(new Locale("vie"));
//
//                    if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED){
//                        Toast.makeText(TestSpeechBookActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestSpeechBookActivity.this, "Language supported", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//        btnSpeech.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = sachtest.getContent();
//                String utteranceId = UUID.randomUUID().toString();
//                //textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH,null);
//                textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null,utteranceId);
//            }
//        });
//    }
//
//    public void CommentAction(){
//        ImageView imageViewComment = findViewById(R.id.imageView_comment);
//        imageViewComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(TestSpeechBookActivity.this, CommentActivity.class);
//                TestSpeechBookActivity.this.startActivity(myIntent);
//            }
//        });
//    }
//
//    public void showPopup (View v){
//        PopupMenu popup = new PopupMenu(TestSpeechBookActivity.this, v);
//        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
//        popup.inflate(R.menu.menu_read);
//        popup.setForceShowIcon(true);
//        popup.show();
//    }
//
//
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        if (menuItem.getItemId() == R.id.item1){
//            Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else if (menuItem.getItemId() == R.id.item2){
//            Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else if (menuItem.getItemId() == R.id.item3){
//            Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else    {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_readbook, menu);
//        return true;
//    }
//
//    @Override
//    protected void onDestroy() {
//
//        super.onDestroy();
//        if(textToSpeech != null){
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
//    }
//}
