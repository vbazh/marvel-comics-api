package com.vbazh.marvelcomics.di.interval;


import com.vbazh.marvelcomics.di.annotations.IntervalScope;
import com.vbazh.marvelcomics.presentation.choosedate.ChooseIntervalContract;
import com.vbazh.marvelcomics.presentation.choosedate.ChooseIntervalPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class IntervalModule {

    @Provides
    @IntervalScope
    ChooseIntervalContract.Presenter providePresenter(){
        return new ChooseIntervalPresenter();
    }

}
