package com.artlite.bslibrary.helpers.abs;

import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.models.BSLoggableObject;

/**
 * Created by dlernatovich on 3/21/2017.
 */

public abstract class BSBaseHelper extends BSLoggableObject {

    /**
     * {@link String} constants of the TAG
     */
    protected static final String TAG = BSBaseHelper.class.getSimpleName();

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
     * Method which provide the validation for null elements
     *
     * @param objects objects for validate
     * @return validation result
     */
    public static boolean isNull(@Nullable final Object... objects) {
        return BSValidationHelper.isNull(objects);
    }
}
