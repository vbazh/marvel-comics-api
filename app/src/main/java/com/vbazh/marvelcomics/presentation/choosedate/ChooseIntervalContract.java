package com.vbazh.marvelcomics.presentation.choosedate;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ChooseIntervalContract {

    interface Presenter {

        void setStartInterval(int year, int month, int day);

        void setEndInterval(int year, int month, int day);

        void checkValidInterval();

        void pickStartInterval();

        void pickEndInterval();

        void hideDialog();

    }

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface View extends MvpView {

        void setStart(long startDate);

        void setEnd(long endDate);

        @StateStrategyType(OneExecutionStateStrategy.class)
        void startComicsActivity(long start, long end);

        @StateStrategyType(OneExecutionStateStrategy.class)
        void showError();

        @StateStrategyType(AddToEndStrategy.class)
        void showStartDatePickerDialog();

        @StateStrategyType(AddToEndStrategy.class)
        void showEndDatePickerDialog();

        @StateStrategyType(AddToEndStrategy.class)
        void hideDatePickerDialog();


    }



}
