package com.artlite.bslibrary.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public abstract class BSFragment extends Fragment implements View.OnClickListener {

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
        onCreateFragment(containerView);
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
}
