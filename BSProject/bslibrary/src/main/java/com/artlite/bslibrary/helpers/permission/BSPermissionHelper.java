package com.artlite.bslibrary.helpers.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper which provide the permission functional
 */

public class BSPermissionHelper {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSPermissionHelper.class.getSimpleName();

    /**
     * Method which provide the request permissions
     *
     * @param activity    instance of the {@link Activity}
     * @param callback    instance of the {@link BSPermissionCallback}
     * @param permissions array of the {@link Manifest.permission}
     */
    public static void requestPermissions(@Nullable final Activity activity,
                                          @Nullable final BSPermissionCallback callback,
                                          @Nullable final String... permissions) {
        if (BSValidationHelper.validateNull(activity, callback, permissions)) {
            List<String> ungrandedPermissions = new ArrayList<>();
            List<String> ableToGrand = new ArrayList<>();
            List<String> unableToGrand = new ArrayList<>();

            for (String permission : permissions) {
                if (permission != null) {
                    if (ActivityCompat.checkSelfPermission(activity, permission)
                            != PackageManager.PERMISSION_GRANTED) {
                        ungrandedPermissions.add(permission);
                    }
                }
            }

            for (String permission : ungrandedPermissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        permission.toString())) {
                    unableToGrand.add(permission.toString());
                } else {
                    ableToGrand.add(permission);
                }
            }

            if (unableToGrand.size() > 0) {
                Toast.makeText(activity,
                        "Unable to grand that permissions: "
                                + TextUtils.join(", ", unableToGrand)
                                + ". Please check the application permissions",
                        Toast.LENGTH_LONG).show();
            }

            if (ableToGrand.size() > 0) {
                ActivityCompat.requestPermissions(activity, ableToGrand.toArray(new String[0]),
                        0b1010);
            }

            if ((unableToGrand.size() == 0) && (ableToGrand.size() == 0)) {
                callback.onPermissionGranted();
            }
        }
    }

    /**
     * Method which provide the checking if the permission is granted
     *
     * @param context    instance of the {@link Context}
     * @param permission {@link String} value of the permission
     * @return {@link Boolean} value if it granted
     */
    public static boolean isGranted(@Nullable Context context,
                                    @Nullable String permission) {
        try {
            return ActivityCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED;
        } catch (Exception ex) {
            Log.e(TAG, "isGranted: ", ex);
        }
        return false;
    }

    /**
     * Method which provide the checking if the permission is granted
     *
     * @param context     instance of the {@link Context}
     * @param permissions {@link String} array of the permissions
     * @return {@link Boolean} value if it granted
     */
    public static boolean isGranted(@Nullable Context context,
                                    @Nullable String... permissions) {
        try {
            for (String permission : permissions) {
                if (!isGranted(context, permission)) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "isGranted: ", ex);
        }
        return false;
    }

}
