package com.artlite.bslibrary.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.canvas.BSCanvasHelper;
import com.artlite.bslibrary.managers.BSRandomManager;

/**
 * Class which provide the extendable functional for the
 */
@SuppressLint("AppCompatCustomView")
public final class BSImageView extends ImageView {

    /**
     * Constructor which provide to create of the {@link BSImageView} with parameters
     *
     * @param context instance of the {@link Context}
     */
    public BSImageView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide to create of the {@link BSImageView} with parameters
     *
     * @param context instance of the {@link Context}
     * @param attrs   instance of the {@link AttributeSet}
     */
    public BSImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide to create of the {@link BSImageView} with parameters
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the def style
     */
    public BSImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Constructor which provide to create of the {@link BSImageView} with parameters
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the def style
     * @param defStyleRes  {@link Integer} value of the default style resources
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BSImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Method which provide the on touch functional
     *
     * @param event instance of the {@link MotionEvent}
     * @return {@link Boolean} value about on touch event information
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                if (rect != null) {
//                    if (rect.contains((int) x, (int) y)) {
//                        Toast.makeText(this, "Rectangle clicked", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            default: {
//                break;
//            }
//        }
        return true;
    }

    /**
     * Method which provide the rectangle drawing
     *
     * @param bitmap instance of the {@link Bitmap}
     * @param color  {@link Integer} value of the color
     * @param left   {@link Integer} value of the x top
     * @param top    {@link Integer} value of the y top
     * @param right  {@link Integer} value of the x bottom
     * @param bottom {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public Rect drawRect(@Nullable Bitmap bitmap,
                         @ColorRes int color,
                         @IntRange(from = 0, to = 100) int alpha,
                         int left,
                         int top,
                         int right,
                         int bottom) {
        return this.drawRect(bitmap, color, alpha, left, top, right, bottom, null);
    }

    /**
     * Method which provide the rectangle drawing
     *
     * @param bitmap instance of the {@link Bitmap}
     * @param color  {@link Integer} value of the color
     * @param left   {@link Integer} value of the x top
     * @param top    {@link Integer} value of the y top
     * @param right  {@link Integer} value of the x bottom
     * @param bottom {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public Rect drawRect(@Nullable Bitmap bitmap,
                         @ColorRes int color,
                         @IntRange(from = 0, to = 100) int alpha,
                         int left,
                         int top,
                         int right,
                         int bottom,
                         @Nullable String id) {
        return BSCanvasHelper.drawRect(this,
                bitmap, getResources().getColor(color), alpha,
                left, top, right, bottom);
    }

    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================

    /**
     * Class which providing of the inner rect
     */
    protected static class InnerRect {

        /**
         * {@link Integer} constants of the ID length
         */
        private static final int K_ID_LENGTH = 50;

        /**
         * {@link String} value of the id
         */
        private final String id;

        /**
         * Instance of the {@link Rect}
         */
        private final Rect rect;

        /**
         * Constructor which provide the creating of the {@link InnerRect} with parameters
         *
         * @param id   {@link String} value of the ID
         * @param rect instance of the {@link Rect}
         */
        public InnerRect(String id, Rect rect) {
            this.id = (id == null)
                    ? BSRandomManager.generateString(K_ID_LENGTH, true) : id;
            this.rect = rect;
        }

        /**
         * Method which the equaling functional
         *
         * @param object instance of the {@link Object}
         * @return {@link Boolean} value if it equaling
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            InnerRect innerRect = (InnerRect) object;
            return id != null ? id.equals(innerRect.id) : innerRect.id == null;
        }

        /**
         * Method which provide the {@link Integer} value of the hash code
         *
         * @return {@link Integer} value of the hash code
         */
        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

        /**
         * Method which provide the to {@link String} functionality
         *
         * @return instance of the {@link String}
         */
        @Override
        public String toString() {
            return "InnerRect{" +
                    "id='" + id + '\'' +
                    ", rect=" + rect +
                    '}';
        }
    }
}
