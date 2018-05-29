package com.artlite.bslibrary.helpers.statusbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.lang.reflect.Method;

/**
 * Class which provide the open/close status bar
 */
public final class BSStatusBarHelper extends BSBaseHelper {
    /**
     * Method which provide the open of the status bar
     */
    public static void open(@Nullable Context context) {
        try {
            @SuppressLint("WrongConstant") Object sbservice = context
                    .getSystemService("statusbar");
            Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
            Method showsb;
            if (Build.VERSION.SDK_INT >= 17) {
                showsb = statusbarManager.getMethod("expandNotificationsPanel");
            } else {
                showsb = statusbarManager.getMethod("expand");
            }
            showsb.invoke(sbservice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
