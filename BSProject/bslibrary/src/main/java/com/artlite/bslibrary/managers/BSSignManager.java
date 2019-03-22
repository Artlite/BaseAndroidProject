package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.sign.BSSignHelper;

/**
 * Method which provide the getting of the {@link Context} independently
 */

public final class BSSignManager extends BSBaseManager {

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSSignManager instance;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSSignManager(context);
        } else {
            Log.e(TAG, "ContextManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSSignManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "ContextManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private BSSignManager(@Nullable final Context context) {
        super(context);
    }

    /**
     * Method which provide the getting of the MD5 sign information
     *
     * @return {@link String} value of the MD5 sign information
     */
    @Nullable
    public static String getMD5() {
        try {
            return getMD5(getInstance().getContext().getPackageName());
        } catch (Exception ex) {
            Log.e(TAG, "getMD5: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the getting of the MD5 sign information
     *
     * @param packageValue {@link String} value of the package name
     * @return {@link String} value of the MD5 sign information
     */
    @Nullable
    public static String getMD5(@Nullable String packageValue) {
        try {
            return BSSignHelper.getMD5(getInstance().getContext(), packageValue);
        } catch (Exception ex) {
            Log.e(TAG, "getMD5: ", ex);
        }
        return null;
    }

}
