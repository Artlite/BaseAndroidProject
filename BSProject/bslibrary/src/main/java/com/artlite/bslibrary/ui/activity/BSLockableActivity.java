package com.artlite.bslibrary.ui.activity;

import android.util.Log;
import android.view.MotionEvent;

import com.artlite.bslibrary.annotations.Warning;

/**
 * Class which provide the activity which can lockActivity it UI
 */

public abstract class BSLockableActivity extends BSActivity {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSLockableActivity.class.getSimpleName();

    /**
     * {@link Boolean} value if the view is locked
     */
    private boolean isLocked = false;

    /**
     * Method which provide the lock of the user interface
     *
     * @return {@link Boolean} value if the UI was locked
     */
    public final boolean lockActivity() {
        try {
            this.isLocked = true;
            this.onLockActivity();
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "lockActivity: ", ex);
        }
        this.onLockActivity();
        return false;
    }

    /**
     * Method which provide the action when the activity locked
     */
    @Warning(massage = "Just for overriding")
    protected void onLockActivity() {

    }

    /**
     * Method which provide the lockActivity of the user interface
     *
     * @return {@link Boolean} value if the UI was locked
     */
    public final boolean unlockActivity() {
        try {
            this.isLocked = false;
            this.onUnlockActivity();
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "unlock: ", ex);
        }
        this.onUnlockActivity();
        return false;
    }

    /**
     * Method which provide the action when the activity locked
     */
    @Warning(massage = "Just for overriding")
    protected void onUnlockActivity() {

    }

    /**
     * Method which provide the dispatching of the touch event
     *
     * @param motionEvent instance of the {@link MotionEvent}
     * @return {@link Boolean} value of the click event
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return (this.isLocked) ? true : super.dispatchTouchEvent(motionEvent);
    }

}
