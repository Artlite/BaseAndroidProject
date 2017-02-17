package com.artlite.bslibrary.core;

import android.content.Context;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.managers.BSServiceManager;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.managers.BSTransferManager;

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
        BSEventManager.init(context);
        BSServiceManager.init(context);
        BSThreadManager.init(context);
        BSTransferManager.init(context);
    }
}
