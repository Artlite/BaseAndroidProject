package com.artlite.bslibrary.helpers.log;

import android.util.Log;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

/**
 * Class which provide the log functional
 */

public final class BSLogHelper {
    /**
     * Method which provide the show log for loggable object
     *
     * @param owner      owner object
     * @param methodName method name
     * @param error      error
     * @param object     object for show
     */
    public static void log(@Nullable final Object owner,
                           @Nullable final String methodName,
                           @Nullable final Exception error,
                           @Nullable final Object object) {
        final StringBuilder builder = new StringBuilder();
        String TAG = BSLogHelper.class.getSimpleName();
        if (validate(owner)) {
            TAG = owner.getClass().getSimpleName();
            builder.append("Class: \t").append(TAG);
        }
        if (validate(methodName)) {
            builder.append("\nMethod:\t").append(methodName);
        }
        if (validate(error)) {
            builder.append("\nError: \t").append(error.toString());
        }
        if (validate(object)) {
            builder.append("\nObject:\t").append(object.toString());
        }
        if (error == null) {
            Log.e(TAG, builder.toString());
        } else {
            Log.i(TAG, builder.toString());
        }
    }

    /**
     * Method which provide the validation
     *
     * @param objects objects
     * @return validation
     */
    private static boolean validate(@Nullable final Object... objects) {
        return BSValidationHelper.emptyValidate(objects);
    }
}
