package com.artlite.bslibrary.helpers.screen;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide the getting of the screen resolution
 */
public final class BSScreenHelper extends BSBaseHelper {

    /**
     * Method which provide the getting of the device screen height
     *
     * @param context instance of {@link Context}
     * @return height of the screen
     */
    public static int getHeight(@Nullable final Context context) {
        final String methodName = "int getHeight(context)";
        try {
            return getDisplayMetrics(context).heightPixels;
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return 0;
    }

    /**
     * Method which provide the getting of the device screen width
     *
     * @param context instance of {@link Context}
     * @return height of the screen
     */
    public static int getWidth(@Nullable final Context context) {
        final String methodName = "int getWidth(context)";
        try {
            return getDisplayMetrics(context).widthPixels;
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return 0;
    }

    /**
     * Method which provide the getting of the instance of the {@link DisplayMetrics}
     *
     * @param context instance of {@link Context}
     * @return instance of {@link DisplayMetrics}
     */
    @Nullable
    private static final DisplayMetrics getDisplayMetrics(@Nullable final Context context) {
        final String methodName = "DisplayMetrics getDisplayMetrics(context)";
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return null;
    }

}
