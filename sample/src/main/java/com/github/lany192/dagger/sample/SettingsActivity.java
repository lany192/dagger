package com.github.lany192.dagger.sample;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class SettingsActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}