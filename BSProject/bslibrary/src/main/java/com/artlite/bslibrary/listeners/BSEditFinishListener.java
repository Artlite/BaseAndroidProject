package com.artlite.bslibrary.listeners;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.artlite.bslibrary.helpers.ime.BSImeHelper;
import com.artlite.bslibrary.managers.BSContextManager;

/**
 * Class which provide the action when user press enter button on keyboard
 */

public final class BSEditFinishListener implements TextView.OnEditorActionListener {

    /**
     * Constructor which provide to create the {@link BSEditFinishListener} with parameters
     */
    public BSEditFinishListener() {
    }

    /**
     * Method which provide of the {@link TextView} editor action
     *
     * @param textView instance of the {@link TextView}
     * @param actionId {@link Integer} value of the action id
     * @param event    instance of the {@link KeyEvent}
     * @return {@link Boolean} value if the action needed
     */
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            if (event == null || !event.isShiftPressed()) {
                textView.clearFocus();
                BSImeHelper.hideKeyboard(BSContextManager.getApplicationContext(), textView);
                return true; // consume.
            }
        }
        return false;
    }
}
