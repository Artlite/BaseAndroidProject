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
 * Class which provide the activity which can lock it UI
 */

public abstract class BSLockableActivity extends BSActivity {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSLockableActivity.class.getSimpleName();

    /**
     * Method which provide the getting content view ID
     *
     * @return {@link Integer} value of the content view ID
     */
    @Nullable
    @IdRes
    protected abstract Integer getContentViewID();

    /**
     * Method which provide the lock of the user interface
     *
     * @return {@link Boolean} value if the UI was locked
     */
    protected boolean lock() {
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "lock: ", ex);
        }
        return false;
    }

    /**
     * Method which provide the lock of the user interface
     *
     * @return {@link Boolean} value if the UI was locked
     */
    protected boolean unlock() {
        try {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "unlock: ", ex);
        }
        return false;
    }


}
