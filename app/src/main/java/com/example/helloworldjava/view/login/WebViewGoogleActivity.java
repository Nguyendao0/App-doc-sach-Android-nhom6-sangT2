package com.example.helloworldjava.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.helloworldjava.R;

public class WebViewGoogleActivity extends AppCompatActivity {
        private static final String GMAIL_LOGIN_URL = "https://accounts.google.com/signin/v2/identifier";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_google);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(false); // Disable DOM storage
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // Enable JavaScript
        webView.loadUrl(GMAIL_LOGIN_URL); // Load Gmail login URL
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://accounts.google.com/o/oauth2/auth")) {
                    // URL is the authentication callback URL
                    // Handle authentication success or failure
                    return true; // Redirect handled by app
                }
                // Load the URL in the WebView
                view.loadUrl(url);
                return true;
            }
        });
    }

}