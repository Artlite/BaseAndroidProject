package com.artlite.bslibrary.callbacks;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.artlite.bslibrary.helpers.intent.BSIntentHelper;

/**
 * Class which provide the functional for {@link BSIntentHelper}
 * Created by dlernatovich on 2/17/2017.
 */

public interface BSIntentCallback {
    /**
     * Method which provide the on result functional
     *
     * @param object instance of {@link Parcelable} object
     */
    void onResult(@NonNull final String key, @NonNull final Parcelable object);
}
