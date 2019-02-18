package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ola extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ola);
        WebView webView;
        setContentView(R.layout.ola);
        webView = (WebView)findViewById(R.id.olaview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.olacabs.com/");
    }
}
