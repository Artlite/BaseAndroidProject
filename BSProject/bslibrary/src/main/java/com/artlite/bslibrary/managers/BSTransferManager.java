package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LruCache;

import java.lang.ref.WeakReference;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSTransferManager extends BSBaseManager {

    /**
     * Instance of the {@link BSTransferManager}
     */
    private static BSTransferManager instance;

    /**
     * Instance of the {@link LruCache}
     */
    private final LruCache<Class, WeakReference<Object>> data;

    /**
     * Method which provide the instance of the {@link BSTransferManager}
     *
     * @return instance of the {@link BSTransferManager}
     */
    protected static BSTransferManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "TransferManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Method which provide the initializing of the {@link BSTransferManager}
     */
    public static void init(@Nullable final Context context) {
        if (instance == null) {
            instance = new BSTransferManager(context);
        } else {
            Log.e(TAG, "ThreadManager already initialized");
        }
    }

    /**
     * Default constructor
     */
    private BSTransferManager(@NonNull Context context) {
        super(context);
        data = new LruCache<>(K_CACHE_SIZE);
    }

    /**
     * Method which provide the putting object for transfert
     *
     * @param dest   destination class
     * @param object instance of {@link Object}
     * @return put results
     */
    public static synchronized boolean put(@Nullable final Class dest,
                                           @Nullable final Object object) {
        final String methodName = "put(aClass, object)";
        boolean result = true;
        try {
            getInstance().data.put(dest, new WeakReference<Object>(object));
        } catch (Exception ex) {
            Log.e(TAG, methodName, ex);
        }
        return result;
    }

    /**
     * Method which provide the getting of the object
     *
     * @param owner from owner
     * @param <T>   object type
     * @return instance of {@link Object}
     */
    @Nullable
    public static synchronized <T extends Object> T get(@Nullable final Object owner) {
        if ((owner != null) && (hasInstance())) {
            return get(owner.getClass());
        }
        return null;
    }

    /**
     * Method which provide the getting of the object
     *
     * @param owner from owner
     * @param <T>   object type
     * @return instance of {@link Object}
     */
    @Nullable
    public static synchronized <T extends Object> T get(@Nullable final Class owner) {
        final String methodName = "T get(owner)";
        if ((owner != null) && (hasInstance())) {
            if (getInstance().data.get(owner) != null) {
                final WeakReference<Object> reference = getInstance().data.get(owner);
                getInstance().data.remove(owner);
                if ((reference != null) && (reference.get() != null)) {
                    final Object object = reference.get();
                    try {
                        return (T) object;
                    } catch (Exception ex) {
                        Log.e(TAG, methodName, ex);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method which provide the validation initializing
     *
     * @return validation result
     */
    private static boolean hasInstance() {
        return getInstance() != null;
    }
}
