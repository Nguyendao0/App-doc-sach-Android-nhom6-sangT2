package com.example.helloworldjava.view.SpeechBookTest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworldjava.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class SpeechActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    Button btnPlay;
    Button btnPause;
    Button btnNext;
    Button btnPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        demoSpeech();
    }




    public void demoSpeech(){

        // Tao demobook de doc thu
        SachContent demobook = new SachContent();
        demobook.Name_chapter = "Cuối cùng chu kỳ, cầu thông hắc ám";
        demobook.numberofpage = 200;
        demobook.numberofchapter = 1328;

        demobook.setContent("Nhớ lại năm 1898, một thảm kịch đã xảy ra tại thị trấn Rorland - New York. Buổi sáng đầy tuyết lạnh và trơn trợt hôm đó, Joe Farley ra chuồng ngựa, đấm yên cương để dự đám tang một đứa bé thì con ngựa bỗng dưng trở chứng và tung một cú đá hầu, làm anh tử vong tại chỗ để lại vợ và ba đứa con trai cùng vài trăm đô-la tiền bảo hiểm.\n" +
                "\n" +
                " Người con trai lớn nhất của anh, Jim, 10 tuổi, phải bỏ học để làm việc kiếm sống tại một nhà máy gạch. Cậu làm đủ mọi việc từ đẩy xe xúc, đổ đất vào khuôn rồi sắp gạch đi phơi nắng. Jim không được may mắn học hành đến nơi đến chốn nhưng với tánh tình vui vẻ tự nhiên, cậu rất dễ làm cho người khác yêu mến mình. Năm tháng trôi qua, cậu bé ngày xưa đã trở thành một người đàn ông từng trải bước vào chính trường và trở thành một chính trị gia có tài nhớ tên người một cách kỳ lạ.\n" +
                "\n" +
                "Ông chưa bao giờ được nghe giảng ở một trường Đại học nào cả nhưng trước năm 46 tuổi, 4 trường Đại học đã tặng cho ông nhiều học vị danh dự rồi ông trở thành Chủ tịch Uỷ ban toàn Quốc đảng Dân Chủ và là Tổng Giám Đốc của bưu điện Mỹ. Tôi đã có lần phỏng vấn và hỏi Jim Farley về bí quyết thành công. Ông trả lời : \"Cần cù làm việc\" Tôi đáp lại : \"Ông đừng nói đùa nhé !\" Ông hỏi ngược lại là theo tôi thì lý do gì khiến ông thành công. Tôi đáp : \"Tôi biết ông có thể nhớ tên riêng của 10.000 người\" Ông đáp lại : \"Không ! Ông sai rồi. Tôi có thể nhớ tên riêng của 50.000 người\". Chính khả năng đặc biệt này đã giúp cho Jim Farley đưa Franklin Roosevelt vào Nhà trắng khi ông điều hành chiến dịch tranh cử Tổng thống cho Roosevelt vào năm 1932.");


        // Tao class texttospeech, set locale tieng viet
        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int lang = textToSpeech.setLanguage(new Locale("vie"));

                if (lang ==TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(SpeechActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(SpeechActivity.this, "Language supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String utteranceId = UUID.randomUUID().toString();
        btnPause= (Button) findViewById(R.id.pause);
        btnPlay = (Button) findViewById(R.id.play);
        btnNext = (Button) findViewById(R.id.skip_next);
        btnPrevious = (Button) findViewById(R.id.skip_previous);

        // Xu li nut play
//        btnPlay.setOnClickListener(v -> {textToSpeech.synthesizeToFile(wakeUpText, myHashRender, destFileName);
////            textToSpeech.synthesizeToFile(wakeUpText,utteranceId,destFileName,utteranceId);
//
//            Toast.makeText(this, demobook.getContent(), Toast.LENGTH_SHORT).show();
//            textToSpeech.speak(demobook.Content, TextToSpeech.QUEUE_FLUSH,null,utteranceId);
//            HashMap<String, String> myHashRender = new HashMap();
//            String wakeUpText = "Are you up yet?";
//            String destFileName = "/sdcard/myAppCache/wakeUp.wav";
//            myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, wakeUpText);
//
//        });
//        // Xu li nut Pause
//        btnPause.setOnClickListener(v -> {
//                    Toast.makeText(this, "Check OK", Toast.LENGTH_SHORT).show();
//                    textToSpeech.stop();
//        });



//        MediaRecorder recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setOutputFile("/sdcard/audiofile.mp3");
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        recorder.start();
//        recorder.stop();
//        recorder.release();
//
//        textToSpeech.shutdown();



    }

}