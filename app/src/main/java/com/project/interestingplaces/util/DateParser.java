package com.project.interestingplaces.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    public static String formatDate(int timestamp) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        return simpleDateFormat.format(new Date((long) timestamp * 1000));
    }
}
