package com.github.lany192.dagger.sample.di.module;

import com.github.lany192.dagger.sample.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CustomModule {

    @Singleton
    @Provides
    User provideUser() {
        return new User(1,"Lany");
    }
}
