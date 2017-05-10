package com.artlite.bslibrary.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

import java.lang.ref.WeakReference;

/**
 * Class which provide the base {@link View} functional
 */

public abstract class BSView extends LinearLayout implements View.OnClickListener {

    /**
     * Instance of {@link View}
     */
    protected View baseView;

    /**
     * Instance of {@link PopupCallback}
     */
    protected WeakReference<PopupCallback> popupCallback;

    /**
     * Instance of the {@link WeakReference}
     */
    protected WeakReference<Dialog> dialogReference;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSView(Context context) {
        super(context);
        onInitializeView(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitializeView(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitializeView(context);
    }

    /**
     * Method which provide the {@link BSView} initializing
     *
     * @param context instance of {@link Context}
     */
    private void onInitializeView(Context context) {
        inflateView(context, getLayoutId());
        if (baseView != null) {
            BSInjector.inject(baseView);
            BSInjector.inject(this);
        }
        onCreateView();
    }

    /**
     * Method which provide the inflating of the view
     *
     * @param context  current context
     * @param layoutID layout id
     */
    private void inflateView(Context context, int layoutID) {
        LayoutInflater inflater = LayoutInflater.from(context);
        baseView = inflater.inflate(layoutID, this);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    protected abstract int getLayoutId();

    /**
     * Method which provide the creating of the {@link View}
     */
    protected abstract void onCreateView();

    /**
     * Method which provide starting the Activity
     *
     * @param activtyClass activity which should be starting
     */
    protected void startActivity(Class activtyClass) {
        getContext().startActivity(new Intent(getContext(), activtyClass));
    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param views current list of Views
     */
    protected void setOnClickListeners(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param ids current list of {@link View} IDs
     */
    protected void setOnClickListeners(@IdRes Integer... ids) {
        for (Integer id : ids) {
            final View view = findViewById(id);
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }

    /**
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {

    }

    //==============================================================================================
    //                                          POPUP
    //==============================================================================================

    /**
     * Method which provide the setting of the {@link PopupCallback}
     *
     * @param popupCallback instance of {@link PopupCallback}
     */
    public void setPopupCallback(@Nullable final PopupCallback popupCallback) {
        if (!BSValidationHelper.isEmpty(popupCallback)) {
            this.popupCallback = new WeakReference<PopupCallback>(popupCallback);
        }
    }

    /**
     * Method which provide the {@link PopupCallback} getting from the {@link BSView}
     *
     * @return instance of the {@link PopupCallback}
     */
    @Nullable
    protected PopupCallback getCallback() {
        return (popupCallback == null)
                ? null : popupCallback.get();
    }

    /**
     * Method which provide the dismissing the pop up window if popUpListener isn't null
     */
    protected void dismissPopup() {
        final PopupCallback callback = getCallback();
        if (callback != null) {
            callback.close();
        }
    }

    //==============================================================================================
    //                                          DIALOG
    //==============================================================================================

    /**
     * Method which provide the setting of the {@link Dialog} inside the {@link BSView}
     *
     * @param dialog instance of the {@link Dialog}
     */
    public void setDialog(@Nullable final Dialog dialog) {
        if (!BSValidationHelper.isNull(dialog)) {
            this.dialogReference = new WeakReference<Dialog>(dialog);
        }
    }

    /**
     * Method which provide the {@link Dialog} getting
     *
     * @return instance of the {@link Dialog}
     */
    @Nullable
    protected Dialog getDialog() {
        return (dialogReference == null)
                ? null : dialogReference.get();
    }

    /**
     * Method which provide the dismiss {@link Dialog} from the {@link BSView}
     */
    protected void dismissDialog() {
        final Dialog dialog = getDialog();
        if (!BSValidationHelper.isNull(dialog)) {
            dialog.dismiss();
        }
    }

    //==============================================================================================
    //                                          CLASSES
    //==============================================================================================

    /**
     * Listener which provide the interaction between
     * View and Popup Window
     */
    public interface PopupCallback {
        void close();
    }
}
