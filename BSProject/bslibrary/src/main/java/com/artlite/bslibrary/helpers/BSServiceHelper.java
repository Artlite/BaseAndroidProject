package com.artlite.bslibrary.helpers;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Class which provide the start/stop {@link android.app.Service}
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSServiceHelper {

    /**
     * Method which provide the checking if Service is already running
     *
     * @param context      current context
     * @param serviceClass current service class
     * @return result
     */
    public static boolean isRunning(@Nullable final Context context,
                                    @Nullable final Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method which provide the service starting
     *
     * @param context      current context
     * @param serviceClass current service class
     */
    public static void start(@Nullable final Context context,
                             @Nullable final Class<?> serviceClass) {
        if (!isRunning(context, serviceClass)) {
            context.startService(new Intent(context, serviceClass));
        }
    }

    /**
     * Method which provide the service starting
     *
     * @param context      current context
     * @param serviceClass current service class
     * @param intent       current intent
     */
    public static void start(@Nullable final Context context,
                             @Nullable final Class<?> serviceClass,
                             @Nullable final Intent intent) {
        if (!isRunning(context, serviceClass)) {
            context.startService(intent);
        }
    }

    /**
     * Method which provide the service stopping
     *
     * @param context      current context
     * @param serviceClass current service class
     */
    public static void stop(@Nullable final Context context,
                            @Nullable final Class<?> serviceClass) {
        if (isRunning(context, serviceClass)) {
            context.stopService(new Intent(context, serviceClass));
        }
    }

    /**
     * Method which provide the service stopping
     *
     * @param context      current context
     * @param serviceClass current service class
     * @param intent       current intent
     */
    public static void stop(@Nullable final Context context,
                            @Nullable final Class<?> serviceClass,
                            @Nullable final Intent intent) {
        if (isRunning(context, serviceClass)) {
            context.stopService(intent);
        }
    }
}
