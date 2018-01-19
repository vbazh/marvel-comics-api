package com.vbazh.marvelcomics.domain.comics;

import com.vbazh.marvelcomics.models.ComicsResponse;
import com.vbazh.marvelcomics.repositories.IComicsRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class ComicsInteractor implements IComicsInteractor {

    IComicsRepository comicsRepository;

    @Inject
    public ComicsInteractor(IComicsRepository comicsRepository) {

        this.comicsRepository = comicsRepository;
    }

    @Override
    public Single<ComicsResponse> getComics(Integer limit, Integer offset, String dateInterval) {

        return comicsRepository.getComics(limit, offset, dateInterval);
    }
}
