package com.vbazh.marvelcomics.presentation.comics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vbazh.marvelcomics.di.annotations.ComicsScope;
import com.vbazh.marvelcomics.domain.characters.ICharacterInteractor;
import com.vbazh.marvelcomics.domain.comics.IComicsInteractor;
import com.vbazh.marvelcomics.utils.DateFormatUtils;
import com.vbazh.marvelcomics.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@ComicsScope
@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsContract.View> implements ComicsContract.Presenter {

    private IComicsInteractor comicsInteractor;
    private ICharacterInteractor characterInteractor;
    private DateFormatUtils dateFormatUtils;

    private CompositeDisposable subscriptions;
    private String interval;
    private int totalCount, offset;
    private boolean isLoadingComics;

    @Inject
    public ComicsPresenter(IComicsInteractor comicsInteractor,
                           ICharacterInteractor characterInteractor,
                           DateFormatUtils dateFormatUtils) {

        this.comicsInteractor = comicsInteractor;
        this.characterInteractor = characterInteractor;
        this.dateFormatUtils = dateFormatUtils;
        subscriptions = new CompositeDisposable();
        offset = 0;
    }

    @Override
    public void getDataIntent(long start, long end) {
        this.interval = dateFormatUtils.formatIntervalForQuery(start, end);
        getViewState().setTitle(dateFormatUtils.formatIntervalText(start, end));
    }

    @Override
    public void loadData() {

        isLoadingComics = true;
        getViewState().showLoading();
        subscriptions.add(comicsInteractor.getComics(20, offset, interval)
                .compose(RxUtil.applySingleSchedulers())
                .doFinally(() -> isLoadingComics = false)
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

        if (!isLoadingComics && offset != totalCount) {

            if ((visibleItemCount + firstVisibleItem) >= totalItemCount
                    && firstVisibleItem >= 0
                    && totalItemCount <= totalCount) {

                loadData();
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
