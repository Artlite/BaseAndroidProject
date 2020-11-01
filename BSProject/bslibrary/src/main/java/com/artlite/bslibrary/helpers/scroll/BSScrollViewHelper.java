package com.artlite.bslibrary.helpers.scroll;

import android.graphics.Rect;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide the scroll view help functional
 */
public final class BSScrollViewHelper extends BSBaseHelper {

    /**
     * Method which provide the scrolling functional
     *
     * @param scrollView instance of the {@link ScrollView}
     * @param parent     instance of the {@link View}
     * @param targetView instance of the {@link View}
     */
    public static void scrollTo(@Nullable final ScrollView scrollView,
                                @Nullable final View parent,
                                @Nullable final View targetView) {
        if (isEmpty(scrollView, parent, targetView)) return;
        if (scrollView != null) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    Rect rect = new Rect();
                    targetView.getHitRect(rect);
                    scrollView.requestChildRectangleOnScreen(parent, rect, false);
                }
            });
        }
    }

}
