package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class OYO extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oyo);
        WebView webView;
        setContentView(R.layout.oyo);
        webView = (WebView)findViewById(R.id.oyo);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.oyorooms.com/");
    }
}
