package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class avis extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avis);
        WebView webView;
        setContentView(R.layout.avis);
        webView = (WebView)findViewById(R.id.avisview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.avis.com/en/home");
    }
}
