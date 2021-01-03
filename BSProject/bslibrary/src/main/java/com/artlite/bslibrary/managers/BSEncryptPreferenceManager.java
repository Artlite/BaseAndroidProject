package com.artlite.bslibrary.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.artlite.bslibrary.helpers.convert.BSConvertHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.artlite.bslibrary.helpers.log.BSLogHelper.log;

/**
 * New version of the Encrypted Shared Preferences.
 */
public class BSEncryptPreferenceManager extends BSBaseManager {

    /**
     * {@link String} value of the file name.
     */
    private static final String K_FILE_NAME = "75LLVUBM25GWJHF77WU8";

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSEncryptPreferenceManager instance;

    /**
     * Instance of the {@link SharedPreferences}
     */
    private SharedPreferences preferences;

    /**
     * Method which provide the initialization of {@link BSEncryptPreferenceManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSEncryptPreferenceManager(context);
        } else {
            Log.e(TAG, "EncryptPreference is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEncryptPreferenceManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSEncryptPreferenceManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "EncryptPreference should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     *
     * @param context
     */
    private BSEncryptPreferenceManager(@NonNull Context context) {
        super(context);
        this.onInitPreferences(context);
    }

    /**
     * Method which provide the init preferences.
     *
     * @param context instance of {@link Context}
     */
    private void onInitPreferences(@NonNull Context context) {
        final String methodName = "onInitPreferences(context)";
        SharedPreferences preferences = null;
        try {
            MasterKey mainKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            preferences = EncryptedSharedPreferences.create(
                    context,
                    K_FILE_NAME,
                    mainKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception ex) {
            log(this, methodName, ex, null);
        } finally {
            this.preferences = preferences;
        }
    }

    /**
     * Method which provide the saving of the String value to the SharedPreferences
     *
     * @param object current String value
     * @param key    SharedPreferences key
     */
    public static boolean save(@Nullable final String object,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(object, key)) {
            try {
                SharedPreferences.Editor editor = getEditor();
                editor.putString(key, object);
                editor.commit();
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the saving of the {@link Boolean} value to the SharedPreferences
     *
     * @param object current {@link Boolean} value
     * @param key    SharedPreferences key
     */
    public static boolean save(final boolean object,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(object, key)) {
            try {
                SharedPreferences.Editor editor = getEditor();
                editor.putBoolean(key, object);
                editor.commit();
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the saving of the {@link List} value to the SharedPreferences
     *
     * @param objects current list of {@link List} values
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final List<String> objects,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(objects, key)) {
            try {
                final List<String> cryptedObjects = new ArrayList<>();
                final Iterator<String> iterator = objects.listIterator();
                while (iterator.hasNext()) {
                    final String object = iterator.next();
                    if (object != null) {
                        cryptedObjects.add(object);
                    }
                }
                SharedPreferences.Editor editor = getEditor();
                editor.putStringSet(key, new HashSet<String>(cryptedObjects));
                editor.commit();
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the saving of the {@link Date} value to the SharedPreferences
     *
     * @param date current list of {@link Date} values
     * @param key  SharedPreferences key
     */
    public static boolean save(@Nullable final Date date,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(date, key)) {
            try {
                final String dateString = convert(date);
                SharedPreferences.Editor editor = getEditor();
                editor.putString(key, dateString);
                editor.commit();
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the getting of the the String value by key
     *
     * @param key key ID
     * @return current String
     */
    @NonNull
    public static String getString(@Nullable final String key) {
        final String methodName = "String get(context, id)";
        final StringBuilder result = new StringBuilder("");
        try {
            final SharedPreferences settings = getPreferences();
            final String decrypted = settings.getString(key, "");
            result.append((decrypted != null) ? decrypted : "");
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return result.toString();
    }

    /**
     * Method which provide the getting of the the {@link Boolean} value by key
     *
     * @param key key ID
     * @return current {@link Boolean}
     */
    public static boolean getBoolean(@Nullable final String key) {
        final String methodName = "String get(context, id)";
        try {
            SharedPreferences settings = getPreferences();
            return settings.getBoolean(key, false);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return false;
    }

    /**
     * Method which provide the getting of the the {@link Date} value by key
     *
     * @param key key ID
     * @return current {@link Date}
     */
    @NonNull
    public static Date getDate(@Nullable final String key) {
        final String methodName = "String get(context, id)";
        try {
            final SharedPreferences settings = getPreferences();
            final String decryptedString = settings.getString(key, null);
            return convert(decryptedString);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return new Date();
    }

    /**
     * Method which provide the getting of the the {@link List} value by key
     *
     * @param key key ID
     * @return current {@link List}
     */
    @NonNull
    public static List<String> getList(@Nullable final String key) {
        final String methodName = "List<String> get(context, id)";
        final List<String> result = new ArrayList<>();
        try {
            final SharedPreferences settings = getPreferences();
            final Set<String> strings = settings.getStringSet(key, Collections.<String>emptySet());
            final List<String> decryptedObjects = new ArrayList<>();
            final Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()) {
                final String object = iterator.next();
                if (object != null) {
                    decryptedObjects.add(object);
                }
            }
            result.addAll(decryptedObjects);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return result;
    }

    /**
     * Method which provide the getting of the {@link SharedPreferences}
     *
     * @return instance of {@link SharedPreferences}
     */
    @NonNull
    protected static SharedPreferences getPreferences() {
        return getInstance().preferences;
    }

    /**
     * Method which provide the getting of the {@link SharedPreferences.Editor}
     *
     * @return instance of the {@link SharedPreferences.Editor}
     */
    @NonNull
    protected static SharedPreferences.Editor getEditor() {
        final String methodName = "SharedPreferences.Editor getEditor(context)";
        try {
            return getPreferences().edit();
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return null;
    }

    /**
     * Method which provide the converting of the {@link Date} to {@link String}
     *
     * @param date instance of {@link Date}
     * @return converted {@link String}
     */
    @Nullable
    public static String convert(@Nullable final Date date) {
        return BSConvertHelper.convert(date);
    }

    /**
     * Method which provide the converting of the {@link Date} to {@link String}
     *
     * @param date instance of {@link Date}
     * @return converted {@link String}
     */
    @Nullable
    public static Date convert(@Nullable final String date) {
        return BSConvertHelper.convert(date);
    }

    /**
     * Method which provide the deleting of the object by key
     *
     * @param key {@link String} value of the key
     * @return {@link Boolean} valie if it delete
     */
    public static boolean delete(@Nullable String key) {
        final String methodName = "boolean delete(context, key)";
        if (validate(key)) {
            try {
                SharedPreferences.Editor editor = getEditor();
                editor.remove(key);
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the deleting of the object by key
     *
     * @return {@link Boolean} valie if it delete
     */
    public static boolean clear() {
        final String methodName = "boolean clear(context)";
        try {
            SharedPreferences.Editor editor = getEditor();
            editor.clear();
            return true;
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return false;
    }

    /**
     * Method which provide the validation
     *
     * @param objects objects
     * @return validation
     */
    protected static boolean validate(@Nullable final Object... objects) {
        return BSValidationHelper.emptyValidate(objects);
    }

}
