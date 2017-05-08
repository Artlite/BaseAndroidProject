package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Class which provide the executing action in background and main threads
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSThreadManager extends BSBaseManager {

    /**
     * Instance of the {@link BSThreadManager}
     */
    private static BSThreadManager instance;

    /**
     * Method which provide the getting instance of {@link BSThreadManager}
     *
     * @return
     */
    protected static BSThreadManager getInstance() {
        if (isEmpty(instance)) {
            Log.e(TAG, "ThreadManager should be initialized in the Application singleton");
        }
        return instance;
    }

    /**
     * Method which provide the initializing of {@link BSThreadManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isEmpty(instance)) {
            instance = new BSThreadManager(context);
        } else {
            Log.e(TAG, "ThreadManager already initialized");
        }
    }

    /**
     * Default constructor
     */
    private BSThreadManager(@Nullable final Context context) {
        super(context);
    }

    /**
     * Method which provide the executing action on main thread
     *
     * @param callback instance of {@link OnThreadCallback}
     */
    public static void main(@Nullable final OnThreadCallback callback) {
        main(0, callback);
    }

    /**
     * Method which provide the executing action on main thread
     *
     * @param delay    delay value
     * @param callback instance of {@link OnThreadCallback}
     */
    public static void main(int delay, @Nullable final OnThreadCallback callback) {
        //Validate instance
        if (!hasInstance()) {
            return;
        }
        //Execute callback
        if (getInstance().getHandler() != null) {
            getInstance().getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (nullValidate(callback)) {
                        callback.onExecute();
                    }
                }
            }, delay * 1000);
        }
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link OnThreadCallback}
     */
    public static void background(@Nullable final OnThreadCallback callback) {
        //Validate instance
        if (!hasInstance()) {
            return;
        }
        //Execute callback
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (nullValidate(callback)) {
                    callback.onExecute();
                }
            }
        }).start();
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link OnThreadCallback}
     */
    public static void execute(@Nullable final OnExecutionCallback callback) {
        //Validate instance
        if (!hasInstance()) {
            return;
        }
        background(new OnThreadCallback() {
            @Override
            public void onExecute() {
                if (nullValidate(callback)) {
                    callback.onBackground();
                    main(new OnThreadCallback() {
                        @Override
                        public void onExecute() {
                            if (nullValidate(callback)) {
                                callback.onMain();
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * Method which provide the validation initializing
     *
     * @return validation result
     */
    private static boolean hasInstance() {
        return getInstance() != null;
    }

    //==============================================================================================
    //                                      CLASSES
    //==============================================================================================

    /**
     * Callback class
     */
    public interface OnThreadCallback {
        /**
         * Method which provide the executing callback
         */
        void onExecute();
    }

    /**
     * Callback which provide the sequently executing on background and on main thread and
     */
    public interface OnExecutionCallback {
        /**
         * Method which provide the background executing
         */
        void onBackground();

        /**
         * Method which provide the main thread executing
         */
        void onMain();
    }
}
