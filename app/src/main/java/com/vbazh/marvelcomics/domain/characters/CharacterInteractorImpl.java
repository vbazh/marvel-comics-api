package com.vbazh.marvelcomics.domain.characters;

import com.vbazh.marvelcomics.models.CharacterResponse;
import com.vbazh.marvelcomics.repositories.ICharacterRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CharacterInteractorImpl implements ICharacterInteractor {

    private ICharacterRepository characterRepository;

    @Inject
    public CharacterInteractorImpl(ICharacterRepository characterRepository) {

        this.characterRepository = characterRepository;
    }

    @Override
    public Single<CharacterResponse> getCharacter(int id) {

        return characterRepository.getCharacter(id);
    }
}
