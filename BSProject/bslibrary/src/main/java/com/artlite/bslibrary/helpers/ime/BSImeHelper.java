package com.artlite.bslibrary.helpers.ime;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide ime functional for keyboard
 */

public class BSImeHelper extends BSBaseHelper {
    /**
     * Method which provide the showing of the keyboard
     *
     * @param context current context
     * @param view    target view
     */
    public static void showKeyboard(@Nullable final Context context,
                                    @Nullable final View view) {
        if (isEmpty(context, view)) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Method which provide the hiding of the keyboard
     *
     * @param context current context
     * @param view    target view
     */
    public static void hideKeyboard(@Nullable final Context context,
                                    @Nullable final View view) {
        if (isEmpty(context, view)) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
