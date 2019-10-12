package com.artlite.bslibrary.helpers.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide the view helping functionality
 */
public class BSViewHelper extends BSBaseHelper {

    /**
     * Method which provide the checking if the {@link View} is visible in windows
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link View}
     * @return {@link Boolean} value if it visible
     */
    public static boolean isViewVisible(@Nullable Context context,
                                        @Nullable View view) {
        if (context == null) return false;
        if (view == null) return false;
        if (!view.isShown()) return false;
        final Rect actualPosition = new Rect();
        view.getGlobalVisibleRect(actualPosition);
        final Rect screen = new Rect(0, 0, getWidth(context),
                getHeight(context));
        return actualPosition.intersect(screen);

    }

    /**
     * Method which provide the getting of the device screen height
     *
     * @param context instance of {@link Context}
     * @return height of the screen
     */
    private static int getHeight(@Nullable final Context context) {
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
    private static int getWidth(@Nullable final Context context) {
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
    private static DisplayMetrics getDisplayMetrics(@Nullable final Context context) {
        final String methodName = "DisplayMetrics getDisplayMetrics(context)";
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return null;
    }

}
