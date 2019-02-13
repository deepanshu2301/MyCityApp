package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MMT extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmt);
        WebView webView;
        setContentView(R.layout.mmt);
        webView = (WebView)findViewById(R.id.mmt);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.makemytrip.com/");
    }
}
