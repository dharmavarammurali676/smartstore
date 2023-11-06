package com.smartstore.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Get date with format
     *
     * @param date Date
     * @param format DateFormat
     * @return the date after format
     */
    public static String getDateStr(Date date, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);

            // to convert Date to String, use format method of SimpleDateFormat class.
            return dateFormat.format(date);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return StringUtils.EMPTY;
        }
    }
    public static Date getCurrentDate() {
        return new Date();
    }

    public static String convertLongToDateTime(Long absoluteTime, String format) {
        LOGGER.info("[DateUtils] convertLongToDateTime absoluteTime:{}, format:{}", absoluteTime,
                format);
        if (absoluteTime != null) {
            LocalDateTime date =
                    Instant.ofEpochMilli(absoluteTime).atZone(ZoneId.of("Japan")).toLocalDateTime();
            return date.format(DateTimeFormatter.ofPattern(format));
        }
        return StringUtils.EMPTY;
    }
}
