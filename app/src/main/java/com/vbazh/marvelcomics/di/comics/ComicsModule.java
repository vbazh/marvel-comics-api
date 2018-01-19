package com.vbazh.marvelcomics.di.comics;

import com.vbazh.marvelcomics.data.network.MarvelApiService;
import com.vbazh.marvelcomics.domain.characters.CharacterInteractorImpl;
import com.vbazh.marvelcomics.domain.characters.ICharacterInteractor;
import com.vbazh.marvelcomics.domain.comics.ComicsInteractor;
import com.vbazh.marvelcomics.domain.comics.IComicsInteractor;
import com.vbazh.marvelcomics.presentation.comics.ComicsContract;
import com.vbazh.marvelcomics.presentation.comics.ComicsPresenter;
import com.vbazh.marvelcomics.repositories.CharacterRepositoryImpl;
import com.vbazh.marvelcomics.repositories.ComicsRepositoryImpl;
import com.vbazh.marvelcomics.repositories.ICharacterRepository;
import com.vbazh.marvelcomics.repositories.IComicsRepository;


import dagger.Module;
import dagger.Provides;

@Module
public class ComicsModule {

    @Provides
    ComicsContract.Presenter providePresenter(IComicsInteractor comicsInteractor, ICharacterInteractor characterInteractor){
        return new ComicsPresenter(comicsInteractor, characterInteractor);
    }

    @Provides
    IComicsInteractor provideComicsInteractor(ComicsRepositoryImpl comicsRepositoryImpl){
        return new ComicsInteractor(comicsRepositoryImpl);
    }

    @Provides
    IComicsRepository provideComicsRepository(MarvelApiService apiService){
        return new ComicsRepositoryImpl(apiService);
    }

    @Provides
    ICharacterInteractor provideCharacterInteractor(CharacterRepositoryImpl characterRepositoryImpl){
        return new CharacterInteractorImpl(characterRepositoryImpl);
    }

    @Provides
    ICharacterRepository provideCharacterRepository(MarvelApiService apiService){
        return new CharacterRepositoryImpl(apiService);
    }

}