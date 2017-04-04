package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.random.BSRandomHelper;

/**
 * Class which provide the random generating functional
 */

public final class BSRandomManager extends BSBaseManager {

    /**
     * Instance of {@link BSRandomManager}
     */
    private static BSRandomManager instance;

    /**
     * Method which provide the initialization of {@link BSRandomManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSRandomManager(context);
        } else {
            Log.e(TAG, "BSRandomManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSRandomManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSRandomManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "BSRandomManager should be initialized the Application singleton");
        }
        return instance;
    }


    /**
     * Default constructor
     *
     * @param context instance of {@link Context}
     */
    public BSRandomManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the generating of the random integer value
     *
     * @return current random integer value
     */
    public static int generateInt() {
        return BSRandomHelper.generateInt();
    }

    /**
     * Method which provide the generating of the random integer value
     *
     * @param max max value
     * @return current random integer value
     */
    public static int generateInt(int max) {
        return BSRandomHelper.generateInt(max);
    }

    /**
     * Method which provide the generating of the random integer value
     *
     * @param min min value
     * @param max max value
     * @return current random integer value
     */
    public static int generateInt(int min, int max) {
        return BSRandomHelper.generateInt(min, max);
    }

    /**
     * Method which provide the generating of the random String value with length
     *
     * @param length current random string length
     * @return generated String value
     */
    public static String generateString(int length) {
        return BSRandomHelper.generateString(length);
    }

    /**
     * Method which provide to generate of the random sentence
     *
     * @return generated sentence
     */
    public static String generateSentence(int count) {
        return BSRandomHelper.generateSentence(getInstance().getContext(), count);
    }
}
