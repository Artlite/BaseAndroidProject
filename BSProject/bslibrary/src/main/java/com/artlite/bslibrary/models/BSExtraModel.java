package com.artlite.bslibrary.models;

import android.support.annotation.NonNull;

import com.artlite.bslibrary.helpers.BSValidationHelper;

import java.io.Serializable;

/**
 * Method which provide the setting of the extra to {@link android.content.Intent}
 * Created by dlernatovich on 2/17/2017.
 *
 * @see com.artlite.bslibrary.helpers.BSLocalBroadcastHelper
 */

public class BSExtraModel<T extends BSParcelable> {

    private final String key;
    private final T object;

    /**
     * Method which provide the creating of the {@link BSExtraModel}
     *
     * @param key    key value
     * @param object instance of {@link Serializable} object
     */
    public BSExtraModel(@NonNull final String key, @NonNull final T object) {
        this.key = key;
        this.object = object;
    }

    /**
     * Method which provide the getting of the key value
     *
     * @return key value
     */
    public String getKey() {
        return key;
    }

    /**
     * Method which provide the getting of the {@link Serializable} object
     *
     * @return instance of {@link Serializable} object
     */
    public T getObject() {
        return object;
    }

    /**
     * Method which provide the validation of the object
     *
     * @return validation result
     */
    public boolean validate() {
        return BSValidationHelper.emptyValidate(key, object);
    }
}
