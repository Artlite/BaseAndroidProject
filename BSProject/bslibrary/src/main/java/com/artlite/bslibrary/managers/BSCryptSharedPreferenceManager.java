package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.preference.BSCryptSharedPreferenceHelper;
import com.artlite.bslibrary.helpers.preference.BSSharedPreferenceHelper;

import java.util.Date;
import java.util.List;

public final class BSCryptSharedPreferenceManager extends BSBaseManager {

    /**
     * Instance of the {@link BSScreenManager}
     */
    private static BSCryptSharedPreferenceManager instance;

    /**
     * Method which provide the getting of the instance of the {@link BSServiceManager}
     *
     * @return instance of {@link BSServiceManager}
     */
    protected static BSCryptSharedPreferenceManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "ServiceManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Method which provide the initialization of the {@link BSServiceManager}
     *
     * @param context
     */
    public static void init(@NonNull Context context) {
        if (isNull(instance)) {
            instance = new BSCryptSharedPreferenceManager(context);
        } else {
            Log.e(TAG, "ServiceManager already initialized");
        }
    }

    /**
     * Default constructor
     *
     * @param context
     */
    private BSCryptSharedPreferenceManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the saving of the String value to the SharedPreferences
     *
     * @param object current String value
     * @param key    SharedPreferences key
     */
    public static boolean save(@Nullable final String object,
                               @Nullable final String key) {
        return BSCryptSharedPreferenceHelper.save(getInstance().getContext(), object, key);
    }

    /**
     * Method which provide the saving of the {@link Boolean} value to the SharedPreferences
     *
     * @param object current {@link Boolean} value
     * @param key    SharedPreferences key
     */
    public static boolean save(@Nullable final boolean object,
                               @Nullable final String key) {
        return BSCryptSharedPreferenceHelper.save(getInstance().getContext(), object, key);
    }

    /**
     * Method which provide the saving of the {@link List} value to the SharedPreferences
     *
     * @param objects current list of {@link List} values
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final List<String> objects,
                               @Nullable final String key) {
        return BSCryptSharedPreferenceHelper.save(getInstance().getContext(), objects, key);
    }

    /**
     * Method which provide the saving of the {@link Date} value to the SharedPreferences
     *
     * @param date current list of {@link Date} values
     * @param key  SharedPreferences key
     */
    public static boolean save(@Nullable final Date date,
                               @Nullable final String key) {
        return BSCryptSharedPreferenceHelper.save(getInstance().getContext(), date, key);
    }

    /**
     * Method which provide the getting of the the String value by key
     *
     * @param key key ID
     * @return current String
     */
    @NonNull
    public static String getString(@Nullable final String key) {
        return BSCryptSharedPreferenceHelper.getString(getInstance().getContext(), key);
    }

    /**
     * Method which provide the getting of the the {@link Boolean} value by key
     *
     * @param key key ID
     * @return current {@link Boolean}
     */
    public static boolean getBoolean(@Nullable final String key) {
        return BSCryptSharedPreferenceHelper.getBoolean(getInstance().getContext(), key);
    }

    /**
     * Method which provide the getting of the the {@link Date} value by key
     *
     * @param key key ID
     * @return current {@link Date}
     */
    @NonNull
    public static Date getDate(@Nullable final String key) {
        return BSCryptSharedPreferenceHelper.getDate(getInstance().getContext(), key);
    }

    /**
     * Method which provide the getting of the the {@link List} value by key
     *
     * @param key key ID
     * @return current {@link List}
     */
    @NonNull
    public static List<String> getList(@Nullable final String key) {
        return BSCryptSharedPreferenceHelper.getList(getInstance().getContext(), key);
    }

    /**
     * Method which provide the deleting of the object by key
     *
     * @param key {@link String} value of the key
     * @return {@link Boolean} valie if it delete
     */
    public static boolean delete(@Nullable String key) {
        return BSCryptSharedPreferenceHelper.delete(getInstance().getContext(), key);
    }

    /**
     * Method which provide the deleting of the object by key
     *
     * @return {@link Boolean} valie if it delete
     */
    public static boolean clear() {
        return BSCryptSharedPreferenceHelper.clear(getInstance().getContext());
    }
}
