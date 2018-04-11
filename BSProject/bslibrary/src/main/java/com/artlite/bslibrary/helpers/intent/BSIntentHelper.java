package com.artlite.bslibrary.helpers.intent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;

import com.artlite.bslibrary.callbacks.BSIntentCallback;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

/**
 * Class which provide the {@link android.content.Intent} helping functional
 */

public final class BSIntentHelper extends BSBaseHelper {

    //==============================================================================================
    //                                      CONSTANTS
    //==============================================================================================
    /**
     * Meraket constant
     */
    private static final String K_KEY_MARKET = "market://details?id=";

    /**
     * Browser link
     */
    private static final String K_KEY_BROWSER_MARKET = "https://play.google.com/store/apps/details?id=";

    //==============================================================================================
    //                                    INTENT EXTRAS
    //==============================================================================================

    /**
     * Method which provide the getting of the object by filter value
     *
     * @param intent instance of {@link Intent}
     * @return
     */
    public static void get(@Nullable final Intent intent,
                           @Nullable final BSIntentCallback callback,
                           @Nullable final String... keys) {
        if (!isEmpty(intent, callback, keys)) {
            for (final String key : keys) {
                if (!isEmpty(key)) {
                    final Parcelable parcelable = intent.getParcelableExtra(key);
                    if (!isEmpty(parcelable)) {
                        callback.onResult(key, parcelable);
                    }
                }
            }
        }
    }

    //==============================================================================================
    //                                      PLAY STORE
    //==============================================================================================

    /**
     * Open app page at Google Play. If Play Store application isn't available on the device
     * then web browser will be opened
     *
     * @param context instance of {@link Context}
     */
    @Nullable
    public static Intent openPlayStore(@Nullable final Context context)
            throws NullPointerException {
        return openPlayStore(context, true);
    }

