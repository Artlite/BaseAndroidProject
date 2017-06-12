package com.artlite.bslibrary.callbacks;

/**
 * Callback which provide the permission callback functional
 *
 * @see com.artlite.bslibrary.helpers.permission.BSPermissionHelper
 */

public interface BSPermissionCallback {

    /**
     * Method which provide the action when permission was granted
     */
    void onPermissionGranted();

}
