package com.vbazh.marvelcomics.presentation.choosedate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class PickDateDialog extends DatePickerDialog {

    /* упрощенный конструктор для поддержки апи < 24 */
    public PickDateDialog(@NonNull Context context, @Nullable OnDateSetListener listener) {
        super(context, listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }


}
