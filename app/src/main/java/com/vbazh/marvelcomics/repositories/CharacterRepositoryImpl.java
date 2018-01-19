package com.vbazh.marvelcomics.repositories;

import com.vbazh.marvelcomics.data.network.MarvelApiService;
import com.vbazh.marvelcomics.models.CharacterResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class CharacterRepositoryImpl implements ICharacterRepository {

    MarvelApiService apiService;

    @Inject
    public CharacterRepositoryImpl(MarvelApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<CharacterResponse> getCharacter(int id) {
        return apiService.getCharacter(id);
    }
}
