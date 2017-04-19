package com.bawei.goshopping.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.bawei.goshopping.R;

/**
 * Created by 赵倩 on 2017/4/17.
 * <p>
 * 类的用途：
 */
public class WebActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_main);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebView web = (WebView) findViewById(R.id.web_web);
        web.loadUrl(url);
    }
}
