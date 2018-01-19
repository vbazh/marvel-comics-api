package com.vbazh.marvelcomics.di.interval;

import com.vbazh.marvelcomics.presentation.choosedate.ChooseIntervalActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {IntervalModule.class})
public interface IntervalComponent {

    @Subcomponent.Builder
    interface Builder {
        IntervalComponent build();
    }
    void inject(ChooseIntervalActivity chooseIntervalActivity);

}
