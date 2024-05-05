package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanCodeActivity extends AppCompatActivity implements
        ZXingScannerView.ResultHandler {
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }
    @Override
    public void handleResult(Result result) {
        TextView resultTExtView = findViewById(R.id.result_Text);
        resultTExtView.setText(result.getText());
        ScannerView.stopCamera();
    }
    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }
    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}