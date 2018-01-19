package com.vbazh.marvelcomics.repositories;


import com.vbazh.marvelcomics.models.CharacterResponse;

import io.reactivex.Single;

public interface ICharacterRepository {

    Single<CharacterResponse> getCharacter(int id);
}
