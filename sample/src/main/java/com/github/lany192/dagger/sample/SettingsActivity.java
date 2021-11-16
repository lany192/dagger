package com.github.lany192.dagger.sample;

import android.os.Bundle;
import android.widget.TextView;

import com.github.lany192.dagger.annotation.Dagger;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@Dagger
public class SettingsActivity extends DaggerAppCompatActivity {
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ((TextView) findViewById(R.id.show)).setText(user.toString());
    }
}