package com.vbazh.marvelcomics.domain.characters;

import com.vbazh.marvelcomics.models.CharacterResponse;

import io.reactivex.Single;

public interface ICharacterInteractor {

    Single<CharacterResponse> getCharacter(int id);

}
