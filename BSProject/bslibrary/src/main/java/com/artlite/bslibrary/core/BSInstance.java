package com.artlite.bslibrary.core;

import android.content.Context;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.managers.BSContextManager;
import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.managers.BSLocalNotificationManager;
import com.artlite.bslibrary.managers.BSRandomManager;
import com.artlite.bslibrary.managers.BSScreenManager;
import com.artlite.bslibrary.managers.BSServiceManager;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.managers.BSTransferManager;
import com.artlite.bslibrary.managers.BSTypefaceManager;

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
        final String methodName = "void init(context)";
        try {
            BSEventManager.init(context);
            BSServiceManager.init(context);
            BSThreadManager.init(context);
            BSTransferManager.init(context);
            BSTypefaceManager.init(context);
            BSRandomManager.init(context);
            BSScreenManager.init(context);
            BSContextManager.init(context);
            BSLocalNotificationManager.init(context);
        } catch (Exception ex) {
            BSLogHelper.log(null, methodName, ex, null);
        }
    }
}
