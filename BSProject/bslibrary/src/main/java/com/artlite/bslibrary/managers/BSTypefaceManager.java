package com.artlite.bslibrary.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.log.BSLogHelper;

/**
 * Class which provide the functional for {@link Typeface}
 */

public final class BSTypefaceManager extends BSBaseManager {

    /**
     * Instance of {@link BSTypefaceManager}
     */
    private static BSTypefaceManager instance;

    /**
     * Default {@link Typeface}
     */
    private Typeface DEFAULT_FONT;

    /**
     * Bold default {@link Typeface}
     */
    private Typeface DEFAULT_BOLD_FONT;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSTypefaceManager(context);
        } else {
            Log.e(TAG, "TypeFace manager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSTypefaceManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "TypeFace manager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     *
     * @param context
     */
    public BSTypefaceManager(@NonNull Context context) {
        super(context);
        final String methodName = "BSTypefaceManager(context)";
        try {
            DEFAULT_FONT = Typeface.createFromAsset(context.getAssets(), "fonts/Bariol.ttf");
            DEFAULT_BOLD_FONT = Typeface.createFromAsset(context.getAssets(), "fonts/Bariol_Bold.ttf");
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
    }

    //==============================================================================================
    //                                       GETTERS
    //==============================================================================================

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getFont() {
        return getInstance().getDefaultFont();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public Typeface getDefaultFont() {
        return DEFAULT_FONT;
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public static Typeface getBold() {
        return getInstance().getDefaultBoldFont();
    }

    /**
     * Method which provide the getting of the bold {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @NonNull
    public Typeface getDefaultBoldFont() {
        return DEFAULT_BOLD_FONT;
    }

}
