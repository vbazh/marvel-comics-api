package com.vbazh.marvelcomics.presentation.comics;

import com.arellomobile.mvp.MvpView;
import com.vbazh.marvelcomics.models.CharacterResponse;
import com.vbazh.marvelcomics.models.ComicsResponse;

import java.util.List;

public interface ComicsContract {

    interface View extends MvpView {

        void setTitle(String interval);

        void addData(List<ComicsResponse.Comic> comics);

        void showError();

        void hideError();

        void showLoading();

        void hideLoading();

        void showNodata();

        void hideNodata();

        void addCharacters(int position, int id, List<CharacterResponse.Character> characters);

    }

    interface Presenter {

        void getDataIntent(long start, long end);

        void loadData();

        void onScrolled(int visibleItemCount, int totalItemCount, int firstVisibleItem);

        void loadCharacters(int position, int id);

    }
}
