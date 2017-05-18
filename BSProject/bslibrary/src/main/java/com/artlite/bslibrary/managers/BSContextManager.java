package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Method which provide the getting of the {@link Context} independently
 */

public final class BSContextManager extends BSBaseManager {

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSContextManager instance;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSContextManager(context);
        } else {
            Log.e(TAG, "ContextManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSContextManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "ContextManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private BSContextManager(@Nullable final Context context) {
        super(context);
    }

    /**
     * Method which provide the getting of the instance of the {@link Context}
     *
     * @return instance of the {@link Context}
     */
    @Nullable
    public static Context getRegisteredContext() {
        if ((instance != null) && (instance.getContext() != null)) {
            return instance.getContext();
        }
        return null;
    }

    /**
     * Method which provide the getting of the instance of the {@link Context}
     *
     * @return instance of the {@link Context}
     */
    @Nullable
    public static Context getApplicationContext() {
        if ((instance != null)
                && (instance.getContext() != null)
                && (instance.getContext().getApplicationContext() != null)) {
            return instance.getContext().getApplicationContext();
        }
        return null;
    }

}
