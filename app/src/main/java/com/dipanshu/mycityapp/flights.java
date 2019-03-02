package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class flights extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmt);


        WebView webView;
        Intent intent=getIntent();
        String city=intent.getStringExtra("City");

        setContentView(R.layout.mmt);
        webView = (WebView)findViewById(R.id.mmt);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.makemytrip.com/");
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
        Toast.makeText(getBaseContext(),""+city,Toast.LENGTH_LONG).show();
        webView.loadUrl("https://www.google.com/search?ibp=flt&q=Flights%20to%20"+city);
    }
}
