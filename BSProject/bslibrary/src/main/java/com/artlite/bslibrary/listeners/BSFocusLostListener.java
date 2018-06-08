package com.artlite.bslibrary.listeners;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

/**
 * Class which provide the action when user press enter button on keyboard
 */

public abstract class BSFocusLostListener<T extends View> implements View.OnFocusChangeListener {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSFocusLostListener.class.getSimpleName();

    /**
     * Constructor which provide to create the {@link BSFocusLostListener} with parameters
     */
    public BSFocusLostListener() {
    }


    /**
     * Method which provide the focus change
     *
     * @param view     instance of the {@link View}
     * @param hasFocus {@link Boolean} if has focus
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            try {
                this.onFocusLost((T) view);
            } catch (Exception ex) {
                Log.e(TAG, "onFocusChange: ", ex);
            }
        } else {
            try {
                this.onFocusStart((T) view);
            } catch (Exception ex) {
                Log.e(TAG, "onFocusChange: ", ex);
            }
        }
    }

    /**
     * Method which provide the focus lost listening
     *
     * @param view instance of the {@link View}
     */
    public abstract void onFocusLost(@NonNull T view);

    /**
     * Method which provide on focus start
     *
     * @param view instance of the {@link View}
     */
    public abstract void onFocusStart(@NonNull T view);

}
