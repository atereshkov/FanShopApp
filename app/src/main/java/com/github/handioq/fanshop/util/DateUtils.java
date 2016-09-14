package com.github.handioq.fanshop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static String getStringDateFromLong(long longDate) {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                .format(new Date(longDate));
    }

}
