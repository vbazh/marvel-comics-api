package com.vbazh.marvelcomics;

import android.app.Application;

import com.vbazh.marvelcomics.di.ComponentManager;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ComponentManager.getInstance().initAppComponent(this);

    }
}
