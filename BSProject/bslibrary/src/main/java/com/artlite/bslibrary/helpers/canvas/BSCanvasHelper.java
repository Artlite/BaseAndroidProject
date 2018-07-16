package com.artlite.bslibrary.helpers.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Helper which provide the functionality with the {@link Canvas}
 */
public final class BSCanvasHelper extends BSBaseHelper {

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
                                @IntRange(from = 0, to = 100) int alpha,
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

}
