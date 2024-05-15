package com.example.helloworldjava.view.SpeechBookTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.example.helloworldjava.view.ReadBookActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class SpeechActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    TextView bookName, chapterName;
    ImageView btnBack;
    Button btnPlay;
    Button btnPause;
    SeekBar seekBar;
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
        Intent intent =  getIntent();
        String tenSach = intent.getStringExtra("TenSach");
        String tenChuong = intent.getStringExtra("TenChuong");
        String noiDung = intent.getStringExtra("NoiDung");

        bookName = findViewById(R.id.book_name);
        chapterName = findViewById(R.id.chapter_name);

        bookName.setText(tenSach);
        chapterName.setText(tenChuong);

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
        seekBar = (SeekBar) findViewById(R.id.seedbar_play) ;
        btnPause= (Button) findViewById(R.id.pause);
        btnPause.setVisibility(View.INVISIBLE);
        btnPlay = (Button) findViewById(R.id.play);
        btnNext = (Button) findViewById(R.id.skip_next);
        btnPrevious = (Button) findViewById(R.id.skip_previous);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        // Xu li nut play
        btnPlay.setOnClickListener(v -> {
            // Toast.makeText(this, demobook.getContent(), Toast.LENGTH_SHORT).show();
           btnPlay.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   textToSpeech.speak(noiDung, TextToSpeech.QUEUE_FLUSH,null,utteranceId);
                   btnPause.setVisibility(View.VISIBLE);
                   btnPlay.setVisibility(View.INVISIBLE);
               }
           });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    float lound = (float) seekBar.getProgress() / 50;
                    if (lound < 0.1) lound = 0.1f;
                    textToSpeech.setSpeechRate(lound);
                    textToSpeech.speak(noiDung, TextToSpeech.QUEUE_FLUSH,null,utteranceId);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        });
        // Xu li nut Pause
        btnPause.setOnClickListener(v -> {
                    textToSpeech.stop();
                    btnPause.setVisibility(View.INVISIBLE);
            btnPlay.setVisibility(View.VISIBLE);
        });

        // Xu li nut back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SpeechActivity.this, ReadBookActivity.class);
                SpeechActivity.this.startActivity(i);
                textToSpeech.stop();

            }
        });

        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/me");
        dir.mkdirs();
        File file = new File(dir, "myData.wav");
        Log.d("Debug ","Path : "+file.getAbsolutePath());
        HashMap<String, String> params = new HashMap<>();
        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, demobook.Content);
        if (textToSpeech.synthesizeToFile(demobook.Content,params,file.getAbsolutePath()) == TextToSpeech.ERROR){

        }
    }

}