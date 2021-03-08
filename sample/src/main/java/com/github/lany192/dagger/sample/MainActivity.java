package com.github.lany192.dagger.sample;

import android.content.Intent;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
        findViewById(R.id.button2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));
    }
}