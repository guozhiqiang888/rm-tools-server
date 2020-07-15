package com.farben.readme.util;

import com.farben.readme.constant.Constant;
import com.farben.readme.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @ClassName: TimeUtil
 * @Description: TODO
 * @author: 43994701
 * @date May 21, 2020
 */
public class TimeUtil {

    private final static Logger log = LoggerFactory.getLogger(TimeUtil.class);

    private static final String timeZone = "Asia/Shanghai";

    /**
     * get current time with time zone Shanghai
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeUtil.timeZone));
        return simpleDateFormat.format(date);
    }

    public static String getTime(final long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeUtil.timeZone));
        return simpleDateFormat.format(date);
    }

    public static String getTime(final long time, final String timePattern) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeUtil.timeZone));
        return simpleDateFormat.format(date);
    }

    public static String getTime(final String timePattern) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeUtil.timeZone));
        return simpleDateFormat.format(date);
    }

    public static String getTime(final Date date, final String timePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        return simpleDateFormat.format(date);
    }

    public static String getTime(final Date date, final String timePattern, final Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern, locale);
        return simpleDateFormat.format(date);
    }

    public static Date parseString2Date(final String time) {
        if (StringUtils.isEmpty(time)) {
            log.error("Parse data error, empty resultvalue ");
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Constant.PATTERN_YYYY_MM_DD);
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            log.error("Parse data error ", e);
        }
        return date;
    }

    public static boolean expiredTimeStamp(final String timeStamp) throws TokenException {
        log.info("Create time [{}]", timeStamp2Date(timeStamp));
        long expriredTime = Long.valueOf(timeStamp) + Constant.TOKEN_TIMESTAMP_LAST_TIME;
        log.info("Expired time [{}]", timeStamp2Date(String.valueOf(expriredTime)));
        long currentTime = System.currentTimeMillis();
        log.info("Current time [{}]", timeStamp2Date(String.valueOf(currentTime)));
        return currentTime > expriredTime;
    }

    public static String timeStamp2Date(final String seconds) throws TokenException {
        if (!isNumeric(seconds)) {
            throw new TokenException("Invalid timestamp");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.PATTERN_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static boolean isNumeric(final String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
