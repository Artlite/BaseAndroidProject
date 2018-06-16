package com.artlite.bslibrary.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.intent.BSIntentHelper;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.listeners.BSSwipeListener;
import com.artlite.bslibrary.managers.BSThreadManager;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Set;

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
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "lockActivity: ", ex);
        }
        return false;
    }

    /**
     * Method which provide the lockActivity of the user interface
     *
     * @return {@link Boolean} value if the UI was locked
     */
    public final boolean unlockActivity() {
        try {
            this.isLocked = false;
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "unlock: ", ex);
        }
        return false;
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
