package com.artlite.bslibrary.managers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.artlite.bslibrary.helpers.crypt.BSCryptHelper;
import com.artlite.bslibrary.helpers.sign.BSSignHelper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Method which provide the getting of the {@link Context} independently
 */

public final class BSCryptManager extends BSBaseManager {

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSCryptManager instance;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSCryptManager(context);
        } else {
            Log.e(TAG, "ContextManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSCryptManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "ContextManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private BSCryptManager(@Nullable final Context context) {
        super(context);
    }

    /**
     * Method which provide to encrypt of the information
     *
     * @param value {@link String} value of the information which should be encrypted
     * @return {@link String} value of the encrypted information
     */
    @Nullable
    public static String encrypt(@Nullable String value) {
        return BSCryptHelper.encrypt(value, BSSignManager.getMD5());
    }

    /**
     * Method which provide to decrypt of the information
     *
     * @param value {@link String} value of the information which should be encrypted
     * @return {@link String} value of the encrypted information
     */
    public static String decrypt(@Nullable String value) {
        return BSCryptHelper.decrypt(value, BSSignManager.getMD5());
    }

}
