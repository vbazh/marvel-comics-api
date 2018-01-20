package com.vbazh.marvelcomics.presentation.comics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vbazh.marvelcomics.di.annotations.ComicsScope;
import com.vbazh.marvelcomics.domain.characters.ICharacterInteractor;
import com.vbazh.marvelcomics.domain.comics.IComicsInteractor;
import com.vbazh.marvelcomics.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@ComicsScope
@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsContract.View> implements ComicsContract.Presenter {

    IComicsInteractor comicsInteractor;
    ICharacterInteractor characterInteractor;

    CompositeDisposable subscriptions;
    private String interval;
    private int totalCount, offset;

    @Inject
    public ComicsPresenter(IComicsInteractor comicsInteractor, ICharacterInteractor characterInteractor) {

        this.comicsInteractor = comicsInteractor;
        this.characterInteractor = characterInteractor;
        subscriptions = new CompositeDisposable();
        offset = 0;
    }

    @Override
    public void loadData(String interval) {

        this.interval = interval;

        getViewState().showLoading();
        subscriptions.add(comicsInteractor.getComics(20, offset, interval)
                .compose(RxUtil.applySingleSchedulers())
                .subscribe(
                        response -> {
                            getViewState().hideLoading();
                            getViewState().hideError();
                            getViewState().addData(response.getData().getComics());
                            if (response.getData().getTotal() == 0) {
                                getViewState().showNodata();
                            } else {
                                totalCount = response.getData().getTotal();
                                this.offset += response.getData().getComics().size();
                            }
                        },

                        error -> {
                            getViewState().showError();
                            getViewState().hideLoading();

                        }
                ));
    }

    @Override
    public void onScrolled(int visibleItemCount, int totalItemCount, int firstVisibleItem) {

        if (offset != totalCount) {

            if ((visibleItemCount + firstVisibleItem) >= totalItemCount
                    && firstVisibleItem >= 0
                    && totalItemCount <= totalCount) {

                loadData(interval);
            }
        }
    }

    @Override
    public void loadCharacters(int position, int id) {

        getViewState().showLoading();
        subscriptions.add(characterInteractor.getCharacter(id)
                .compose(RxUtil.applySingleSchedulers())
                .subscribe(
                        response -> {
                            getViewState().addCharacters(position, id, response.getData().getCharacters());
                            getViewState().hideLoading();
                            getViewState().hideError();
                        },

                        error -> {
                            getViewState().showError();
                            getViewState().hideLoading();

                        }
                ));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(subscriptions);
    }
}
