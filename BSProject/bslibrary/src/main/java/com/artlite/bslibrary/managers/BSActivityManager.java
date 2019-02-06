package com.artlite.bslibrary.managers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Manager which provide the getting of the on top activity
 */
public final class BSActivityManager extends BSBaseManager {

    /**
     * Instance of the {@link BSActivityManager}
     */
    private static BSActivityManager instance;

    /**
     * Instance of the {@link WeakReference}
     */
    private WeakReference<Activity> activityReference;

    /**
     * Method which provide the getting of the instance of the {@link BSActivityManager}
     *
     * @return instance of the {@link BSActivityManager}
     */
    @NonNull
    private static BSActivityManager getInstance() {
        return instance;
    }

    /**
     * Method which provide the initialization of {@link BSActivityManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSActivityManager(context);
        } else {
            Log.e(TAG, "BSActivityManager is already created");
        }
    }

    /**
     * Default constructor
     *
     * @param context
     */
    public BSActivityManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the register of the {@link Activity} instance
     *
     * @param activity instance of the {@link Activity}
     */
    public static void registerActivity(@Nullable Activity activity) {
        final BSActivityManager instance = getInstance();
        if ((instance != null) && (activity != null)) {
            instance.activityReference = new WeakReference<>(activity);
        }
    }

    /**
     * Method which provide the register of the {@link Activity} instance
     *
     * @param activity instance of the {@link Activity}
     */
    public static void registerActivityIfEmpty(@Nullable Activity activity) {
        if (getForegroundActivity() == null) {
            registerActivity(activity);
        }
    }

    /**
     * Method which provide the getting of the foreground {@link Activity}
     *
     * @return instance of the foreground {@link Activity}
     */
    @Nullable
    public static <T extends Activity> T getForegroundActivity() {
        try {
            return (T) getInstance().activityReference.get();
        } catch (Exception ex) {
            Log.e(TAG, "getForegroundActivity: ", ex);
            return null;
        }
    }
}
