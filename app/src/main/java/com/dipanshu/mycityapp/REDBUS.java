package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class REDBUS extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redbus);
        WebView webView;
        setContentView(R.layout.redbus);
        webView = (WebView)findViewById(R.id.redbus);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.redbus.in/");
    }
}
