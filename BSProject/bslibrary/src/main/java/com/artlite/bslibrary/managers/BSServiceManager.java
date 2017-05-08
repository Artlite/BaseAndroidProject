package com.artlite.bslibrary.managers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.artlite.bslibrary.helpers.service.BSServiceHelper;

/**
 * Class which provide the service performing
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSServiceManager extends BSBaseManager {

    /**
     * Instance of the {@link BSServiceManager}
     */
    private static BSServiceManager instance;

    /**
     * Method which provide the getting of the instance of the {@link BSServiceManager}
     *
     * @return instance of {@link BSServiceManager}
     */
    protected static BSServiceManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "ServiceManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Method which provide the initialization of the {@link BSServiceManager}
     *
     * @param context
     */
    public static void init(@NonNull Context context) {
        if (isNull(instance)) {
            instance = new BSServiceManager(context);
        } else {
            Log.e(TAG, "ServiceManager already initialized");
        }
    }

    /**
     * Constructor which provide the create {@link BSServiceManager} from
     *
     * @param context instance of {@link Context}
     */
    private BSServiceManager(@NonNull final Context context) {
        super(context);
    }

    /**
     * Method which provide the checking if Service is already running
     *
     * @param serviceClass current service class
     * @return result
     */
    private static boolean isRunning(Class<?> serviceClass) {
        if (hasContext()) {
            final Context context = getInstance().getContext();
            return BSServiceHelper.isRunning(context, serviceClass);
        }
        return false;
    }

    /**
     * Method which provide the service starting
     *
     * @param serviceClass current service class
     */
    public static void start(Class<?> serviceClass) {
        if (hasContext()) {
            if (!isRunning(serviceClass)) {
                final Context context = getInstance().getContext();
                context.startService(new Intent(context, serviceClass));
            }
        }
    }

    /**
     * Method which provide the service starting
     *
     * @param serviceClass current service class
     * @param intent       current intent
     */
    public static void start(Class<?> serviceClass, Intent intent) {
        if (hasContext()) {
            if (!isRunning(serviceClass)) {
                final Context context = getInstance().getContext();
                context.startService(intent);
            }
        }
    }

    /**
     * Method which provide the service stopping
     *
     * @param serviceClass current service class
     */
    public static void stop(Class<?> serviceClass) {
        if (hasContext()) {
            if (isRunning(serviceClass)) {
                final Context context = getInstance().getContext();
                context.stopService(new Intent(context, serviceClass));
            }
        }
    }

    /**
     * Method which provide the service stopping
     *
     * @param serviceClass current service class
     * @param intent       current intent
     */
    public static void stop(Class<?> serviceClass, Intent intent) {
        if (hasContext()) {
            if (isRunning(serviceClass)) {
                final Context context = getInstance().getContext();
                context.stopService(intent);
            }
        }
    }

    /**
     * Method which provide the {@link BSEventManager} instance validation
     *
     * @return validation result
     */
    private static boolean hasContext() {
        if (getInstance() != null) {
            if (getInstance().getContext() != null) {
                return true;
            }
        }
        return false;
    }
}
