package com.github.lany192.dagger.sample;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import dagger.android.support.DaggerAppCompatActivity;

@Route(path = "/app/about")
public class AboutActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}