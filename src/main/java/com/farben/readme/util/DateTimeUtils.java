package com.farben.readme.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {


    private static final Locale LOCALE = Locale.CHINA;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String GMT_8 = "GMT+8";
    private final SimpleDateFormat dateTimeSdf = new SimpleDateFormat(DateTimeUtils.DATE_TIME_PATTERN, DateTimeUtils.LOCALE);

    private static final DateTimeUtils INSTAN = new DateTimeUtils();

    public static DateTimeUtils instance() {
        return DateTimeUtils.INSTAN;
    }

    public String formatDateTime(final Date date) {
        if (date != null) {
            return this.dateTimeSdf.format(date);
        } else {
            return null;
        }
    }

    public static Date getGMT8Time() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DateTimeUtils.GMT_8));
        return cal.getTime();
    }
}
