package com.artlite.bslibrary.helpers.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class which provide the base functional for {@link Bitmap}
 */

public final class BSBitmapHelper extends BSBaseHelper {
    private static final int K_MAX_HEIGHT = 1920;
    private static final int K_MAX_WIDTH = 1920;
    private static final int K_MAX_RATIO = K_MAX_HEIGHT * K_MAX_WIDTH;

    /**
     * Method which provide the scaling bitmap if needed
     *
     * @param bitmap instance of {@link Bitmap}
     * @return scaled {@link Bitmap}
     */
    @Nullable
    public static Bitmap scale(@Nullable final Bitmap bitmap) {
        return scale(bitmap, K_MAX_WIDTH, K_MAX_HEIGHT);
    }

    /**
     * Method which provide the scaling bitmap if needed
     *
     * @param bitmap instance of {@link Bitmap}
     * @return scaled {@link Bitmap}
     */
    @Nullable
    public static Bitmap scale(@Nullable final Bitmap bitmap,
                               @IntRange(from = 1, to = K_MAX_WIDTH) int maxWidth,
                               @IntRange(from = 1, to = K_MAX_WIDTH) int maxHeight) {
        final int currentRatio = (bitmap == null)
                ? K_MAX_RATIO : (bitmap.getWidth() * bitmap.getHeight());
        if (currentRatio > K_MAX_RATIO) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxHeight / (float) maxWidth;
            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            return Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, false);
        }
        return bitmap;
    }

    /**
     * Method which provide the convert the {@link Bitmap} to {@link Byte} array
     *
     * @param bitmap instance of {@link Bitmap}
     * @return instance of {@link Byte} array
     */
    @NonNull
    public static byte[] convert(@Nullable final Bitmap bitmap) {
        final String methodName = "byte[] convert(bitmap)";
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            scale(bitmap).compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        } catch (Exception ex) {
            log(null, methodName, ex, bitmap);
            return new byte[0];
        }
    }

    /**
     * Method which provide the convert the {@link View} to the {@link Bitmap}
     *
     * @param view instance of {@link View}
     * @return instance of the {@link Bitmap}
     */
    @NonNull
    public static Bitmap convert(@Nullable final View view) {
        final String methodName = "Bitmap convert(view)";
        try {
            view.measure(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            final Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                    view.getMeasuredHeight(),
                    Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap);
            view.layout(0, 0, view.getMeasuredWidth(),
                    view.getMeasuredHeight());
            view.draw(canvas);
            return bitmap;
        } catch (Exception ex) {
            log(null, methodName, ex, null);
            return null;
        }
    }

    /**
     * Method which provide the converting of the {@link Byte} array to {@link Bitmap}
     *
     * @param bytes instance of {@link Byte}
     * @return instance of {@link Bitmap}
     */
    @Nullable
    public static Bitmap convert(@Nullable final byte[] bytes) {
        final String methodName = "Bitmap convert(bytes)";
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        } catch (Exception ex) {
            log(null, methodName, ex, bytes);
            return null;
        }
    }

    /**
     * Method which provide the saving of the {@link Bitmap} to file system
     *
     * @param context instance of {@link Context}
     * @param bitmap  instance of {@link Bitmap}
     * @param name    name value
     */
    public static void cacheBitmap(final Context context,
                                   final Bitmap bitmap,
                                   final String name) {
        if (isEmpty(context, name)) {
            return;
        }
        final String methodName = "void cacheBitmap(context, bitmap, name)";
        String filename = String.format("%s%s%s", context.getCacheDir(), File.separator, name);
        log(null, methodName, null, filename);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            log(null, methodName, e, null);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log(null, methodName, e, null);
            }
        }
    }

    /**
     * Method which provide the getting of the cached {@link Bitmap}
     *
     * @param context instance of {@link Context}
     * @param name    name value
     * @return instance of {@link Bitmap}
     */
    public static Bitmap getCachedBitmap(@Nullable final Context context,
                                         @Nullable final String name) {
        if (isEmpty(context, name)) {
            return null;
        }
        String filename = String.format("%s%s%s", context.getCacheDir(), File.separator, name);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(filename, options);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
