package com.github.lany192.dagger.sample.di.module;


import com.github.lany192.dagger.sample.AboutActivity;
import com.github.lany192.dagger.sample.MainActivity;
import com.github.lany192.dagger.sample.SettingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract AboutActivity aboutActivity();

    @ContributesAndroidInjector
    abstract SettingsActivity settingsActivity();
}
