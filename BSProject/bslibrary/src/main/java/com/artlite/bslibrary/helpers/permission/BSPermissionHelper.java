package com.artlite.bslibrary.helpers.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
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

}
