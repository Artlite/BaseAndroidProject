package com.artlite.bslibrary.helpers.period;


import android.util.Log;

import androidx.annotation.Nullable;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;

import java.util.Date;

/**
 * Class which provide the calculating of the difference between dates
 */
public class BSPeriodHelper {

    /**
     * {@link String} constant of the tag
     */
    private static final String TAG = BSPeriodHelper.class.getSimpleName();

    /**
     * Method which provide to get difference between dates
     *
     * @param startDate instance of the {@link Date}
     * @return instance of the {@link Period}
     */
    @Nullable
    public static Period getDifference(@Nullable final Date startDate) {
        return getDifference(startDate, new Date());
    }

    /**
     * Method which provide to get difference between dates
     *
     * @param startDate instance of the {@link Date}
     * @param endDate   instance of the {@link Date}
     * @return instance of the {@link Period}
     */
    @Nullable
    public static Period getDifference(@Nullable final Date startDate,
                                       @Nullable final Date endDate) {
        try {
            LocalDate startLocalDate = isReverseDates(startDate, endDate)
                    ? asLocalDate(endDate)
                    : asLocalDate(startDate);
            LocalDate endLocalDate = isReverseDates(startDate, endDate)
                    ? asLocalDate(startDate)
                    : asLocalDate(endDate);
            return Period.between(startLocalDate, endLocalDate);
        } catch (Exception ex) {
            Log.e(TAG, "getDifference: ", ex);
        }
        return null;
    }

    /**
     * Method which provide to convert the {@link Date} to {@link LocalDate}
     *
     * @param date instance of the {@link Date}
     * @return instance of the {@link LocalDate}
     */
    @Nullable
    private static LocalDate asLocalDate(@Nullable final Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Method which provide to convert the {@link Date} to {@link LocalDate}
     *
     * @param date instance of the {@link Date}
     * @param zone instance of the {@link ZoneId}
     * @return instance of the {@link LocalDate}
     */
    @Nullable
    private static LocalDate asLocalDate(@Nullable final Date date,
                                         @Nullable final ZoneId zone) {
        try {
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
        } catch (Exception ex) {
            Log.e(TAG, "asLocalDate: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the checking if dates is reverse
     *
     * @param startDate instance of the {@link Date}
     * @param endDate   instance of the {@link Date}
     * @return {@link Boolean} value of the difference
     */
    private static boolean isReverseDates(@Nullable final Date startDate,
                                          @Nullable final Date endDate) {
        try {
            if (startDate.compareTo(endDate) > 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return false;
    }


}
