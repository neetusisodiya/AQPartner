package com.app.oooelePartner.Activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.oooelePartner.R;

public class Static_web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_web);

        String webs = getIntent().getStringExtra("web");


        WebView browser = findViewById(R.id.webview);

        browser.loadUrl(webs);


    }
}
