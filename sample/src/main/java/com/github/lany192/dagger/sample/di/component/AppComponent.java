package com.github.lany192.dagger.sample.di.component;

import android.app.Application;


import com.github.lany192.dagger.sample.MyApplication;
import com.github.lany192.dagger.sample.di.module.ActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}