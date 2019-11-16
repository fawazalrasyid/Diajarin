package com.diajarin.id;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DiajarinTesWebView extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diajarintes_webview);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        WebView webView;
        webView = (WebView) this.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

    // Kill Activity on Back Press
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
