package com.vbazh.marvelcomics.repositories;


import com.vbazh.marvelcomics.models.ComicsResponse;

import io.reactivex.Single;

public interface IComicsRepository {

    Single<ComicsResponse> getComics(Integer limit, Integer offset, String dateInterval);

}
