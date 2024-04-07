package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import com.example.helloworldjava.Library.View.LibraryActivity;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity" ,"Hello world");

//         tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    // Set language (US English in this case)
//                    tts.setLanguage(Locale.US);
//                    String text = "Hello, this is a sample text.";
//                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//
//                } else {
//                    Log.e("MainActivity", "TTS initialization failed");
//                }
//            }
//        });

    }




}