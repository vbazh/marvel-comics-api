package com.vbazh.marvelcomics.presentation.choosedate;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vbazh.marvelcomics.di.annotations.IntervalScope;

import java.util.Calendar;

import javax.inject.Inject;

@IntervalScope
@InjectViewState
public class ChooseIntervalPresenter extends MvpPresenter<ChooseIntervalContract.View> implements ChooseIntervalContract.Presenter {

    @Inject
    public ChooseIntervalPresenter(){

    }

    @Override
    public void setStartInterval(int year, int month, int day) {

        getViewState().setStart(getCalendar(year, month, day).getTimeInMillis());
    }

    @Override
    public void setEndInterval(int year, int month, int day) {

        getViewState().setEnd(getCalendar(year, month, day).getTimeInMillis());
    }

    @Override
    public void checkValidInterval(long start, long end) {

        if (start == 0 || end == 0 || start > end) {
            getViewState().showError();
        } else getViewState().startComicsActivity();
    }

    @Override
    public void pickStartInterval() {

        getViewState().showStartDatePickerDialog();
    }

    @Override
    public void pickEndInterval() {

        getViewState().showEndDatePickerDialog();
    }

    @Override
    public void hideDialog() {
        getViewState().hideDatePickerDialog();
    }

    private Calendar getCalendar(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}