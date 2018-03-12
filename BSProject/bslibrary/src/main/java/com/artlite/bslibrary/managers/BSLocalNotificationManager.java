package com.artlite.bslibrary.managers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Class which provide the manager which is working with Local Notifications
 */

public final class BSLocalNotificationManager extends BSBaseManager {

    /**
     * Instance of the {@link BSLocalNotificationManager}
     */
    private static BSLocalNotificationManager instance;

    /**
     * Default constructor
     *
     * @param context
     */
    public BSLocalNotificationManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the initializing of {@link BSThreadManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isEmpty(instance)) {
            instance = new BSLocalNotificationManager(context);
        } else {
            Log.e(TAG, "ThreadManager already initialized");
        }
    }

    /**
     * Method which provide the getting instance of the {@link BSLocalNotificationManager}
     *
     * @return instance of the {@link BSLocalNotificationManager}
     */
    @Nullable
    protected static BSLocalNotificationManager getInstance() {
        return instance;
    }

    /**
     * Method which provide the register of the {@link BroadcastReceiver} with the
     * {@link String} value of the filter
     *
     * @param filter   {@link String} value of the filter
     * @param receiver instance of the {@link BroadcastReceiver}
     * @return {@link Boolean} value if it register
     */
    public static boolean register(@Nullable final BroadcastReceiver receiver,
                                   @Nullable final String filter) {
        if (!isEmpty(getInstance(), receiver, filter)) {
            if (getInstance().getContext() != null) {
                LocalBroadcastManager.getInstance(getInstance().getContext())
                        .registerReceiver(receiver, new IntentFilter(filter));
                return true;
            }
        }
        return false;
    }

    /**
     * Method which provide the unregister of the {@link BroadcastReceiver}
     *
     * @param receiver instance of the {@link BroadcastReceiver}
     * @return {@link Boolean} value if it unregister
     */
    public static boolean unregister(@Nullable final BroadcastReceiver receiver) {
        if (!isEmpty(getInstance(), receiver)) {
            if (getInstance().getContext() != null) {
                LocalBroadcastManager.getInstance(getInstance().getContext())
                        .unregisterReceiver(receiver);
                return true;
            }
        }
        return false;
    }

    /**
     * Method which provide the sending of the broadcast
     *
     * @param filter {@link String} value of the filter
     * @return {@link Boolean} value if it sent
     */
    public static boolean send(@Nullable final String filter) {
        if (!isEmpty(getInstance(), filter)) {
            return send(new Intent(filter));
        }
        return false;
    }

    /**
     * Method which provide the sending of the broadcast
     *
     * @param intent instance of the {@link Intent}
     * @return {@link Boolean} value if it sent
     */
    public static boolean send(@Nullable final Intent intent) {
        if (!isEmpty(getInstance(), intent)) {
            if (getInstance().getContext() != null) {
                LocalBroadcastManager.getInstance(getInstance().getContext())
                        .sendBroadcast(intent);
                return true;
            }
        }
        return false;
    }
}
