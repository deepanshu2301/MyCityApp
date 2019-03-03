package com.dipanshu.mycityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class admindelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindelete);


        WebView webView;
        setContentView(R.layout.activity_admindelete);
        webView = (WebView)findViewById(R.id.adminrights);
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
        webView.loadUrl("https://console.firebase.google.com/u/0/project/mycityapp-d5a62/database/mycityapp-d5a62/data");

    }
}
