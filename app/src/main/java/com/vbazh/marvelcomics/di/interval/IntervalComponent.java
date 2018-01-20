package com.vbazh.marvelcomics.di.interval;

import com.vbazh.marvelcomics.di.annotations.IntervalScope;
import com.vbazh.marvelcomics.presentation.choosedate.ChooseIntervalActivity;

import dagger.Subcomponent;

@IntervalScope
@Subcomponent(modules = {IntervalModule.class})
public interface IntervalComponent {

    @Subcomponent.Builder
    interface Builder {
        IntervalComponent build();
    }
    void inject(ChooseIntervalActivity chooseIntervalActivity);

}
