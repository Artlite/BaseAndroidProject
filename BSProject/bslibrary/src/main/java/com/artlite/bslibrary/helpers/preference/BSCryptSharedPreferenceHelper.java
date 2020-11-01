package com.artlite.bslibrary.helpers.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.helpers.convert.BSConvertHelper;
import com.artlite.bslibrary.helpers.crypt.BSCryptHelper;
import com.artlite.bslibrary.helpers.sign.BSSignHelper;
import com.artlite.bslibrary.managers.BSSignManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class which provide the actions with {@link SharedPreferences}
 */

public final class BSCryptSharedPreferenceHelper extends BSBaseHelper {
    /**
     * Constants
     */
    protected static final String K_SHARED_PREFERENCE_NAME = "BS_ART_CRYPT_SHARED_PREFERENCES";

    /**
     * Method which provide the saving of the String value to the SharedPreferences
     *
     * @param context current context
     * @param object  current String value
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final Context context,
                               @Nullable final String object,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(object, context, key)) {
            try {
                SharedPreferences.Editor editor = getEditor(context);
                editor.putString(key, encrypt(context, object));
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
     * @param context current context
     * @param object  current {@link Boolean} value
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final Context context,
                               @Nullable final boolean object,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(object, context, key)) {
            try {
                SharedPreferences.Editor editor = getEditor(context);
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
     * @param context current context
     * @param objects current list of {@link List} values
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final Context context,
                               @Nullable final List<String> objects,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(objects, context, key)) {
            try {
                final List<String> cryptedObjects = new ArrayList<>();
                final Iterator<String> iterator = objects.listIterator();
                while (iterator.hasNext()) {
                    final String object = iterator.next();
                    final String cryptObject = encrypt(context, object);
                    if (cryptObject != null) {
                        cryptedObjects.add(cryptObject);
                    }
                }
                SharedPreferences.Editor editor = getEditor(context);
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
     * @param context current context
     * @param date    current list of {@link Date} values
     * @param key     SharedPreferences key
     */
    public static boolean save(@Nullable final Context context,
                               @Nullable final Date date,
                               @Nullable final String key) {
        final String methodName = "boolean save(context, object, id)";
        if (validate(date, context, key)) {
            try {
                final String dateString = convert(date);
                SharedPreferences.Editor editor = getEditor(context);
                editor.putString(key, encrypt(context, dateString));
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
     * @param context current context
     * @param key     key ID
     * @return current String
     */
    @NonNull
    public static String getString(@Nullable final Context context,
                                   @Nullable final String key) {
        final String methodName = "String get(context, id)";
        final StringBuilder result = new StringBuilder("");
        try {
            final SharedPreferences settings = getPreferences(context);
            final String decrypted = decrypt(context, settings.getString(key, ""));
            result.append((decrypted != null) ? decrypted : "");
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return result.toString();
    }

    /**
     * Method which provide the getting of the the {@link Boolean} value by key
     *
     * @param context current context
     * @param key     key ID
     * @return current {@link Boolean}
     */
    public static boolean getBoolean(@Nullable final Context context,
                                     @Nullable final String key) {
        final String methodName = "String get(context, id)";
        try {
            SharedPreferences settings = getPreferences(context);
            return settings.getBoolean(key, false);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return false;
    }

    /**
     * Method which provide the getting of the the {@link Date} value by key
     *
     * @param context current context
     * @param key     key ID
     * @return current {@link Date}
     */
    @NonNull
    public static Date getDate(@Nullable final Context context,
                               @Nullable final String key) {
        final String methodName = "String get(context, id)";
        try {
            final SharedPreferences settings = getPreferences(context);
            final String decryptedString = decrypt(context, settings.getString(key, null));
            return convert(decryptedString);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return new Date();
    }

    /**
     * Method which provide the getting of the the {@link List} value by key
     *
     * @param context current context
     * @param key     key ID
     * @return current {@link List}
     */
    @NonNull
    public static List<String> getList(@Nullable final Context context,
                                       @Nullable final String key) {
        final String methodName = "List<String> get(context, id)";
        final List<String> result = new ArrayList<>();
        try {
            final SharedPreferences settings = getPreferences(context);
            final Set<String> strings = settings.getStringSet(key, Collections.<String>emptySet());
            final List<String> decryptedObjects = new ArrayList<>();
            final Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()) {
                final String object = iterator.next();
                final String decryptObject = decrypt(context, object);
                if (decryptObject != null) {
                    decryptedObjects.add(decryptObject);
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
     * @param context instance of {@link Context}
     * @return instance of {@link SharedPreferences}
     */
    @Nullable
    protected static SharedPreferences getPreferences(@Nullable final Context context) {
        final String methodName = "SharedPreferences getPreferences(context)";
        try {
            return context.getSharedPreferences(K_SHARED_PREFERENCE_NAME, 0);
        } catch (Exception ex) {
            log(null, methodName, ex, null);
        }
        return null;
    }

    /**
     * Method which provide the getting of the {@link SharedPreferences.Editor}
     *
     * @param context instance of the {@link Context}
     * @return instance of the {@link SharedPreferences.Editor}
     */
    @Nullable
    protected static SharedPreferences.Editor getEditor(@Nullable final Context context) {
        final String methodName = "SharedPreferences.Editor getEditor(context)";
        try {
            return getPreferences(context).edit();
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
     * @param context instance of {@link Context}
     * @param key     {@link String} value of the key
     * @return {@link Boolean} valie if it delete
     */
    public static boolean delete(@Nullable final Context context,
                                 @Nullable String key) {
        final String methodName = "boolean delete(context, key)";
        if (validate(context, key)) {
            try {
                SharedPreferences.Editor editor = getEditor(context);
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
     * @param context instance of {@link Context}
     * @return {@link Boolean} valie if it delete
     */
    public static boolean clear(@Nullable final Context context) {
        final String methodName = "boolean clear(context)";
        if (validate(context)) {
            try {
                SharedPreferences.Editor editor = getEditor(context);
                editor.clear();
                return true;
            } catch (Exception ex) {
                log(null, methodName, ex, null);
            }
        }
        return false;
    }

    /**
     * Method which provide the encrypt object
     *
     * @param context instance of the {@link Context}
     * @param object  current String value
     * @return {@link String} value of the encrypted
     */
    @Nullable
    protected static String encrypt(@Nullable Context context,
                                    @Nullable String object) {
        try {
            return BSCryptHelper.encrypt(object, BSSignHelper.getMD5(context));
        } catch (Exception ex) {
            Log.e(TAG, "encrypt: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the decrypt object
     *
     * @param context instance of the {@link Context}
     * @param object  current String value
     * @return {@link String} value of the decrypted
     */
    @Nullable
    protected static String decrypt(@Nullable Context context,
                                    @Nullable String object) {
        try {
            return BSCryptHelper.decrypt(object, BSSignHelper.getMD5(context));
        } catch (Exception ex) {
            Log.e(TAG, "encrypt: ", ex);
        }
        return null;
    }
}
