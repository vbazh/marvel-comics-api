package com.vbazh.marvelcomics.utils;


import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class DateFormatUtils {

    Context context;

    @Inject
    public DateFormatUtils(Context context) {
        this.context = context;
    }

    public String formatDate(@NonNull long date) {
        if (date != 0) {
            return new SimpleDateFormat("yyyy-MM-DD", Locale.getDefault()).format(new Date(date));
        }
        return "";
    }

    public String formatDateText(@NonNull long date) {
        if (date != 0) {
            return DateUtils.formatDateTime(context, date, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        }
        return "";
    }

    public String formatInterval(long start, long end) {

        if (start != 0 && end != 0) {
            return formatDate(start) + " - " + formatDate(end);
        }
        return "";
    }

    public String formatIntervalText(long start, long end) {
        return formatDateText(start) + " - " + formatDateText(end);
    }

    public String formatIntervalForQuery(long start, long end) {

        if (start != 0 && end != 0) {
            return formatDate(start) + "," + formatDate(end);
        }
        return "";
    }

}
