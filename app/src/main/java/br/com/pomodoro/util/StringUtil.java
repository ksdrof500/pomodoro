package br.com.pomodoro.util;

import android.app.Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.pomodoro.R;

/**
 * Created by filipenunes on 04/20/18.
 */

public class StringUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dateFormatHour = new SimpleDateFormat("h:m:s a");
    private static final int MINUTE_SECOND = 60;
    public static final int ONE_SECOND = 1000;
    private static final int TODAY = 0;
    private static final int YESTERDAY = 1;
    public static final int TWENTY_FIVE_MINUTES = 1500000;
    public static final String TWENTY_FIVE = "25:00";

    public static String convertToDate(Date date) {
        return dateFormat.format(date);
    }

    public static String calculateBetweenDates(String dateHistoric, Activity activity) {
        try {
            Date date = dateFormat.parse(dateHistoric);
            switch ((int) TimeUnit.DAYS.convert(
                    Math.abs(new Date().getTime() - date.getTime()), TimeUnit.MILLISECONDS)) {
                case TODAY:
                    return activity.getString(R.string.today);

                case YESTERDAY:
                    return activity.getString(R.string.yesterday);

                default:
                    return dateHistoric;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateHistoric;
    }

    public static String calculateBetweenHour(long millisUntilFinished, Activity activity) {
        long diffInMillies = new Date().getTime() - millisUntilFinished;
        long minutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (seconds < MINUTE_SECOND) {
            return activity.getString(R.string.ago_seconds, seconds);
        } else if (minutes < MINUTE_SECOND) {
            return activity.getString(R.string.ago_minutes, minutes);
        } else {
            return dateFormatHour.format(
                    new Date(millisUntilFinished));
        }
    }

    public static String calculateTimerFinish(long millisUntilFinished) {
        return calculateTimer(TWENTY_FIVE_MINUTES - (millisUntilFinished - ONE_SECOND));
    }

    public static String calculateTimer(long millisUntilFinished) {
        return formatTimer(true, millisUntilFinished) + ":"
                + formatTimer(false, millisUntilFinished);
    }

    private static String formatTimer(boolean isMin, long millisUntilFinished) {
        String aux;
        int constCalender = isMin ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);
        aux = c.get(constCalender) < 10 ? "0" + c.get(constCalender) : "" + c.get(constCalender);
        return aux;
    }


}
