package com.artlite.bslibrary.callbacks;

import android.support.annotation.Nullable;

/**
 * Library execution callback
 * Created by dlernatovich on 2/17/2017.
 */

public interface BSExecutionCallback<T extends Object> {
    /**
     * Method which provide the action when callback finish execution
     *
     * @param isSuccess if it success
     * @param error     instance of {@link Throwable}
     * @param result    instance of {@link Object}
     */
    <K extends Throwable> void onResult(boolean isSuccess,
                                        @Nullable final K error,
                                        @Nullable final T result);
}
