package com.github.lany192.dagger.sample;

import android.os.Bundle;

import com.github.lany192.dagger.annotation.Dagger;

import dagger.android.support.DaggerAppCompatActivity;

@Dagger
public class AboutActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}