package com.artlite.bslibrary.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.managers.BSThreadManager;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public abstract class BSFragment extends Fragment implements View.OnClickListener {

    /**
     * Method which provide the delaying between create {@link Fragment} and call of the
     * {@link #onCreateFragment(View)}
     * for now it 1 second
     */
    private static final int K_DELAY_CREATION = 1;

    /**
     * Instance of {@link View}
     */
    protected View containerView;

    /**
     * Default constructor
     */
    public BSFragment() {
    }

    /**
     * Method which provide the action when {@link Fragment} created
     *
     * @param inflater  instance of {@link LayoutInflater}
     * @param container instance of {@link ViewGroup}
     * @param bundle    instance of {@link Bundle}
     * @return fragment {@link View}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        containerView = inflater.inflate(getLayoutId(), container, false);
        BSInjector.inject(this, containerView);
        BSThreadManager.main(K_DELAY_CREATION, new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                onCreateFragment(containerView);
            }
        });
        return containerView;
    }

    /**
     * Method which provide the getting of the current layout ID
     *
     * @return current layout ID
     */
    protected abstract int getLayoutId();

    /**
     * Method which provide the action when fragment is created
     *
     * @param containerView current view
     */
    protected abstract void onCreateFragment(View containerView);


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
     * Method which provide the action for the OnClickListener event
     *
     * @param v current view
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * Method which provide starting the Activity
     *
     * @param aClass activity which should be starting
     */
    protected void startActivity(Class aClass) {
        getActivity().startActivity(new Intent(getActivity(), aClass));
    }

    //==============================================================================================
    //                                      PERFORMING
    //==============================================================================================

    /**
     * Method which provide the doing action on UI thread after the delaying time
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    protected void main(@Nullable final BSThreadManager.OnThreadCallback callback) {
        main(0, callback);
    }

    /**
     * Method which provide the doing action on UI thread after the delaying time
     *
     * @param delayTime delaying time (in seconds)
     * @param callback  instance of {@link BSThreadManager.OnThreadCallback}
     */
    protected void main(int delayTime,
                        @Nullable final BSThreadManager.OnThreadCallback callback) {
        BSThreadManager.main(delayTime, callback);
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    public static void background(@Nullable final BSThreadManager.OnThreadCallback callback) {
        BSThreadManager.background(callback);
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    public static void execute(@Nullable final BSThreadManager.OnExecutionCallback callback) {
        BSThreadManager.execute(callback);
    }
}
