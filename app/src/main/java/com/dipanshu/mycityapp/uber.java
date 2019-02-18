package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class uber extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber);
        WebView webView;
        setContentView(R.layout.uber);
        webView = (WebView)findViewById(R.id.uberview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.uber.com/in/en/");
    }
}
