package com.artlite.bslibrary.helpers.random;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.util.Random;

/**
 * Created by dlernatovich on 12/22/2016.
 */

public class BSRandomHelper extends BSBaseHelper {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 900000;
    private static final String ALPHABET = "QWERTYUIOP{}ASDFGHJKL:|ZXCVBNM<>?qwertyuiop[]" +
            "asdfghjkl;'zxcvbnm,./1234567890-=+_";
    private static final String ALPHABET_ONLY_LETTERS = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiop" +
            "asdfghjklzxcvbnm1234567890";

    /**
     * Method which provide the generating of the random integer value
     *
     * @return current random integer value
     */
    public static int generateInt() {
        return generateInt(MIN_VALUE, MAX_VALUE);
    }

    /**
     * Method which provide the generating of the random integer value
     *
     * @param max max value
     * @return current random integer value
     */
    public static int generateInt(int max) {
        return generateInt(MIN_VALUE, max);
    }

    /**
     * Method which provide the generating of the random integer value
     *
     * @param min min value
     * @param max max value
     * @return current random integer value
     */
    public static int generateInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * Method which provide the generating of the random String value with length
     *
     * @param length current random string length
     * @return generated String value
     */
    public static String generateString(int length) {
        return generateString(length, false);
    }

    /**
     * Method which provide the generating of the random String value with length
     *
     * @param length current random string length
     * @return generated String value
     */
    public static String generateString(int length, boolean onlyLetters) {
        char[] chars = (!onlyLetters)
                ? ALPHABET.toCharArray()
                : ALPHABET_ONLY_LETTERS.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    /**
     * Method which provide to generate of the random sentence
     *
     * @param context instance of context
     * @return generated sentence
     */
    public static String generateSentence(@Nullable final Context context, int count) {
        final StringBuilder builder = new StringBuilder("");
        String[] words = words(context);
        if (validate(words)) {
            for (int i = 0; i < count; i++) {
                int random = generateInt(0, words.length - 1);
                String word = words[random];
                if ((i == 0) && (word.length() > 1)) {
                    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
                }
                builder.append(word).append(" ");
            }
        }
        return builder.toString();
    }


    /**
     * Method which provide the getting of the random words list from xml
     *
     * @param context instance of {@link Context}
     * @return array of {@link String}
     */
    @NonNull
    private static String[] words(@Nullable final Context context) {
        final String methodName = "String[] words(context)";
        try {
            String[] words = context.getResources().getStringArray(R.array.bs_array_english_words);
            return words;
        } catch (Exception ex) {
            log(null, methodName, ex, context);
        }
        return new String[]{};
    }

    /**
     * Method which provide the getting of the random color
     *
     * @param context instance of {@link Context}
     * @return {@link Integer} value of the color
     */
    @ColorInt
    public static int randomColor(@Nullable Context context) {
        try {
            String[] colors = context.getResources().getStringArray(R.array.bs_random_colors);
            int colorIndex = generateInt(0, colors.length - 1);
            return Color.parseColor(colors[colorIndex]);
        } catch (Exception ex) {
            Log.e(TAG, "randomColor: ", ex);
            return Color.parseColor("#607D8B");
        }
    }

}
