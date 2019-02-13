package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class GOIBIBO extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goibibo);
        WebView webView;
        setContentView(R.layout.goibibo);
        webView = (WebView)findViewById(R.id.goibibo);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.goibibo.com/");
    }
}
