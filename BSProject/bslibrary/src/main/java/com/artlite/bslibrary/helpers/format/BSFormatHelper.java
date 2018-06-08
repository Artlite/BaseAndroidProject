package com.artlite.bslibrary.helpers.format;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which provide the format functional
 */

public final class BSFormatHelper extends BSBaseHelper {

    private static String K_FORMAT_DATE = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * Method which provide the formatting of double value
     *
     * @param number         value which should be formatted
     * @param precisionCount precision count
     * @return
     */
    public static String format(double number, int precisionCount) {
        StringBuilder formatterValue = new StringBuilder("0.");

        for (int i = 0; i < precisionCount; i++) {
            formatterValue.append("0");
        }

        DecimalFormat formatter = new DecimalFormat(formatterValue.toString());
        return formatter.format(number);
    }

    /**
     * Method which provide the formatting date
     *
     * @param date current date
     * @return formatted String
     */
    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(K_FORMAT_DATE);
        return dateFormat.format(date);
    }

    /**
     * Method which provide the formatting {@link String} to {@link Date}
     *
     * @param dateString instance of {@link String}
     * @return instance of {@link Date}
     */
    public static Date format(String dateString) {
        final String methodName = "String convert(date)";
        try {
            return new SimpleDateFormat(K_FORMAT_DATE).parse(dateString);
        } catch (Exception e) {
            log(null, methodName, e, null);
        }
        return null;
    }
}
