package com.vbazh.marvelcomics;

import com.vbazh.marvelcomics.domain.characters.ICharacterInteractor;
import com.vbazh.marvelcomics.domain.comics.IComicsInteractor;
import com.vbazh.marvelcomics.presentation.comics.ComicsContract;
import com.vbazh.marvelcomics.presentation.comics.ComicsPresenter;
import com.vbazh.marvelcomics.utils.DateFormatUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComicsTestPresenter {

    @Mock
    ComicsContract.View comicsView;

    @Mock
    IComicsInteractor comicsInteractor;

    @Mock
    DateFormatUtils dateFormatUtils;

    @Mock
    ICharacterInteractor characterInteractor;

    ComicsPresenter comicsPresenter;

    @Before
    public void initTestComics() {
        MockitoAnnotations.initMocks(this);

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

        comicsPresenter = new ComicsPresenter(comicsInteractor, characterInteractor, dateFormatUtils);
        comicsPresenter.attachView(comicsView);

    }

    @Test
    public void checkNetworkError() {

        Throwable throwable = new Throwable("error internet");

        when(comicsInteractor.getComics(20, 0, null)).thenReturn(Single.error(throwable));

        comicsPresenter.loadData();

        InOrder inOrder = Mockito.inOrder(comicsView);

        inOrder.verify(comicsView, times(1)).showError();
        inOrder.verify(comicsView, times(1)).hideLoading();
        inOrder.verifyNoMoreInteractions();

    }
}
