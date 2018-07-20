package com.artlite.bslibrary.managers;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.artlite.bslibrary.helpers.screen.BSScreenHelper;
import com.artlite.bslibrary.helpers.view.BSViewHelper;

/**
 * Class which provide the getting of the screen resolution
 */
public final class BSViewManager extends BSBaseManager {

    /**
     * Instance of the {@link BSViewManager}
     */
    private static BSViewManager instance;

    /**
     * Method which provide the getting of the instance of the {@link BSServiceManager}
     *
     * @return instance of {@link BSServiceManager}
     */
    protected static BSViewManager getInstance() {
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
            instance = new BSViewManager(context);
        } else {
            Log.e(TAG, "ServiceManager already initialized");
        }
    }

    /**
     * Default constructor
     *
     * @param context
     */
    public BSViewManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the checking if the {@link View} is visible in windows
     *
     * @param view instance of the {@link View}
     * @return {@link Boolean} value if it visible
     */
    public static boolean isViewVisible(@Nullable View view) {
        return BSViewHelper.isViewVisible(getInstance().getContext(), view);

    }
}
