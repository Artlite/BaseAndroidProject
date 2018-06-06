package com.artlite.bslibrary.managers;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.image.BSImageHelper;

/**
 * Class which provide the download image functional for the {@link ImageView}
 * and it caching
 */

public final class BSImageManager extends BSBaseManager {

    /**
     * Instance of the {@link BSImageHelper}
     */
    private static BSImageManager instance;

    /**
     * Default constructor
     */
    private BSImageManager(@Nullable Context context) {
        super(context);
    }

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSImageManager(context);
        } else {
            Log.e(TAG, "BSImageManager is already created");
        }
    }

    /**
     * Method which provide the getting of the instance of the of the {@link BSImageManager}
     *
     * @return instance of the {@link BSImageManager}
     */
    @NonNull
    protected static BSImageManager getInstance() {
        return instance;
    }

    /**
     * Method which provide the creating of the {@link BSImageHelper.LoadingTask}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     * @return instance of the {@link BSImageHelper.LoadingTask}
     */
    @NonNull
    public static BSImageHelper.LoadingTask create(@Nullable final ImageView imageView,
                                                   @Nullable final String url) {
        return new BSImageHelper.LoadingTask(getInstance().getContext(), imageView, url);
    }

    /**
     * Method which provide the creating of the {@link BSImageHelper.LoadingTask}
     *
     * @param imageView instance of the {@link ImageView}
     * @param uri       {@link Uri} value of the image URL
     * @return instance of the {@link BSImageHelper.LoadingTask}
     */
    @NonNull
    public static BSImageHelper.LoadingTask create(@Nullable final ImageView imageView,
                                                   @Nullable final Uri uri) {
        return new BSImageHelper.LoadingTask(getInstance().getContext(), imageView, uri);
    }

}
