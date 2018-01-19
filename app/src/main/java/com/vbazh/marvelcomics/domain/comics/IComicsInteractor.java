package com.vbazh.marvelcomics.domain.comics;

import com.vbazh.marvelcomics.models.ComicsResponse;

import io.reactivex.Single;

public interface IComicsInteractor {

    Single<ComicsResponse> getComics(Integer limit, Integer offset, String dateInterval);

}
