package com.artlite.bslibrary.ui.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dlernatovich on 3/22/2017.
 */

public class BSViewPagerNonScroll extends ViewPager {
    private static final boolean IS_PAGING_ENABLED = false;

    /**
     * Constructor which provide the create {@link BSViewPagerNonScroll} form
     *
     * @param context instance of {@link Context}
     */
    public BSViewPagerNonScroll(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSViewPagerNonScroll} form
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSViewPagerNonScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Method which provide the touch action
     *
     * @param event instance of {@link MotionEvent}
     * @return touch result
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.IS_PAGING_ENABLED && super.onTouchEvent(event);
    }

    /**
     * Method which provide the intercepting of touch action
     *
     * @param event instance of {@link MotionEvent}
     * @return touch result
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.IS_PAGING_ENABLED && super.onInterceptTouchEvent(event);
    }
}
