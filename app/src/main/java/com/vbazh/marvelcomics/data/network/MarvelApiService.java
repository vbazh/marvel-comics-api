package com.vbazh.marvelcomics.data.network;

import com.vbazh.marvelcomics.models.CharacterResponse;
import com.vbazh.marvelcomics.models.ComicsResponse;


import javax.annotation.Nullable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelApiService {

    @GET("/v1/public/comics")
    Single<ComicsResponse> getComics(@Query("limit") @Nullable Integer limit,
                                     @Query("offset") @Nullable Integer offset,
                                     @Query("dateRange") @Nullable String dateRange);

    @GET("/v1/public/comics/{id}/characters")
    Single<CharacterResponse> getCharacter(@Path("id") Integer id);

}
