package com.vbazh.marvelcomics.di.app;

import com.vbazh.marvelcomics.di.comics.ComicsComponent;
import com.vbazh.marvelcomics.di.interval.IntervalComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        RemoteDataModule.class,
        LocalDataModule.class,
        UtilsModule.class})
public interface AppComponent {

    ComicsComponent.Builder comicsComponentBuilder();

    IntervalComponent.Builder intervalComponentBuilder();

}
