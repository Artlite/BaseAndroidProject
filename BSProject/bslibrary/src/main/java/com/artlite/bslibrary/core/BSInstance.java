package com.artlite.bslibrary.core;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.constants.BSTypeface;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.managers.BSActivityManager;
import com.artlite.bslibrary.managers.BSApplicationManager;
import com.artlite.bslibrary.managers.BSContextManager;
import com.artlite.bslibrary.managers.BSCryptManager;
import com.artlite.bslibrary.managers.BSCryptSharedPreferenceManager;
import com.artlite.bslibrary.managers.BSEncryptPreferenceManager;
import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.managers.BSImageManager;
import com.artlite.bslibrary.managers.BSLocalNotificationManager;
import com.artlite.bslibrary.managers.BSLocationManager;
import com.artlite.bslibrary.managers.BSProgressDialogManager;
import com.artlite.bslibrary.managers.BSRandomManager;
import com.artlite.bslibrary.managers.BSScreenManager;
import com.artlite.bslibrary.managers.BSServiceManager;
import com.artlite.bslibrary.managers.BSSharedPreferenceManager;
import com.artlite.bslibrary.managers.BSSignManager;
import com.artlite.bslibrary.managers.BSStatusBarManager;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.managers.BSTransferManager;
import com.artlite.bslibrary.managers.BSTypefaceManager;
import com.artlite.bslibrary.managers.BSViewManager;
import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Class which provide the BSLibrary application
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSInstance {

    /**
     * Method which provide the initialization of the Base project library
     *
     * @param context instance of {@link Context}
     */
    public static void init(@Nullable final Context context) {
        init(context, BSTypeface.PRODUCT_SANS);
    }

    /**
     * Method which provide the initialization of the Base project library
     *
     * @param context instance of {@link Context}
     */
    public static void init(@Nullable final Context context,
                            @NonNull BSTypeface typeface) {
        final String methodName = "void init(context)";
        try {
            BSContextManager.init(context);
            BSEventManager.init(context);
            BSImageManager.init(context);
            BSLocalNotificationManager.init(context);
            BSLocationManager.init(context);
            BSRandomManager.init(context);
            BSScreenManager.init(context);
            BSServiceManager.init(context);
            BSThreadManager.init(context);
            BSTransferManager.init(context);
            BSTypefaceManager.init(context, typeface);
            BSStatusBarManager.init(context);
            BSSharedPreferenceManager.init(context);
            BSCryptSharedPreferenceManager.init(context);
            BSViewManager.init(context);
            BSProgressDialogManager.init(context);
            BSActivityManager.init(context);
            AndroidThreeTen.init(context);
            BSSignManager.init(context);
            BSCryptManager.init(context);
            BSApplicationManager.init(context);
            BSEncryptPreferenceManager.init(context);
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
    }
}
