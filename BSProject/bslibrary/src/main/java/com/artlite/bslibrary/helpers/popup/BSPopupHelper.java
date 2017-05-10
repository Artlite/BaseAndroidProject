package com.artlite.bslibrary.helpers.popup;

import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.ui.popup.BSPopupWindow;
import com.artlite.bslibrary.ui.view.BSView;

/**
 * CLass which provide the pop up functional
 *
 * @see BSView
 * @see BSPopupWindow
 */

public final class BSPopupHelper extends BSBaseHelper {

    /**
     * Method which provide the showing of the pop up windows
     *
     * @param dropdownView view which should set in pop up window
     * @param targetView   view to which this pop up windows should be attached
     */
    public static void show(BSView dropdownView, View targetView) {
        show(dropdownView, targetView, 0, 0);
    }

    /**
     * Method which provide the showing of the pop up windows
     *
     * @param dropdownView view which should set in pop up window
     * @param targetView   view to which this pop up windows should be attached
     * @param paddingTop   {@link Integer} value of the padding top
     */
    public static void show(@Nullable BSView dropdownView,
                            @Nullable View targetView,
                            int paddingTop) {
        show(dropdownView, targetView, paddingTop, 0);
    }

    /**
     * Method which provide the showing of the pop up windows
     *
     * @param dropdownView view which should set in pop up window
     * @param targetView   view to which this pop up windows should be attached
     * @param paddingTop   {@link Integer} value of the padding top
     * @param paddingLeft  {@link Integer} value of the padding left
     */
    protected static void show(@Nullable BSView dropdownView,
                               @Nullable View targetView,
                               int paddingTop,
                               int paddingLeft) {
        if ((dropdownView == null) || (targetView == null)) {
            return;
        }
        BSPopupWindow popupWindow = new BSPopupWindow(dropdownView);
        popupWindow.showAsDropDown(targetView, paddingLeft, paddingTop);
    }
}
