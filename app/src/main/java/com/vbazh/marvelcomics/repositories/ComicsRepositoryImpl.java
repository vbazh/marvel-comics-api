package com.vbazh.marvelcomics.repositories;

import com.vbazh.marvelcomics.data.network.MarvelApiService;
import com.vbazh.marvelcomics.models.ComicsResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ComicsRepositoryImpl implements IComicsRepository {

    MarvelApiService apiService;

    @Inject
    public ComicsRepositoryImpl(MarvelApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<ComicsResponse> getComics(Integer limit, Integer offset, String dateInterval) {
        return apiService.getComics(limit, offset, dateInterval);
    }

}
