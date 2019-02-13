package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class IRCTC extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irctc);
        WebView webView;
        setContentView(R.layout.irctc);
        webView = (WebView)findViewById(R.id.irctc);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.irctc.co.in/nget/train-search");
    }
}
