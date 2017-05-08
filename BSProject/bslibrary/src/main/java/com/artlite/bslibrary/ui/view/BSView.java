package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.helpers.injector.BSInjector;

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
    protected PopupCallback popupCallback;

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
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {

    }

    /**
     * Method which provide the setting of the {@link PopupCallback}
     *
     * @param popupCallback instance of {@link PopupCallback}
     */
    public void setPopupCallback(@Nullable final PopupCallback popupCallback) {
        this.popupCallback = popupCallback;
    }

    /**
     * Method which provide the dismissing the pop up window if popUpListener isn't null
     */
    protected void dismissPopup() {
        if (popupCallback != null) {
            popupCallback.close();
        }
    }

    /**
     * Listener which provide the interaction between
     * View and Popup Window
     */
    public interface PopupCallback {
        void close();
    }
}
