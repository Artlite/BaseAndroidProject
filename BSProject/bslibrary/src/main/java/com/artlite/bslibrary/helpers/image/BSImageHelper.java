package com.artlite.bslibrary.helpers.image;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Class which provide the helping functional which related to {@link ImageView}
 */

public final class BSImageHelper extends BSBaseHelper {

    /**
     * Enum which provide the image crop style
     */
    public enum ImagePositionType {
        NONE, CENTER_CROP, FIT_CENTER
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final String url,
                            @DrawableRes int placeholder) {
        load(context, imageView, url, -1, -1,
                placeholder, null, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param uri       instance of the {@link Uri}
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final Uri uri,
                            @DrawableRes int placeholder) {
        load(context, imageView, uri, -1, -1,
                placeholder, null, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     * @param width     {@link Integer} value of the width
     * @param height    {@link Integer} value of the height
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder) {
        load(context, imageView, url, width, height,
                placeholder, null, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param uri       instance of the {@link Uri}
     * @param width     {@link Integer} value of the width
     * @param height    {@link Integer} value of the height
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final Uri uri,
                            int width,
                            int height,
                            @DrawableRes int placeholder) {
        load(context, imageView, uri, width, height,
                placeholder, null, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context           instance of the {@link Context}
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link ImagePositionType}
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable ImagePositionType imagePositionType) {
        load(context, imageView, url, width, height,
                placeholder, imagePositionType, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context           instance of the {@link Context}
     * @param imageView         instance of the {@link ImageView}
     * @param uri               instance of the {@link Uri}
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link ImagePositionType}
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final Uri uri,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable ImagePositionType imagePositionType) {
        load(context, imageView, uri, width, height,
                placeholder, imagePositionType, null);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context           instance of the {@link Context}
     * @param imageView         instance of the {@link ImageView}
     * @param url               {@link String} value of the image URL
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link ImagePositionType}
     * @param transformation    instance of the {@link BitmapTransformation}
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final String url,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        final String methodName = "load(imageView, url, callback)";
        if (imagePositionType == null) {
            imagePositionType = ImagePositionType.NONE;
        }
        if (isNull(imageView, url, context)) {
            return;
        }
        DrawableRequestBuilder builder = Glide
                .with(context)
                .load(url)
                .placeholder(placeholder)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        switch (imagePositionType) {
            case NONE: {
                break;
            }
            case FIT_CENTER: {
                builder = builder.fitCenter();
                break;
            }
            case CENTER_CROP: {
                builder = builder.centerCrop();
                break;
            }
            default: {
                break;
            }
        }
        if (transformation != null) {
            builder = builder.transform(transformation);
        }
        if ((width > 0) && (height > 0)) {
            builder = builder.override(width, height);
        }
        builder.into(imageView);
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param context           instance of the {@link Context}
     * @param imageView         instance of the {@link ImageView}
     * @param uri               instance of the {@link Uri}
     * @param width             {@link Integer} value of the width
     * @param height            {@link Integer} value of the height
     * @param imagePositionType instance of the {@link ImagePositionType}
     * @param transformation    instance of the {@link BitmapTransformation}
     */
    public static void load(@Nullable Context context,
                            @Nullable final ImageView imageView,
                            @Nullable final Uri uri,
                            int width,
                            int height,
                            @DrawableRes int placeholder,
                            @Nullable ImagePositionType imagePositionType,
                            @Nullable BitmapTransformation transformation) {
        final String methodName = "load(imageView, url, callback)";
        if (imagePositionType == null) {
            imagePositionType = ImagePositionType.NONE;
        }
        if (isNull(imageView, uri, context)) {
            return;
        }
        DrawableRequestBuilder builder = Glide
                .with(context)
                .load(uri)
                .placeholder(placeholder)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        switch (imagePositionType) {
            case NONE: {
                break;
            }
            case FIT_CENTER: {
                builder = builder.fitCenter();
                break;
            }
            case CENTER_CROP: {
                builder = builder.centerCrop();
                break;
            }
            default: {
                break;
            }
        }
        if (transformation != null) {
            builder = builder.transform(transformation);
        }
        if ((width > 0) && (height > 0)) {
            builder = builder.override(width, height);
        }
        builder.into(imageView);
    }
}
