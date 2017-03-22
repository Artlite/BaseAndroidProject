package com.artlite.bslibrary.helpers.popup;

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
        BSPopupWindow popupWindow = new BSPopupWindow(dropdownView);
        popupWindow.showAsDropDown(targetView);
    }
}
