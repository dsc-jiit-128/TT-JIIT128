package com.example.rohan.f7;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class WebKiosk extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_kiosk);
        webView.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().getDomStorageEnabled();
        webView.loadUrl("https://webkiosk.jiit.ac.in/");
        webView.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }
}
