package com.artlite.bslibrary.managers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.applications.BSApplicationHelper;
import com.artlite.bslibrary.models.BSPackageModel;

import java.util.ArrayList;
import java.util.List;

public final class BSApplicationManager extends BSBaseManager {

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSApplicationManager instance;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSApplicationManager(context);
        } else {
            Log.e(TAG, "BSApplicationManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSApplicationManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "BSApplicationManager should be " +
                    "initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     *
     * @param context
     */
    private BSApplicationManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the getting of the list of the installed applications
     *
     * @return list of the {@link BSPackageModel}
     */
    @NonNull
    public static List<BSPackageModel> getInstalledApplications() {
        try {
            return BSApplicationHelper.getInstalledApplications(getInstance().getContext());
        } catch (Exception ex) {
            Log.e(TAG, "getInstalledApplications: ", ex);
        }
        return new ArrayList<>();
    }
}
