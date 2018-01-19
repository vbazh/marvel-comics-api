package com.vbazh.marvelcomics.di.app;

import android.content.Context;

import com.vbazh.marvelcomics.Consts;
import com.vbazh.marvelcomics.data.SharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataModule {

    @Provides
    @Singleton
    SharedPrefs provideSharedPrefs(Context context){
        return new SharedPrefs(context.getSharedPreferences(Consts.PREF_DATA, Context.MODE_PRIVATE));
    }
}
