package com.artlite.bslibrary.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.listeners.BSSwipeListener;
import com.artlite.bslibrary.managers.BSThreadManager;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public abstract class BSFragment extends Fragment implements View.OnClickListener,
        View.OnTouchListener {

    /**
     * Method which provide the delaying between create {@link Fragment} and call of the
     * {@link #onFragmentPostCreation(View)}
     * for now it 1 second
     */
    private static final int K_DELAY_CREATION = 1;

    /**
     * Instance of {@link View}
     */
    protected View containerView;

    /**
     * Instance of the {@link GestureDetector}
     */
    private GestureDetector gestureDetector;

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
        onInitgestures();
        BSThreadManager.main(K_DELAY_CREATION, new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                onFragmentPostCreation(containerView);
            }
        });
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
     * Method which provide the action when fragment is created
     *
     * @param containerView current view
     */
    protected abstract void onFragmentPostCreation(View containerView);


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
     * @param ids current list of views
     */
    protected void setOnClickListeners(@IdRes int... ids) {
        final String methodName = "void setOnClickListeners(int... ids)";
        if (this.containerView != null) {
            for (int id : ids) {
                try {
                    this.containerView.findViewById(id).setOnClickListener(this);
                } catch (Exception ex) {
                    BSLogHelper.log(this, methodName, ex, null);
                }
            }
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

    //==============================================================================================
    //                                         GESTURES
    //==============================================================================================

    /**
     * Method which provide the init gestures detector
     */
    protected void onInitgestures() {
        this.gestureDetector = new GestureDetector(getContext(), this.swipeListener);
    }

    /**
     * Method which provide the touch functional
     *
     * @param view        instance of the {@link View}
     * @param motionEvent instance of the {@link MotionEvent}
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.gestureDetector != null) {
            this.gestureDetector.onTouchEvent(motionEvent);
        }
        return false;
    }

    /**
     * Method which provide of the swipe functional
     *
     * @param direction instance of the {@link BSSwipeListener.Direction}
     */
    protected boolean onSwipe(BSSwipeListener.Direction direction) {
        // TODO: 15.02.2018 Implement the swipe functional (override this method)
        return false;
    }

    /**
     * Instance of the {@link BSSwipeListener}
     */
    protected final BSSwipeListener swipeListener = new BSSwipeListener() {
        @Override
        public boolean onSwipe(Direction direction) {
            return BSFragment.this.onSwipe(direction);
        }
    };
}
