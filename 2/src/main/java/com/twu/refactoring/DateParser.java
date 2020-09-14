package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year = getTime("Year", 0, 4, 2000, 2012);
        month = getTime("Month", 5, 7, 1, 12);
        date = getTime("Date", 8, 10, 1, 31);

        if (dateAndTimeString.charAt(11) == 'Z') {
            hour = 0;
            minute = 0;
        } else {
            hour = getTime("Hour", 11, 13, 0, 23);
            minute = getTime("Minute", 14, 16, 0, 59);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getTime(String inputTime, int start, int end, int min, int max) {
        int time;
        try {
            String timeString = dateAndTimeString.substring(start, end);
            time = Integer.parseInt(timeString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("%s string is less than %d characters", inputTime, end - start));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("%s is not an integer", inputTime));
        }

        if (time < min || time > max) {
            throw new IllegalArgumentException(String.format("%s cannot be less than %d or more than %d", inputTime, min, max));
        }

        return time;
    }
}
