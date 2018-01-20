package com.vbazh.marvelcomics.di.comics;

import com.vbazh.marvelcomics.di.annotations.ComicsScope;
import com.vbazh.marvelcomics.presentation.comics.ComicsActivity;

import dagger.Subcomponent;

@ComicsScope
@Subcomponent(modules = {ComicsModule.class})
public interface ComicsComponent {

    @Subcomponent.Builder
    interface Builder {
        ComicsComponent build();
    }

    void inject(ComicsActivity comicsActivity);

}
