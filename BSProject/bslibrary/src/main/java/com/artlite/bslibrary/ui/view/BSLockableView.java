package com.artlite.bslibrary.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.annotations.Info;
import com.artlite.bslibrary.helpers.dialog.BSDialogHelper;
import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.popup.BSPopupHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.listeners.BSSwipeListener;
import com.artlite.bslibrary.managers.BSThreadManager;

import java.lang.ref.WeakReference;

/**
 * Class which provide the base {@link View} functional
 */

public abstract class BSLockableView extends BSView {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSLockableView.class.getSimpleName();

    /**
     * {@link Boolean} value if the view is locked
     */
    private boolean isLocked = false;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSLockableView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSLockableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSLockableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the {@link View} locking
     *
     * @return {@link Boolean} value if the {@link View} is locked
     */
    public final boolean lockView() {
        try {
            this.isLocked = true;
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "lockView: ", ex);
        }
        return false;
    }

    /**
     * Method which provide the {@link View} locking
     *
     * @return {@link Boolean} value if the {@link View} is locked
     */
    public final boolean unlockView() {
        try {
            this.isLocked = false;
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "lockView: ", ex);
        }
        return false;
    }

    /**
     * Method which provide the click interception
     *
     * @param motionEvent instance of the {@link MotionEvent}
     * @return {@link Boolean} value of the touch event
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (this.isLocked) ? true : super.onInterceptTouchEvent(motionEvent);
    }

}
