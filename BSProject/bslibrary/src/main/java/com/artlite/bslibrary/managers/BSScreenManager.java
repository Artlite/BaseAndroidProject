package com.artlite.bslibrary.managers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.artlite.bslibrary.helpers.screen.BSScreenHelper;

/**
 * Class which provide the getting of the screen resolution
 */
public final class BSScreenManager extends BSBaseManager {

    /**
     * Instance of the {@link BSScreenManager}
     */
    private static BSScreenManager instance;

    /**
     * Method which provide the getting of the instance of the {@link BSServiceManager}
     *
     * @return instance of {@link BSServiceManager}
     */
    protected static BSScreenManager getInstance() {
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
            instance = new BSScreenManager(context);
        } else {
            Log.e(TAG, "ServiceManager already initialized");
        }
    }

    /**
     * Default constructor
     *
     * @param context
     */
    private BSScreenManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the getting of the device screen height
     *
     * @return height of the screen
     */
    public static int getHeight() {
        return BSScreenHelper.getHeight(getInstance().getContext());
    }

    /**
     * Method which provide the getting of the device screen width
     *
     * @return height of the screen
     */
    public static int getWidth() {
        return BSScreenHelper.getWidth(getInstance().getContext());
    }

}
