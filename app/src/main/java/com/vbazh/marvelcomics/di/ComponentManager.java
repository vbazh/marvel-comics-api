package com.vbazh.marvelcomics.di;

import android.content.Context;

import com.vbazh.marvelcomics.di.app.AppComponent;
import com.vbazh.marvelcomics.di.app.AppModule;
import com.vbazh.marvelcomics.di.app.DaggerAppComponent;
import com.vbazh.marvelcomics.di.comics.ComicsComponent;
import com.vbazh.marvelcomics.di.interval.IntervalComponent;

public class ComponentManager {

    private static volatile ComponentManager instance;

    private AppComponent appComponent;
    private ComicsComponent comicsComponent;
    private IntervalComponent intervalComponent;

    private ComponentManager() {

    }

    public static ComponentManager getInstance() {

        if (instance == null) {

            synchronized (ComponentManager.class) {
                if (instance == null) {
                    instance = new ComponentManager();
                }
            }
        }
        return instance;
    }

    public void initAppComponent(Context context) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
    }

    public AppComponent getAppComponent() {

        return appComponent;
    }

    public ComicsComponent getComicsComponent() {

        if (comicsComponent == null) {
            comicsComponent = getAppComponent()
                    .comicsComponentBuilder()
                    .build();
        }

        return comicsComponent;
    }

    public void destroyComicsComponent(){

        comicsComponent = null;
    }

    public IntervalComponent getIntervalComponent() {

        if (intervalComponent == null) {
            intervalComponent = getAppComponent()
                    .intervalComponentBuilder()
                    .build();
        }

        return intervalComponent;
    }

    public void destroyIntervalComponent(){

        intervalComponent = null;
    }

}
