package com.artlite.bslibrary.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.canvas.BSCanvasHelper;
import com.artlite.bslibrary.managers.BSRandomManager;

import java.util.HashSet;

/**
 * Class which provide the extendable functional for the
 */
@SuppressLint("AppCompatCustomView")
public final class BSImageView extends AppCompatImageView {

    /**
     * {@link String} constants of the TAG
     */
    private static final String TAG = BSImageView.class.getSimpleName();

    /**
     * Interface which provide the callback functionality
     */
    public interface OnImageViewCallback {

        /**
         * Method which provide the action when the {@link Rect} added to the {@link BSImageView}
         *
         * @param imageView instance of the {@link BSImageView}
         * @param id        {@link String} value of the rect ID
         * @param rect      instance of the {@link Rect}
         */
        void baseImageViewRectAdded(@NonNull BSImageView imageView,
                                    @NonNull String id,
                                    @NonNull Rect rect);

        /**
         * Method which provide the action when the {@link Rect} clicked
         *
         * @param imageView instance of the {@link BSImageView}
         * @param id        {@link String} value of the rect ID
         * @param rect      instance of the {@link Rect}
         */
        void baseImageViewRectClicked(@NonNull BSImageView imageView,
                                      @NonNull String id,
                                      @NonNull Rect rect);

    }

    /**
     * Instance of the {@link HashSet}
     */
    private final HashSet<InnerRect> rects = new HashSet<>();

    /**
     * Instance of the {@link OnImageViewCallback}
     */
    private OnImageViewCallback callback;

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
    public BSImageView(Context context,
                       @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide to create of the {@link BSImageView} with parameters
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the def style
     */
    public BSImageView(Context context,
                       @Nullable AttributeSet attrs,
                       int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the on touch functional
     *
     * @param event instance of the {@link MotionEvent}
     * @return {@link Boolean} value about on touch event information
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                for (InnerRect innerRect : this.rects) {
                    if (innerRect.getRect().contains(x, y)) {
                        if (this.callback != null) {
                            this.callback.baseImageViewRectClicked(this,
                                    innerRect.getId(), innerRect.getRect());
                        }
                        break;
                    }
                }
            }
            default: {
                break;
            }
        }
        return true;
    }

    /**
     * Method which provide the rectangle drawing
     *
     * @param color  {@link Integer} value of the color
     * @param left   {@link Integer} value of the x top
     * @param top    {@link Integer} value of the y top
     * @param right  {@link Integer} value of the x bottom
     * @param bottom {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public Rect drawRect(@ColorRes int color,
                         @IntRange(from = 0, to = 100) int alpha,
                         int left,
                         int top,
                         int right,
                         int bottom) {
        return this.drawRect(color, alpha, left, top, right, bottom, null);
    }

    /**
     * Method which provide the rectangle drawing
     *
     * @param color  {@link Integer} value of the color
     * @param left   {@link Integer} value of the x top
     * @param top    {@link Integer} value of the y top
     * @param right  {@link Integer} value of the x bottom
     * @param bottom {@link Integer} value of the y bottom
     * @return instance of the {@link Rect}
     */
    @Nullable
    public Rect drawRect(@ColorRes int color,
                         @IntRange(from = 0, to = 100) int alpha,
                         int left,
                         int top,
                         int right,
                         int bottom,
                         @Nullable String id) {
        final Rect rect = BSCanvasHelper.drawRect(this,
                this.getBitmap(), getResources().getColor(color), alpha,
                left, top, right, bottom);
        if (rect != null) {
            this.rects.add(new InnerRect(id, rect));
            if (this.callback != null) {
                this.callback.baseImageViewRectAdded(this,
                        id, rect);
            }
        }
        return rect;
    }

    /**
     * Method which provide the getting {@link Bitmap}
     *
     * @return instance of the {@link Bitmap}
     */
    @Nullable
    public Bitmap getBitmap() {
        try {
            return ((BitmapDrawable) this.getDrawable()).getBitmap();
        } catch (Exception ex) {
            Log.e(TAG, "getBitmap: ", ex);
            return null;
        }
    }

    /**
     * Method which provide the setting of the {@link OnImageViewCallback}
     *
     * @param callback instance of the {@link OnImageViewCallback}
     */
    public void setCallback(@Nullable OnImageViewCallback callback) {
        this.callback = callback;
    }

    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================

    /**
     * Class which providing of the inner rect
     */
    private static class InnerRect implements Parcelable {

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
        public InnerRect(@Nullable String id, @NonNull Rect rect) {
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

        /**
         * Method which provide the getting object
         *
         * @return instance of the {@link Object}
         */
        @NonNull
        public String getId() {
            return id;
        }

        /**
         * Method which provide the getting object
         *
         * @return instance of the {@link Object}
         */
        @NonNull
        public Rect getRect() {
            return rect;
        }

        //==========================================================================================
        //                                  PARCELABLE
        //==========================================================================================

        /**
         * Method which provide the content description
         *
         * @return {@link Integer} value of the content description
         */
        @Override
        public int describeContents() {
            return 0;
        }

        /**
         * Method which provide the writing to parcel
         *
         * @param dest  instance of the {@link Parcel}
         * @param flags {@link Integer} value of the flags
         */
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeParcelable(this.rect, flags);
        }

        /**
         * Constructor which provide to create the object from the {@link Parcel}
         *
         * @param in instance of the {@link Parcel}
         */
        protected InnerRect(Parcel in) {
            this.id = in.readString();
            this.rect = in.readParcelable(Rect.class.getClassLoader());
        }

        /**
         * Class which provide the creator for the {@link InnerRect}
         */
        public static final Parcelable.Creator<InnerRect> CREATOR =
                new Parcelable.Creator<InnerRect>() {
                    @Override
                    public InnerRect createFromParcel(Parcel source) {
                        return new InnerRect(source);
                    }

                    @Override
                    public InnerRect[] newArray(int size) {
                        return new InnerRect[size];
                    }
                };
    }
}

/**
 * HOW TO ADD ZOOM
 * <com.otaliastudios.zoom.ZoomLayout
 * android:id="@+id/layout_zoom"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:scrollbars="vertical|horizontal"
 * app:hasClickableChildren="true"
 * app:horizontalPanEnabled="true"
 * app:maxZoom="5.0"
 * app:maxZoomType="zoom"
 * app:minZoom="1.0"
 * app:transformation="centerCrop"
 * app:transformationGravity="top|center_horizontal"
 * app:minZoomType="zoom"
 * app:overPinchable="true"
 * app:overScrollHorizontal="true"
 * app:overScrollVertical="true"
 * app:verticalPanEnabled="true"
 * app:zoomEnabled="true">
 * <p>
 * <com.artlite.bslibrary.ui.view.BSImageView
 * android:id="@+id/image_view"
 * android:layout_width="wrap_content"
 * android:layout_gravity="top"
 * android:layout_height="wrap_content" />
 * <p>
 * </com.otaliastudios.zoom.ZoomLayout>
 */
