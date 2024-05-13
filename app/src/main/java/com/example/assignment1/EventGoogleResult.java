package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.FragmentActivity;

public class EventGoogleResult extends FragmentActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_google_result);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String eventName = intent.getStringExtra("eventName");
        if (eventName != null) {
            String url = "https://www.google.com/search?q=" + eventName.replace(" ", "+");
            webView.loadUrl(url);
        }
    }
}