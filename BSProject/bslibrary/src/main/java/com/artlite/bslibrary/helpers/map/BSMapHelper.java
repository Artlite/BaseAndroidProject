package com.artlite.bslibrary.helpers.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.util.List;
import java.util.Locale;

/**
 * Class which provide the helper for the Google Map
 */

public final class BSMapHelper extends BSBaseHelper {

    /**
     * {@link String} value of the link format
     */
    private static final String K_PREVIEW_LINK_FORMAT =
            "https://maps.googleapis.com/maps/api/staticmap" +
                    "?center=%s,%s" +
                    "&markers=color:red%%7Clabel:C%%7C%s,%s" +
                    "&size=%dx%d" +
                    "&zoom=%d";

    /**
     * {@link Double} value of the average of the earth radius
     */
    private static final double K_AVERAGE_RADIUS_OF_EARTH = 6371;

    /**
     * {@link String} constants of the TAG
     */
    private static final String TAG = BSMapHelper.class.getSimpleName();

    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @return {@link String} url value for the preview
     */
    @Nullable
    public static String getPreviewSmallMap(@Nullable final String latitude,
                                            @Nullable final String longitude) {
        return getPreviewMap(latitude, longitude, 250, 250, 18);
    }

    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @return {@link String} url value for the preview
     */
    @Nullable
    public static String getPreviewLargeMap(@Nullable final String latitude,
                                            @Nullable final String longitude) {
        return getPreviewMap(latitude, longitude, 600, 600, 18);
    }

    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @param width     {@link Integer} value of the width
     * @param height    {@link Integer} value of the height
     * @param zoom      {@link Integer} value of the zoom
     * @return {@link String} url value for the preview
     */
    @Nullable
    @SuppressLint("DefaultLocale")
    public static String getPreviewMap(@Nullable final String latitude,
                                       @Nullable final String longitude,
                                       @IntRange(from = 200, to = 600) int width,
                                       @IntRange(from = 200, to = 600) int height,
                                       @IntRange(from = 1, to = 30) int zoom) {
        if (!isEmpty(latitude, longitude)) {
            return String.format(K_PREVIEW_LINK_FORMAT,
                    latitude,
                    longitude,
                    latitude,
                    longitude,
                    width,
                    height,
                    zoom);
        }
        return null;
    }

    /**
     * Method which provide the getting {@link Double} value of the distance between coordinates
     *
     * @param startLatitude  {@link Double} value of the start latitude
     * @param startLongitude {@link Double} value of the start longitude
     * @param endLatitude    {@link Double} value of the end latitude
     * @param endLongitude   {@link Double} value of the end longitude
     * @return
     */
    public static double getDistance(double startLatitude,
                                     double startLongitude,
                                     double endLatitude,
                                     double endLongitude) {
        double latDistance = Math.toRadians(startLatitude - endLatitude);
        double lngDistance = Math.toRadians(startLongitude - endLongitude);
        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(startLatitude))) *
                        (Math.cos(Math.toRadians(endLatitude))) *
                        (Math.sin(lngDistance / 2)) *
                        (Math.sin(lngDistance / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) (Math.round(K_AVERAGE_RADIUS_OF_EARTH * c));
    }

    /**
     * Method which provide the getting of the address from the latitude and longitude
     *
     * @param context   instance of the {@link Context}
     * @param latitude  {@link Double} value of the latitude
     * @param longitude {@link Double} value of the longitude
     * @return instance of the {@link Address}
     */
    @Nullable
    public static Address getAddress(@Nullable Context context,
                                     double latitude,
                                     double longitude) {
        if (context == null) return null;
        try {
            final Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder
                    .getFromLocation(latitude, longitude, 1);
            return addresses.get(0);
        } catch (Exception ex) {
            Log.e(TAG, "getAddress: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the getting of the address from the latitude and longitude
     *
     * @param context   instance of the {@link Context}
     * @param latitude  {@link Double} value of the latitude
     * @param longitude {@link Double} value of the longitude
     * @return instance of the {@link Address}
     */
    @SuppressLint("DefaultLocale")
    @Nullable
    public static String getAddressName(@Nullable Context context,
                                        double latitude,
                                        double longitude) {
        if (context == null) return null;
        try {
            return getAddress(context, latitude, longitude).getAddressLine(0);
        } catch (Exception ex) {
            Log.e(TAG, "getAddressName: ", ex);
        }
        return String.format("%.2f, %.2f", latitude, longitude);
    }

}
