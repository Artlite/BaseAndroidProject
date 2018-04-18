package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.image.BSImageHelper;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

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
    public static BSImageManager getInstance() {
        return instance;
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    R.drawable.ic_glade_placeholder);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    -1, -1, R.drawable.ic_glade_placeholder,
                    imagePositionType, transformation);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView   instance of the {@link ImageView}
     * @param url         {@link String} value of the image URL
     * @param placeholder {@link Integer} drawable of placeholder
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            @DrawableRes int placeholder) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url, placeholder);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView   instance of the {@link ImageView}
     * @param url         {@link String} value of the image URL
     * @param placeholder {@link Integer} drawable of placeholder
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            @DrawableRes int placeholder,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    -1, -1, placeholder,
                    imagePositionType, transformation);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     * @param width     {@link Integer} value of the width
     * @param height    {@link Integer} value of the height
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, R.drawable.ic_glade_placeholder);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView   instance of the {@link ImageView}
     * @param url         {@link String} value of the image URL
     * @param width       {@link Integer} value of the width
     * @param height      {@link Integer} value of the height
     * @param placeholder {@link Integer} drawable of placeholder
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, placeholder);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link BSImageHelper.ImagePositionType}
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, R.drawable.ic_glade_placeholder, imagePositionType);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param placeholder       {@link Integer} drawable of placeholder
     * @param imagePositionType instance of the {@link BSImageHelper.ImagePositionType}
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, placeholder, imagePositionType);
        }
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link BSImageHelper.ImagePositionType}
     * @param transformation    instance of the {@link BitmapTransformation}
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, R.drawable.ic_glade_placeholder,
                    imagePositionType, transformation);
        }
    }


    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param placeholder       {@link Integer} drawable of placeholder
     * @param imagePositionType instance of the {@link BSImageHelper.ImagePositionType}
     * @param transformation    instance of the {@link BitmapTransformation}
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable BSImageHelper.ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        if (instance != null) {
            BSImageHelper.load(instance.getContext(), imageView, url,
                    width, height, placeholder, imagePositionType, transformation);
        }
    }
}
