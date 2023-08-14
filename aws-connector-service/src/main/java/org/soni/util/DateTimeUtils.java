package org.soni.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;


@Log4j2
public class DateTimeUtils {
    private DateTimeUtils() {

    }

    public static String yYMmDdFormat(String input) {
        LocalDate date = LocalDate.parse(input);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_yyyy_MM_dd)
                .withResolverStyle(ResolverStyle.STRICT);
        return date.format(dateTimeFormatter);
    }

    public static String ddMMYyyyFormat(String input) {
        try {
            if (ObjectUtils.isNotEmpty(input)) {
                DateFormat srcDf = new SimpleDateFormat(DateConstant.DATE_FORMAT_yyyy_MM_dd);
                DateFormat destDf = new SimpleDateFormat(DateConstant.DATE_FORMAT_dd_mm_yyyy_WITH_SLASH);
                Date date = srcDf.parse(input);
                return destDf.format(date);
            }
        } catch (ParseException parseException) {
            log.error("Exception occurred during date parsing {} ", parseException.getMessage());
        }
        return null;
    }

    public static String yyyyMMDDFormat(String input) {
        try {
            if (ObjectUtils.isNotEmpty(input)) {
                DateFormat srcDf = new SimpleDateFormat(DateConstant.DATE_FORMAT_dd_mm_yyyy_WITH_SLASH);
                DateFormat destDf = new SimpleDateFormat(DateConstant.DATE_FORMAT_yyyy_MM_dd);
                Date date = srcDf.parse(input);
                return destDf.format(date);
            }
        } catch (ParseException parseException) {
            log.error("Exception occurred during date parsing {} ", parseException.getMessage());
        }
        return null;
    }

    public static String getDateAndTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return (dateTimeFormatter.format((LocalDate.now())) + System.nanoTime()).substring(0, 20);
    }

}