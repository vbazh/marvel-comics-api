package com.vbazh.marvelcomics.di.app;

import android.content.Context;

import com.vbazh.marvelcomics.utils.DateFormatUtils;
import com.vbazh.marvelcomics.utils.HashUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    DateFormatUtils provideDateUtils(Context context) {
        return new DateFormatUtils(context);

    }

    @Provides
    @Singleton
    HashUtil provideHashUtil(){
        return new HashUtil();
    }

}
