package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IRCTC extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.irctc);

        Intent intent=getIntent();
        String from=intent.getStringExtra("Cityf");
        String to=intent.getStringExtra("City");
        WebView webView;
        setContentView(R.layout.irctc);
        webView = (WebView)findViewById(R.id.irctc);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //url will be the url that you click in your webview.
                //you can open it with your own webview or do
                //whatever you want

                //Here is the example that you open it your own webview.
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://www.google.com/maps/dir/"+from+"/"+to);
    }
}
