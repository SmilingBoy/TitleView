package com.smile.titleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smile.titlebar.ZQTitleView;

public class MainActivity extends AppCompatActivity {

    private ZQTitleView zqTitleView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zqTitleView = (ZQTitleView) findViewById(R.id.titleview);

        zqTitleView.setTitleText("我的");
    }
}
