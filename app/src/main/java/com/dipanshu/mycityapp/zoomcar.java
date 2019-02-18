package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class zoomcar  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoomcar);
        WebView webView;
        setContentView(R.layout.zoomcar);
        webView = (WebView)findViewById(R.id.zoomview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.zoomcar.com/");
    }
}
