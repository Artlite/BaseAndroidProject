package com.artlite.bslibrary.helpers.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

import java.lang.ref.WeakReference;

import jp.wasabeef.glide.transformations.BitmapTransformation;

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
     * Class which provide the image loader class
     */
    public static final class LoadingTask {

        /**
         * {@link String} constants of the TAG
         */
        private static final String TAG = LoadingTask.class.getSimpleName();

        /**
         * Instance of the {@link RequestBuilder}
         */
        private RequestBuilder builder;

        /**
         * Instance of the {@link RequestOptions}
         */
        private RequestOptions options;

        /**
         * Instance of the {@link WeakReference}
         */
        private final WeakReference<ImageView> imageViewReference;

        /**
         * Instance of the {@link WeakReference}
         */
        private final WeakReference<Context> contextReference;

        /**
         * {@link String} constants of the url
         */
        private final String url;

        /**
         * Instance of the {@link Uri}
         */
        private final Uri uri;


        /**
         * Constructor which provide the create {@link LoadingTask} with parameters
         *
         * @param context   instance of the {@link Context}
         * @param imageView instance of the {@link ImageView}
         * @param url       {@link String} value of the image URL
         */
        public LoadingTask(@Nullable Context context,
                           @Nullable final ImageView imageView,
                           @Nullable final String url) {
            if (imageView != null) {
                this.imageViewReference = new WeakReference<>(imageView);
            } else {
                this.imageViewReference = null;
                Log.d(TAG, "LoadingTask: Image view is empty");
            }
            if (context != null) {
                this.contextReference = new WeakReference<>(context);
            } else {
                this.contextReference = null;
                Log.d(TAG, "LoadingTask: Context is empty");
            }
            if (!isNull(url, context)) {
                this.builder = Glide
                        .with(context)
                        .load(url);
            } else {
                this.builder = null;
            }
            this.uri = null;
            this.url = url;
        }

        /**
         * Constructor which provide the create {@link LoadingTask} with parameters
         *
         * @param context   instance of the {@link Context}
         * @param imageView instance of the {@link ImageView}
         * @param uri       instance of the {@link Uri}
         */
        public LoadingTask(@Nullable Context context,
                           @Nullable final ImageView imageView,
                           @Nullable final Uri uri) {
            if (imageView != null) {
                this.imageViewReference = new WeakReference<>(imageView);
            } else {
                this.imageViewReference = null;
                Log.d(TAG, "LoadingTask: Image view is empty");
            }
            if (context != null) {
                this.contextReference = new WeakReference<>(context);
            } else {
                this.contextReference = null;
                Log.d(TAG, "LoadingTask: Context is empty");
            }
            if (!isNull(uri, context)) {
                this.builder = Glide
                        .with(context)
                        .load(uri);
            } else {
                this.builder = null;
            }
            this.uri = uri;
            this.url = null;
        }

        /**
         * Method which provide the setting {@link Integer} value of the placeholder
         *
         * @param placeholder {@link Integer} value of the placeholder
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setPlaceholder(@DrawableRes int placeholder) {
            this.options = this.getOptions().placeholder(placeholder);
            return this;
        }

        /**
         * Method which provide the setting {@link Integer} value of the error
         *
         * @param error {@link Integer} value of the error
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setError(@DrawableRes int error) {
            this.options = this.getOptions().error(error);
            return this;
        }

        /**
         * Method which provide the setting of the width and height
         *
         * @param height {@link Integer} value of the height
         * @param width  {@link Integer} value of the width
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setSize(int width, int height) {
            if ((width > 0) && (height > 0)) {
                this.options = this.getOptions().override(width, height);
            } else {
                Log.d(TAG, "setSize: width or height is less or equals to zero");
            }
            return this;
        }

        /**
         * Method which provide the setting position type
         *
         * @param positionType instance of the {@link ImagePositionType}
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setPositionType(@Nullable ImagePositionType positionType) {
            if (this.builder == null) {
                Log.d(TAG, "setPositionType: builder is empty");
                return this;
            }
            if (positionType == null) {
                Log.d(TAG, "setPositionType: Position type is empty");
                return this;
            }
            switch (positionType) {
                case NONE: {
                    break;
                }
                case FIT_CENTER: {
                    this.options = this.getOptions().fitCenter();
                    break;
                }
                case CENTER_CROP: {
                    this.options = this.getOptions().centerCrop();
                    break;
                }
                default: {
                    break;
                }
            }
            return this;
        }

        /**
         * Method which provide the setting position type
         *
         * @param transformation instance of the {@link BitmapTransformation}
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setTransformation(@Nullable BitmapTransformation transformation) {
            if (this.builder == null) {
                Log.d(TAG, "setTransformation: builder is empty");
                return this;
            }
            if (transformation == null) {
                Log.d(TAG, "setTransformation: Transformation is empty");
                return this;
            }
            this.options = this.getOptions().transform(transformation);
            return this;
        }

        /**
         * Method which provide the setting position type
         *
         * @param transformation instance of the {@link BitmapTransformation}
         * @return instance of the {@link LoadingTask}
         */
        @NonNull
        public LoadingTask setTransformation(@Nullable MultiTransformation transformation) {
            if (this.builder == null) {
                Log.d(TAG, "setTransformation: builder is empty");
                return this;
            }
            if (transformation == null) {
                Log.d(TAG, "setTransformation: Transformation is empty");
                return this;
            }
            this.options = this.getOptions().transform(transformation);
            return this;
        }

        /**
         * Method which provide the download of the url to the {@link ImageView}
         */
        public void download() {
            this.download(null);
        }

        /**
         * Method which provide the download of the url to the {@link ImageView}
         *
         * @param target instance of the {@link SimpleTarget}
         */
        public void download(@Nullable final SimpleTarget<Bitmap> target) {
            if (this.builder == null) {
                Log.d(TAG, "download: builder is empty");
                return;
            }
            final ImageView imageView = this.getImageView();
            final Context context = this.getContext();
            if (imageView != null) {
                if (target == null) {
                    this.builder.apply(this.getOptions()).into(imageView);
                } else if ((context != null) && ((this.url != null) || (this.uri != null))) {
                    this.builder = Glide
                            .with(context)
                            .asBitmap()
                            .load((this.uri == null) ? this.url : this.uri);
                    this.builder.apply(this.getOptions()).into(target);
                }
            } else {
                Log.d(TAG, "download: Image view is empty");
            }
        }

        /**
         * Method which provide the getting of the {@link RequestOptions}
         *
         * @return instance of the {@link RequestOptions}
         */
        @NonNull
        protected RequestOptions getOptions() {
            if (this.options == null) {
                this.options = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate();
            }
            return this.options;
        }

        /**
         * Method which provide the getting {@link Context}
         *
         * @return instance of the {@link Context}
         */
        @Nullable
        protected Context getContext() {
            return (this.contextReference == null)
                    ? null : this.contextReference.get();
        }

        /**
         * Method which provide the getting {@link ImageView}
         *
         * @return instance of the {@link ImageView}
         */
        @Nullable
        protected ImageView getImageView() {
            return (this.imageViewReference == null)
                    ? null : this.imageViewReference.get();
        }
    }

    /**
     * Method which provide the creating of the {@link LoadingTask}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     * @return instance of the {@link LoadingTask}
     */
    @NonNull
    public static LoadingTask create(@Nullable Context context,
                                     @Nullable final ImageView imageView,
                                     @Nullable final String url) {
        return new LoadingTask(context, imageView, url);
    }

    /**
     * Method which provide the creating of the {@link LoadingTask}
     *
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param uri       {@link Uri} value of the image URL
     * @return instance of the {@link LoadingTask}
     */
    @NonNull
    public static LoadingTask create(@Nullable Context context,
                                     @Nullable final ImageView imageView,
                                     @Nullable final Uri uri) {
        return new LoadingTask(context, imageView, uri);
    }

}

