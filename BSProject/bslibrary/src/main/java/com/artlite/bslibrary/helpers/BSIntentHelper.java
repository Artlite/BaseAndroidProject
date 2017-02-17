package com.artlite.bslibrary.helpers;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.callbacks.BSIntentCallback;

/**
 * Class which provide the {@link android.content.Intent} helping functional
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSIntentHelper {

    /**
     * Method which provide the getting of the object by filter value
     *
     * @param intent instance of {@link Intent}
     * @return
     */
    public static void get(@Nullable final Intent intent,
                           @Nullable final BSIntentCallback callback,
                           @Nullable final String... keys) {
        if (BSValidationHelper.emptyValidate(intent, callback, keys)) {
            for (final String key : keys) {
                if (BSValidationHelper.emptyValidate(key)) {
                    final Parcelable parcelable = intent.getParcelableExtra(key);
                    if (BSValidationHelper.emptyValidate(parcelable)) {
                        callback.onResult(key, parcelable);
                    }
                }
            }
        }
    }
}
