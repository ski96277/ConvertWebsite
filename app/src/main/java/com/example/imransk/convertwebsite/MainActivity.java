package com.example.imransk.convertwebsite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    WebView webView;

    public class myWebclient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            MainActivity.this.bar.setVisibility(View.GONE);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if (CheckNetwork.isInternetAvailable(this)) {
            setContentView(R.layout.activity_main);
            this.webView = (WebView) findViewById(R.id.webView_id);
            this.bar = (ProgressBar) findViewById(R.id.progressBar);
            this.webView.setWebViewClient(new myWebclient());
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.loadUrl("http://www.bu.edu.bd/");
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Nointernet.class));
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webView.goBack();
        return true;
    }
}
