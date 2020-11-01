package com.artlite.bslibrary.managers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.artlite.bslibrary.helpers.statusbar.BSStatusBarHelper;

public final class BSStatusBarManager extends BSBaseManager {

    /**
     * Instance of the {@link BSServiceManager}
     */
    private static BSStatusBarManager instance;

    /**
     * Method which provide the getting of the instance of the {@link BSServiceManager}
     *
     * @return instance of {@link BSServiceManager}
     */
    protected static BSStatusBarManager getInstance() {
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
            instance = new BSStatusBarManager(context);
        } else {
            Log.e(TAG, "ServiceManager already initialized");
        }
    }

    /**
     * Constructor which provide the create {@link BSServiceManager} from
     *
     * @param context instance of {@link Context}
     */
    private BSStatusBarManager(@NonNull final Context context) {
        super(context);
    }

    /**
     * Method which provide the open of the status bar
     */
    public static void open() {
        if (getInstance() != null) {
            BSStatusBarHelper.open(getInstance().getContext());
        }
    }
}
