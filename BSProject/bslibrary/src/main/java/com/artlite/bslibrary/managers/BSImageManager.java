package com.artlite.bslibrary.managers;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.image.BSImageHelper;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Class which provide the download image functional for the {@link ImageView}
 * and it caching
 */

public final class BSImageManager {

    /**
     * Class which provide the ImageBitmap cache
     */
    private static class Cache {

        /**
         * {@link Integer} value of the cache size
         */
        private static final int K_CACHE_SIZE = 4 * 1024 * 1024; // 4MiB

        /**
         * Instance of the {@link LruCache}
         */
        private final LruCache<String, Drawable> cache;

        /**
         * Default constructor
         */
        public Cache() {
            cache = new LruCache<>(K_CACHE_SIZE);
        }

        /**
         * Method which provide the {@link Drawable} setting
         *
         * @param url      {@link String} value of the URL
         * @param drawable instance of the {@link Drawable}
         */
        public void setDrawable(@Nullable final String url,
                                @Nullable final Drawable drawable) {
            synchronized (cache) {
                if ((TextUtils.isEmpty(url) == false) &&
                        (drawable != null)) {
                    cache.put(url, drawable);
                }
            }
        }

        /**
         * Method which provide the getting drawable
         *
         * @param url {@link String} value of the URL
         * @return instance of the {@link Drawable}
         */
        @Nullable
        public final Drawable getDrawable(@Nullable final String url) {
            synchronized (cache) {
                if (TextUtils.isEmpty(url) == false) {
                    return cache.get(url);
                }
                return null;
            }
        }
    }

    /**
     * Instance of the {@link BSImageHelper}
     */
    private static BSImageManager instance;

    /**
     * Instance of the {@link Cache}
     */
    private final Cache cache;

    /**
     * Default constructor
     */
    private BSImageManager() {
        this.cache = new Cache();
    }

    /**
     * Method which provide the getting of the instance of the of the {@link BSImageManager}
     *
     * @return instance of the {@link BSImageManager}
     */
    public static BSImageManager getInstance() {
        if (instance == null) {
            instance = new BSImageManager();
        }
        return instance;
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public void load(@Nullable final ImageView imageView,
                     @Nullable final String url) {
        final String methodName = "load(imageView, url)";

        //Set previous cached image
        if (!BSValidationHelper.isEmpty(cache, imageView, url)) {
            final Drawable drawable = cache.getDrawable(url);
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        //Download the image
        BSImageHelper.load(imageView, url, new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e,
                                       String model,
                                       Target<GlideDrawable> target,
                                       boolean isFirstResource) {
                BSLogHelper.log(null, methodName, e, null);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource,
                                           String model,
                                           Target<GlideDrawable> target,
                                           boolean isFromMemoryCache,
                                           boolean isFirstResource) {
                BSLogHelper.log(null, methodName, null, "Image loaded: " + resource);
                if (imageView != null) {
                    imageView.setImageDrawable(resource);
                    if (cache != null) {
                        cache.setDrawable(url, resource);
                    }
                }
                return false;
            }
        });
    }
}
