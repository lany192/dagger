package com.github.lany192.dagger.sample;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@Route(path = "/app/about")
public class AboutActivity extends DaggerAppCompatActivity {
    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ((TextView) findViewById(R.id.show)).setText(user.toString());
    }
}