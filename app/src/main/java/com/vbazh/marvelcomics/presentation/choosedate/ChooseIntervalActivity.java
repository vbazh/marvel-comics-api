package com.vbazh.marvelcomics.presentation.choosedate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vbazh.marvelcomics.Consts;
import com.vbazh.marvelcomics.R;
import com.vbazh.marvelcomics.di.ComponentManager;
import com.vbazh.marvelcomics.presentation.comics.ComicsActivity;
import com.vbazh.marvelcomics.utils.DateFormatUtils;

import javax.inject.Inject;

public class ChooseIntervalActivity extends MvpAppCompatActivity implements ChooseIntervalContract.View {

    Toolbar toolbar;
    EditText startInterval, endInterval;
    PickDateDialog datePickerDialog;

    @Inject
    @InjectPresenter
    ChooseIntervalPresenter presenter;

    @Inject
    DateFormatUtils dateFormatUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getIntervalComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        initViews();
    }

    void initViews() {

        toolbar = findViewById(R.id.toolbar);
        startInterval = findViewById(R.id.start_interval);
        endInterval = findViewById(R.id.end_interval);

        startInterval.setOnClickListener(view -> presenter.pickStartInterval());

        endInterval.setOnClickListener(view -> presenter.pickEndInterval());

        findViewById(R.id.choose_button).setOnClickListener(view -> {

            presenter.checkValidInterval();
        });

        toolbar.setTitle(R.string.app_name);
    }

    @Override
    public void setStart(long startDate) {

        startInterval.setText(dateFormatUtils.formatDate(startDate));
    }

    @Override
    public void setEnd(long endDate) {

        endInterval.setText(dateFormatUtils.formatDate(endDate));
    }

    @Override
    public void startComicsActivity(long start, long end) {

        Intent startComicsActivity = new Intent(this, ComicsActivity.class);
        startComicsActivity.putExtra(Consts.EXTRA_START_INTERVAL, start);
        startComicsActivity.putExtra(Consts.EXTRA_END_INTERVAL, end);
        startActivity(startComicsActivity);
    }

    @Override
    public void showError() {

        Toast.makeText(this, R.string.wrong_interval, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStartDatePickerDialog() {

        datePickerDialog = new PickDateDialog(this, (datePicker, year, month, day) -> {
            presenter.setStartInterval(year, month, day);
            presenter.hideDialog();
        });

        datePickerDialog.setOnCancelListener(dialogInterface -> presenter.hideDialog());
        datePickerDialog.show();
    }

    @Override
    public void showEndDatePickerDialog() {

        datePickerDialog = new PickDateDialog(this, (datePicker, year, month, day) ->
        {
            presenter.setEndInterval(year, month, day);
            presenter.hideDialog();
        });

        datePickerDialog.setOnCancelListener(dialogInterface -> presenter.hideDialog());
        datePickerDialog.show();
    }

    @Override
    public void hideDatePickerDialog() {
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing()) {
            ComponentManager.getInstance().destroyIntervalComponent();
        }
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
            datePickerDialog = null;
        }
    }
}