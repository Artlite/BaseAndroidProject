package com.artlite.bslibrary.helpers.sign;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.security.MessageDigest;

public final class BSSignHelper extends BSBaseHelper {

    /**
     * {@link String} constant of the TAG
     */
    private static final String TAG = BSSignHelper.class.getSimpleName();

    /**
     * Method which provide the getting of the MD5 sign information
     *
     * @param context instance of the {@link Context}
     * @return {@link String} value of the MD5 sign information
     */
    @Nullable
    public static String getMD5(@Nullable Context context) {
        try {
            return getMD5(context, context.getPackageName());
        } catch (Exception ex) {
            Log.e(TAG, "getMD5: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the getting of the MD5 sign information
     *
     * @param context      instance of the {@link Context}
     * @param packageValue {@link String} value of the package name
     * @return {@link String} value of the MD5 sign information
     */
    @Nullable
    public static String getMD5(@Nullable Context context,
                                @Nullable String packageValue) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(packageValue, PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            String s = getMd5(sign);
            return s.toUpperCase();
        } catch (Exception ex) {
            Log.e(TAG, "getMD5: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the getting of the MD5 value
     *
     * @param signature instance of the {@link Signature}
     * @return {@link String} value of the MD5
     */
    private static String getMd5(Signature signature) {
        return encryptionMD5(signature.toByteArray());
    }

    /**
     * Method which provide the getting of the encryption of the MD5
     *
     * @param byteStr array of the {@link Byte}
     * @return instance of the {@link String}
     */
    private static String encryptionMD5(byte[] byteStr) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(byteStr);
            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "encryptionMD5: ", ex);
        }
        return md5StrBuff.toString();
    }

}
