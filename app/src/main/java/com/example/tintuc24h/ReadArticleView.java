package com.example.tintuc24h;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReadArticleView extends AppCompatActivity {

    WebView webView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article_view);

        mToolbar = findViewById(R.id.mToolBarWebView);
        mToolbar.setTitle("Tin tuc 24h");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String readLink = intent.getStringExtra("link");
        webView.loadUrl(readLink);
        webView.setWebViewClient(new WebViewClient());
    }
}
