package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class TRIVAGO extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivago);
        WebView webView;
        setContentView(R.layout.trivago);
        webView = (WebView)findViewById(R.id.trivago);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.trivago.in/");
    }
}
