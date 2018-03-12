package com.artlite.bslibrary.managers;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

import java.lang.ref.WeakReference;

/**
 * Created by dlernatovich on 2/17/2017.
 */

abstract class BSBaseManager {
    //Tag
    protected static final String TAG = BSBaseManager.class.getName();
    //Cache size
    protected static final int K_CACHE_SIZE = 5 * 1024 * 1024; // 5MiB
    //Context reference
    private final WeakReference<Context> context;
    //Handler
    private final Handler handler;

    /**
     * Default constructor
     */
    public BSBaseManager(@NonNull final Context context) {
        this.context = new WeakReference<Context>(context);
        this.handler = new Handler();
    }

    /**
     * Method which provide the getting of the {@link Context}
     *
     * @return instance of {@link Context}
     */
    protected final Context getContext() {
        Context result = null;
        if (context != null) {
            result = context.get();
        }
        return result;
    }

    /**
     * Method which provide the getting of the {@link Handler}
     *
     * @return instance of {@link Handler}
     */
    protected final Handler getHandler() {
        return handler;
    }

    //==============================================================================================
    //                                     VALIDATION
    //==============================================================================================

    /**
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     */
    public static boolean isNull(@Nullable final Object... objects) {
        return BSValidationHelper.isNull(objects);
    }

    /**
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     */
    public static boolean nullValidate(@Nullable final Object... objects) {
        return BSValidationHelper.nullValidate(objects);
    }

    /**
     * Method which provide the empty validations
     *
     * @param objects objects for validate
     * @return validation results
     */
    public static boolean isEmpty(@Nullable final Object... objects) {
        return BSValidationHelper.isEmpty(objects);
    }

    /**
     * Method which provide the empty validations
     *
     * @param objects objects for validate
     * @return validation results
     */
    public static boolean emptyValidate(@Nullable final Object... objects) {
        return BSValidationHelper.emptyValidate(objects);
    }
}