    /**
     * Open app page at Google Play
     *
     * @param context       instance of {@link Context}
     * @param openInBrowser should we try to open application page in web browser
     *                      if Play Store app not found on device
     */
    @Nullable
    public static Intent openPlayStore(@Nullable final Context context,
                                       boolean openInBrowser)
            throws NullPointerException {
        if (isNull(context)) {
            return null;
        }
        final String packageName = context.getPackageName();
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(K_KEY_MARKET + packageName));
        if (isIntentAvailable(context, intent)) {
            return intent;
        }
        if (openInBrowser) {
            return openLink(K_KEY_BROWSER_MARKET + packageName);
        }
        return intent;
    }

    //==============================================================================================
    //                                      EMAIL
    //==============================================================================================

    /**
     * Method which provide the sending email
     *
     * @param to      Receiver email
     * @param subject Message subject
     * @param text    Message body
     * @see #sendEmail(String[], String, String)
     */
    @NonNull
    public static Intent sendEmail(@Nullable final String to,
                                   @Nullable final String subject,
                                   @Nullable final String text) {
        return sendEmail(new String[]{to}, subject, text);
    }

    /**
     * Method which provide the sending email
     *
     * @param to      Receiver email
     * @param subject Message subject
     * @param text    Message body
     * @see #sendEmail(String, String, String)
     */
    @NonNull
    public static Intent sendEmail(@Nullable final String[] to,
                                   @Nullable final String subject,
                                   @Nullable final String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent;
    }

    //==============================================================================================
    //                                      SOCIAL
    //==============================================================================================

    /**
     * Share text via third party app like twitter, facebook, email, sms etc.
     *
     * @param subject Optional subject of the message
     * @param text    Text to share
     */
    @NonNull
    public static Intent shareText(@Nullable final String subject,
                                   @Nullable final String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        if (!TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        return intent;
    }

    //==============================================================================================
    //                                        SMS
    //==============================================================================================

    /**
     * Send SMS message using built-in app
     *
     * @param to      Receiver phone number
     * @param message Text to send
     */
    @NonNull
    public static Intent sendSms(@Nullable final String to,
                                 @Nullable final String message) {
        Uri smsUri = Uri.parse("tel:" + to);
        Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
        intent.putExtra("address", to);
        intent.putExtra("sms_body", message);
        intent.setType("vnd.android-dir/mms-sms");
        return intent;
    }

    //==============================================================================================
    //                                      STREET VIEW
    //==============================================================================================

    /**
     * Opens the Street View application to the given location.
     * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param yaw       Panorama center-of-view in degrees clockwise from North.
     * @param pitch     Panorama center-of-view in degrees from -90 (look straight up) to 90 (look straight down.)
     * @param zoom      Panorama zoom. 1.0 = normal zoom, 2.0 = zoomed in 2x, 3.0 = zoomed in 4x, and so on.
     * @param mapZoom   The map zoom of the map location associated with this panorama.
     */
    @NonNull
    public static Intent showStreetView(float latitude,
                                        float longitude,
                                        @Nullable final Float yaw,
                                        @Nullable final Integer pitch,
                                        @Nullable final Float zoom,
                                        @Nullable final Integer mapZoom) {
        StringBuilder builder = new StringBuilder("google.streetview:cbll=")
                .append(latitude)
                .append(",")
                .append(longitude);
        if (!isNull(yaw, pitch, zoom)) {
            String cbpParam = String.format("%s,,%s,%s",
                    yaw == null ? "" : yaw,
                    pitch == null ? "" : pitch,
                    zoom == null ? "" : zoom);
            builder.append("&cbp=1,")
                    .append(cbpParam);
        }
        if (mapZoom != null) {
            builder.append("&mz=")
                    .append(mapZoom);
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(builder.toString()));
        return intent;
    }

    //==============================================================================================
    //                                      LOCATION
    //==============================================================================================

    /**
     * Opens the Maps application to the given location.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param zoomLevel A zoom level of 1 shows the whole Earth, centered at the given lat,lng.
     *                  A zoom level of 2 shows a quarter of the Earth, and so on. The highest zoom level is 23.
     *                  A larger zoom level will be clamped to 23.
     * @return instance of {@link Intent}
     * @see #findLocation(String)
     */
    @NonNull
    public static Intent showLocation(float latitude,
                                      float longitude,
                                      @Nullable final Integer zoomLevel) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String data = String.format("geo:%s,%s", latitude, longitude);
        if (zoomLevel != null) {
            data = String.format("%s?z=%s", data, zoomLevel);
        }
        intent.setData(Uri.parse(data));
        return intent;
    }

    /**
     * Opens the Maps application to the given query.
     *
     * @param query Query string
     * @return instance of {@link Intent}
     * @see #showLocation(float, float, Integer)
     */
    @NonNull
    public static Intent findLocation(String query) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String data = String.format("geo:0,0?q=%s", query);
        intent.setData(Uri.parse(data));
        return intent;
    }

    /**
     * Open system settings location services screen for turning on/off GPS
     *
     * @return instance of {@link Intent}
     */
    @NonNull
    public static Intent showLocationServices() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        return intent;
    }

    //==============================================================================================
    //                                      OPEN LINK
    //==============================================================================================

    /**
     * Open a browser window to the URL specified.
     *
     * @param url Target url
     * @return instance of {@link Intent}
     */
    @NonNull
    public static Intent openLink(@Nullable String url) {
        // if protocol isn't defined use http by default
        if (!isEmpty(url) && !url.contains("://")) {
            url = "http://" + url;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * @see #openLink(String)
     */
    @Nullable
    public static Intent openLink(@Nullable final URL url) throws NullPointerException {
        if (isNull(url)) {
            return null;
        }
        return openLink(url.toString());
    }

    //==============================================================================================
    //                                      OPEN VIDEO
    //==============================================================================================

    /**
     * Open a video file in appropriate app
     *
     * @param file instance of {@link File}
     * @return instance of {@link Intent}
     */
    @Nullable
    public static Intent openVideo(@Nullable final File file) throws NullPointerException {
        if (isNull(file)) {
            return null;
        }
        return openVideo(Uri.fromFile(file));
    }

    /**
     * Open a video file in appropriate app
     *
     * @param file instance of {@link String}
     * @return instance of {@link Intent}
     * @see #openVideo(java.io.File)
     */
    @Nullable
    public static Intent openVideo(@Nullable final String file) throws NullPointerException {
        if (isEmpty(file)) {
            return null;
        }
        return openVideo(new File(file));
    }

    /**
     * Open a video file in appropriate app
     *
     * @param uri instance of {@link Uri}
     * @return instance of {@link Intent}
     * @see #openVideo(java.io.File)
     */
    @Nullable
    public static Intent openVideo(@Nullable final Uri uri) throws NullPointerException {
        if (isNull(uri)) {
            return null;
        }
        return openMedia(uri, "video/*");
    }

    //==============================================================================================
    //                                      OPEN VIDEO
    //==============================================================================================

    /**
     * Open an audio file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     */
    @Nullable
    public static Intent openAudio(@Nullable final File file) throws NullPointerException {
        if (isNull(file)) {
            return null;
        }
        return openAudio(Uri.fromFile(file));
    }

    /**
     * Open an audio file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     * @see #openAudio(java.io.File)
     */
    @Nullable
    public static Intent openAudio(@Nullable final String file) throws NullPointerException {
        if (isEmpty(file)) {
            return null;
        }
        return openAudio(new File(file));
    }

    /**
     * Open an audio file in appropriate app
     *
     * @param uri instance of {@link Uri}
     * @return instance of {@link Intent}
     * @see #openAudio(java.io.File)
     */
    @Nullable
    public static Intent openAudio(@Nullable final Uri uri) throws NullPointerException {
        if (isEmpty(uri)) {
            return null;
        }
        return openMedia(uri, "audio/*");
    }

    //==============================================================================================
    //                                      OPEN IMAGE
    //==============================================================================================

    /**
     * Open an image file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     */
    @Nullable
    public static Intent openImage(@Nullable final String file) throws NullPointerException {
        if (isEmpty(file)) {
            return null;
        }
        return openImage(new File(file));
    }

    /**
     * Open an image file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     * @see #openImage(String)
     */
    @Nullable
    public static Intent openImage(@Nullable File file) throws NullPointerException {
        if (isNull(file)) {
            return null;
        }
        return openImage(Uri.fromFile(file));
    }

    /**
     * Open an image file in appropriate app
     *
     * @param uri instance of {@link Uri}
     * @return instance of {@link Intent}
     * @see #openImage(String)
     */
    @Nullable
    public static Intent openImage(@Nullable final Uri uri) throws NullPointerException {
        if (isNull(uri)) {
            return null;
        }
        return openMedia(uri, "image/*");
    }

    //==============================================================================================
    //                                      OPEN TEXT
    //==============================================================================================

    /**
     * Open a text file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     */
    @Nullable
    public static Intent openText(@Nullable final String file) throws NullPointerException {
        if (isEmpty(file)) {
            return null;
        }
        return openText(new File(file));
    }

    /**
     * Open a text file in appropriate app
     *
     * @param file File to open
     * @return instance of {@link Intent}
     * @see #openText(String)
     */
    @Nullable
    public static Intent openText(@Nullable final File file) throws NullPointerException {
        if (isEmpty(file)) {
            return null;
        }
        return openText(Uri.fromFile(file));
    }

    /**
     * Open a text file in appropriate app
     *
     * @param uri instance of {@link Uri}
     * @return instance of {@link Intent}
     * @see #openText(String)
     */
    @Nullable
    public static Intent openText(@Nullable final Uri uri) throws NullPointerException {
        if (isEmpty(uri)) {
            return null;
        }
        return openMedia(uri, "text/plain");
    }

    //==============================================================================================
    //                                      PICK FILE
    //==============================================================================================

    /**
     * Pick file from sdcard with file manager. Chosen file can be obtained from Intent in onActivityResult.
     * See code below for example:
     * <p/>
     * <pre><code>
     *     @Override
     *     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     *         Uri file = data.getData();
     *     }
     * </code></pre>
     */
    @NonNull
    public static Intent pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        return intent;
    }

    //==============================================================================================
    //                                      CALL PHONE
    //==============================================================================================

    /**
     * Calls the entered phone number. Valid telephone numbers as defined in the IETF RFC 3966 are accepted.
     * Valid examples include the following:
     * tel:2125551212
     * tel: (212) 555 1212
     * <p/>
     * Note: This requires your application to request the following permission in your manifest:
     * <code>&lt;uses-permission android:name="android.permission.CALL_PHONE"/&gt;</code>
     *
     * @param phoneNumber Phone number
     */
    @NonNull
    public static Intent callPhone(@Nullable final String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    //==============================================================================================
    //                                      PICK CONTACT
    //==============================================================================================

    /**
     * Pick contact from phone book
     */
    @NonNull
    public static Intent pickContact() {
        return pickContact(null);
    }

    /**
     * Pick contact from phone book
     */
    @NonNull
    public static Intent pickContact(@Nullable final String scope) {
        Intent intent;
        if (isSupportsContactsV2()) {
            intent = new Intent(Intent.ACTION_PICK,
                    Uri.parse("content://com.android.contacts/contacts"));
        } else {
            intent = new Intent(Intent.ACTION_PICK, Contacts.People.CONTENT_URI);
        }

        if (!TextUtils.isEmpty(scope)) {
            intent.setType(scope);
        }
        return intent;
    }

    /**
     * Pick contact only from contacts with telephone numbers
     */
    @NonNull
    public static Intent pickContactWithPhone() {
        Intent intent;
        if (isSupportsContactsV2()) {
            intent = pickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        } else { // pre Eclair, use old contacts API
            intent = pickContact(Contacts.Phones.CONTENT_TYPE);
        }
        return intent;
    }

    /**
     * Pick image from gallery
     */
    @NonNull
    public static Intent pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }

    /**
     * Dials (but does not actually initiate the call) the number given.
     * Telephone number normalization described for {@link #callPhone(String)} applies to dial as well.
     *
     * @param phoneNumber Phone number
     */
    @NonNull
    public static Intent dialPhone(@Nullable final String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Check that cropping application is available
     *
     * @param context Application context
     * @return true if cropping app is available
     * @see #cropImage(android.content.Context, java.io.File, int, int, int, int, boolean)
     */
    public static boolean isCropAvailable(Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        return isIntentAvailable(context, intent);
    }

    /**
     * Crop image. Before using, cropImage requires especial check that differs from
     * {@link #isIntentAvailable(android.content.Context, android.content.Intent)}
     * see {@link #isCropAvailable(android.content.Context)} instead
     *
     * @param context Application context
     * @param image   Image that will be used for cropping. This image is not changed during the cropImage
     * @param outputX Output image width
     * @param outputY Output image height
     * @param aspectX Crop frame aspect X
     * @param aspectY Crop frame aspect Y
     * @param scale   Scale or not cropped image if output image and cropImage frame sizes differs
     * @return Intent with <code>data</code>-extra in <code>onActivityResult</code> which contains result as a
     * {@link android.graphics.Bitmap}. See demo app for details
     */
    @NonNull
    public static Intent cropImage(@Nullable final Context context,
                                   @Nullable final File image,
                                   int outputX,
                                   int outputY,
                                   int aspectX,
                                   int aspectY,
                                   boolean scale) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
        ResolveInfo res = list.get(0);

        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("scale", scale);
        intent.putExtra("return-data", true);
        intent.setData(Uri.fromFile(image));

        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
        return intent;
    }

    /**
     * Call standard camera application for capturing an image
     *
     * @param file Full path to captured file
     */
    @NonNull
    public static Intent photoCapture(@Nullable final String file) {
        Uri uri = Uri.fromFile(new File(file));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * Check that in the system exists application which can handle this intent
     *
     * @param context Application context
     * @param intent  Checked intent
     * @return true if intent consumer exists, false otherwise
     */
    public static boolean isIntentAvailable(@Nullable final Context context,
                                            @Nullable final Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * Method which provide opening media
     *
     * @param uri      instance of {@link Uri}
     * @param mimeType mime type
     * @return instance of {@link Intent}
     */
    @NonNull
    private static Intent openMedia(@Nullable final Uri uri,
                                    @Nullable final String mimeType) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mimeType);
        return intent;
    }

    /**
     * Method which provide the checking if device support of the ContactsV2
     *
     * @return checking result
     */
    private static boolean isSupportsContactsV2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
    }

    //==============================================================================================
    //                                      SHARE IMAGE
    //==============================================================================================

    /**
     * Method which provide the {@link Intent} with intent sharing
     *
     * @param bitmap instance of the {@link Bitmap}
     * @return instance of the {@link Bitmap}
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    @Nullable
    public static Intent shareBitmap(@Nullable String title,
                                     @Nullable Context context,
                                     @Nullable Bitmap bitmap) {
        final String imageName = "share_tmp.png";
        Intent intent = null;
        try {
            File file = new File(context.getCacheDir(), imageName);
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            file.setReadable(true, false);
            final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            shareIntent.setType("image/png");
            intent = Intent.createChooser(shareIntent, title);
        } catch (Exception ex) {
            intent = null;
        }
        return intent;
    }

}
