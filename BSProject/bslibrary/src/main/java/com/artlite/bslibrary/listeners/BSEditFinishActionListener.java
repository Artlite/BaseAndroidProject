package com.artlite.bslibrary.listeners;

import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.artlite.bslibrary.helpers.ime.BSImeHelper;
import com.artlite.bslibrary.managers.BSContextManager;

/**
 * Class which provide the action when user press enter button on keyboard
 */

public abstract class BSEditFinishActionListener<T extends TextView>
        implements TextView.OnEditorActionListener {

    /**
     * {@link String} constants of  the tag
     */
    private static final String TAG = BSEditFinishActionListener.class.getSimpleName();

    /**
     * Constructor which provide to create the {@link BSEditFinishActionListener} with parameters
     */
    public BSEditFinishActionListener() {
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
                try {
                    this.onFinishEdit((T) textView);
                } catch (Exception ex) {
                    Log.e(TAG, "onEditorAction: ", ex);
                }
                return true; // consume.
            }
        }
        return false;
    }

    /**
     * Method which provide the end edition action
     *
     * @param view instance of the {@link TextView}
     */
    public abstract void onFinishEdit(@NonNull T view);
}
