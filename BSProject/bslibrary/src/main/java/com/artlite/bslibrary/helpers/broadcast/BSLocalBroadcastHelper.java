package com.artlite.bslibrary.helpers.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.models.BSExtraModel;

/**
 * Class which provide the {@link LocalBroadcastManager} functional
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSLocalBroadcastHelper {

    /**
     * Method which provide the sending the broadcast event for subscribing object
     *
     * @param context current context
     * @param filter  value that allow apply the intent filter and put extra for intent
     * @param values  extra values for the intent
     */
    public static void send(@Nullable Context context,
                            @Nullable String filter,
                            @Nullable BSExtraModel... values) {
        send(context, create(filter, values));
    }

    /**
     * Method which provide the sending the broadcast event for subscribing object
     *
     * @param context current context
     * @param intent  instance of {@link Intent}
     */
    private static void send(@Nullable final Context context,
                             @Nullable final Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * Method which provide the creating {@link Intent} from
     *
     * @param filter filter
     * @param values {@link Parcelable} objects
     * @param <T>    object type
     * @return instance of {@link Intent}
     */
    private static <T extends BSExtraModel> Intent create(@NonNull final String filter,
                                                          @Nullable final T... values) {
        final Intent intent = new Intent(filter);
        if (BSValidationHelper.emptyValidate(values)) {
            for (final T value : values) {
                if (BSValidationHelper.emptyValidate(value)) {
                    if (value.validate()) {
                        intent.putExtra(value.getKey(), value.getObject());
                    }
                }
            }
        }
        return intent;
    }
}
