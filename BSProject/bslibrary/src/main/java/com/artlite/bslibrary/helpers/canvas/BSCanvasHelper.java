package com.artlite.bslibrary.helpers.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Helper which provide the functionality with the {@link Canvas}
 */
public final class BSCanvasHelper extends BSBaseHelper {

    /**
     * Interface which provide the canvas callback
     */
    public interface OnCanvasCallback {

        /**
         * Method which provide the {@link Paint} preparing
         *
         * @param paint instance of the {@link Paint}
         */
        void canvasPaintPreparing(@NonNull final Paint paint);
    }

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSCanvasHelper.class.getSimpleName();

    /**
     * Method which provide the drawing of the {@link Canvas} on {@link Bitmap} image
     *
     * @param imageView instance of the {@link ImageView}
     * @param bitmap    instance of the {@link Bitmap}
     * @param color     {@link Integer} value of the color
     * @param left      {@link Integer} value of the x top
     * @param top       {@link Integer} value of the y top
     * @param right     {@link Integer} value of the x bottom
     * @param bottom    {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public static Rect drawRect(@Nullable ImageView imageView,
                                @Nullable Bitmap bitmap,
                                @ColorInt int color,
                                @IntRange(from = 0, to = 255) int alpha,
                                int left,
                                int top,
                                int right,
                                int bottom) {
        Canvas canvas = null;
        Paint paint = null;
        Rect rect = new Rect(left, top, right, bottom);
        try {
            if (bitmap != null) {
                canvas = new Canvas(bitmap);
            }
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            paint.setAlpha(alpha);
            if (canvas != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Log.e(TAG, "drawRect: ", ex);
            canvas = null;
            rect = null;
        }
        return rect;
    }

    /**
     * Method which provide the drawing of the {@link Canvas} on {@link Bitmap} image
     *
     * @param imageView instance of the {@link ImageView}
     * @param bitmap    instance of the {@link Bitmap}
     * @param color     {@link Integer} value of the color
     * @param left      {@link Integer} value of the x top
     * @param top       {@link Integer} value of the y top
     * @param right     {@link Integer} value of the x bottom
     * @param bottom    {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    public static Rect drawRectRound(@Nullable ImageView imageView,
                                     @Nullable Bitmap bitmap,
                                     @ColorInt int color,
                                     @IntRange(from = 0, to = 255) int alpha,
                                     int left,
                                     int top,
                                     int right,
                                     int bottom) {
        Canvas canvas = null;
        Paint paint = null;
        Rect rect = new Rect(left, top, right, bottom);
        int width = rect.width();
        int height = rect.height();
        int required = (width < height) ? width : height;
        final int cornerDivider = 5;
        try {
            if (bitmap != null) {
                canvas = new Canvas(bitmap);
            }
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            paint.setAlpha(alpha);
            if (canvas != null) {
                canvas.drawRoundRect(left, top, right, bottom,
                        required / cornerDivider, required / cornerDivider, paint);
            }
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Log.e(TAG, "drawRect: ", ex);
            canvas = null;
            rect = null;
        }
        return rect;
    }

    /**
     * Method which provide the drawing of the {@link Canvas} on {@link Bitmap} image
     *
     * @param imageView instance of the {@link ImageView}
     * @param bitmap    instance of the {@link Bitmap}
     * @param left      {@link Integer} value of the x top
     * @param top       {@link Integer} value of the y top
     * @param right     {@link Integer} value of the x bottom
     * @param bottom    {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public static Rect drawRect(@Nullable final ImageView imageView,
                                @Nullable final Bitmap bitmap,
                                int left,
                                int top,
                                int right,
                                int bottom,
                                @Nullable final OnCanvasCallback callback) {
        Canvas canvas = null;
        Paint paint = null;
        Rect rect = new Rect(left, top, right, bottom);
        try {
            if (bitmap != null) {
                canvas = new Canvas(bitmap);
            }
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            if (callback != null) {
                callback.canvasPaintPreparing(paint);
            }
            if (canvas != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Log.e(TAG, "drawRect: ", ex);
            canvas = null;
            rect = null;
        }
        return rect;
    }

    /**
     * Method which provide the drawing of the {@link Canvas} on {@link Bitmap} image
     *
     * @param imageView instance of the {@link ImageView}
     * @param bitmap    instance of the {@link Bitmap}
     * @param left      {@link Integer} value of the x top
     * @param top       {@link Integer} value of the y top
     * @param right     {@link Integer} value of the x bottom
     * @param bottom    {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    public static Rect drawRectRound(@Nullable final ImageView imageView,
                                     @Nullable final Bitmap bitmap,
                                     int left,
                                     int top,
                                     int right,
                                     int bottom,
                                     @Nullable final OnCanvasCallback callback) {
        Canvas canvas = null;
        Paint paint = null;
        Rect rect = new Rect(left, top, right, bottom);
        int width = rect.width();
        int height = rect.height();
        int required = (width < height) ? width : height;
        final int cornerDivider = 5;
        try {
            if (bitmap != null) {
                canvas = new Canvas(bitmap);
            }
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            if (callback != null) {
                callback.canvasPaintPreparing(paint);
            }
            if (canvas != null) {
                canvas.drawRoundRect(left, top, right, bottom,
                        required / cornerDivider, required / cornerDivider, paint);
            }
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception ex) {
            Log.e(TAG, "drawRect: ", ex);
            canvas = null;
            rect = null;
        }
        return rect;
    }

}
