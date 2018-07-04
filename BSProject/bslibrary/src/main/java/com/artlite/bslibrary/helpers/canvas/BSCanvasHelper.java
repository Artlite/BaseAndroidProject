package com.artlite.bslibrary.helpers.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
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
     * @param context   instance of the {@link Context}
     * @param imageView instance of the {@link ImageView}
     * @param bitmap    instance of the {@link Bitmap}
     * @param color     {@link Integer} value of the color
     * @param xTop      {@link Integer} value of the x top
     * @param yTop      {@link Integer} value of the y top
     * @param xBottom   {@link Integer} value of the x bottom
     * @param yBottom   {@link Integer} value of the y bottom
     * @return instance of the {@link Canvas}
     */
    @Nullable
    public static Canvas drawRect(@Nullable Context context,
                                  @Nullable ImageView imageView,
                                  @Nullable Bitmap bitmap,
                                  @ColorInt int color,
                                  int xTop,
                                  int yTop,
                                  int xBottom,
                                  int yBottom) {
        Canvas canvas = null;
        Paint paint = null;
        try {
            canvas = new Canvas(bitmap);
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            canvas.drawRect(xTop, yTop, xBottom, yBottom, paint);

        } catch (Exception ex) {
            Log.e(TAG, "drawRect: ", ex);
            canvas = null;
        }
        return canvas;
    }

}