// KEEP IT FOR NOW
//    /**
//     * Method which provide the loading of the image by it {@link String} value of the
//     * URL for instance of the {@link ImageView}
//     *
//     * @param context           instance of the {@link Context}
//     * @param imageView         instance of the {@link ImageView}
//     * @param url               {@link String} value of the image URL
//     * @param width             {@link Integer} value of the width
//     * @param height            {@link Integer} value of the height
//     * @param imagePositionType instance of the {@link ImagePositionType}
//     * @param transformation    instance of the {@link BitmapTransformation}
//     */
//    public static void load(@Nullable Context context,
//                            @Nullable final ImageView imageView,
//                            @Nullable final String url,
//                            int width,
//                            int height,
//                            @DrawableRes int placeholder,
//                            @Nullable ImagePositionType imagePositionType,
//                            @Nullable BitmapTransformation transformation) {
//        final String methodName = "load(imageView, url, callback)";
//        if (imagePositionType == null) {
//            imagePositionType = ImagePositionType.NONE;
//        }
//        if (isNull(imageView, url, context)) {
//            return;
//        }
//        DrawableRequestBuilder builder = Glide
//                .with(context)
//                .load(url)
//                .placeholder(placeholder)
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        switch (imagePositionType) {
//            case NONE: {
//                break;
//            }
//            case FIT_CENTER: {
//                builder = builder.fitCenter();
//                break;
//            }
//            case CENTER_CROP: {
//                builder = builder.centerCrop();
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        if (transformation != null) {
//            builder = builder.transform(transformation);
//        }
//        if ((width > 0) && (height > 0)) {
//            builder = builder.override(width, height);
//        }
//        builder.into(imageView);
//    }
