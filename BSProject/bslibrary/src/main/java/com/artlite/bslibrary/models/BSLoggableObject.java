package com.artlite.bslibrary.models;

import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

/**
 * Class which provide the loggable functional
 */

public abstract class BSLoggableObject {
    /**
     * Method which provide the show log for loggable object
     *
     * @param owner      owner object
     * @param methodName method name
     * @param error      error
     * @param object     object for show
     * @param <T>
     */
    protected static <T extends BSLoggableObject> void log(@Nullable final T owner,
                                                           @Nullable final String methodName,
                                                           @Nullable final Exception error,
                                                           @Nullable final Object object) {
        BSLogHelper.log(owner, methodName, error, object);
    }

    /**
     * Method which provide the show log for loggable object
     *
     * @param methodName method name
     * @param error      error
     * @param object     object for show
     * @param <T>
     */
    protected <T extends BSLoggableObject> void log(@Nullable final String methodName,
                                                    @Nullable final Exception error,
                                                    @Nullable final Object object) {
        log(this, methodName, error, object);
    }

    /**
     * Method which provide the validation
     *
     * @param objects objects
     * @return validation
     */
    protected static boolean validate(@Nullable final Object... objects) {
        return BSValidationHelper.emptyValidate(objects);
    }
}
