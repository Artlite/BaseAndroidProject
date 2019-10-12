package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;

/**
 * View which provide the Scroll view which could be lockable (enable/disable scroll)
 */
public class BSLockableScrollView extends ScrollView {

    /**
     * {@link Boolean} value if it scrollable
     */
    private boolean isScrollable = false;

    /**
     * Constructor which provide to create of the {@link BSLockableScrollView} with parameters
     *
     * @param context instance of the {@link Context}
     */
    public BSLockableScrollView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide to create of the {@link BSLockableScrollView} with parameters
     *
     * @param context instance of the {@link Context}
     * @param attrs   instance of the {@link AttributeSet}
     */
    public BSLockableScrollView(Context context,
                                AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide to create of the {@link BSLockableScrollView} with parameters
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the default attribute style
     */
    public BSLockableScrollView(Context context,
                                AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Constructor which provide to create of the {@link BSLockableScrollView} with parameters
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the default attribute style
     * @param defStyleRes  {@link Integer} value of the default attribute res
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BSLockableScrollView(Context context,
                                AttributeSet attrs,
                                int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Method which provide the setting of the scrolling enabled
     *
     * @param enabled {@link Boolean} value if the scrolling is enabled
     */
    public void setScrollingEnabled(boolean enabled) {
        isScrollable = enabled;
    }

    /**
     * Method which provide to checking if the {@link ScrollView} is scrollable
     *
     * @return {@link Boolean} value if it scrollable
     */
    public boolean isScrollable() {
        return isScrollable;
    }

    /**
     * Method which provide the action when the key is down
     *
     * @param keyCode {@link Integer} value of the key code
     * @param event   instance of the {@link KeyEvent}
     * @return {@link Boolean} value if the action is performed
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method which provide the action when the key is up
     *
     * @param keyCode {@link Integer} value of the key code
     * @param event   instance of the {@link KeyEvent}
     * @return {@link Boolean} value if the action is performed
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method which provide the action when the touch event happened
     *
     * @param ev instance of the {@link MotionEvent}
     * @return {@link Boolean} value if touch event is performed
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // if we can scroll pass the event to the superclass
                if (isScrollable) return super.onTouchEvent(ev);
                // only continue to handle the touch event if scrolling enabled
                return isScrollable; // isScrollable is always false at this point
            default:
                return super.onTouchEvent(ev);
        }
    }

    /**
     * Method which provide the action when the {@link ScrollView} is interception touch event
     *
     * @param ev instance of the {@link MotionEvent}
     * @return {@link Boolean} value if intercepting event is performed
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Don't do anything with intercepted touch events if
        // we are not scrollable
        if (!isScrollable) return false;
        else return super.onInterceptTouchEvent(ev);
    }

}
