package com.artlite.bslibrary.managers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;

import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.NETWORK_PROVIDER;

/**
 * Manager which provide the location monitoring
 */

public final class BSLocationManager extends BSBaseManager implements LocationListener {

    /**
     * Instance of the {@link BSLocationManager}
     */
    private static BSLocationManager instance;

    /**
     * Instance of the {@link LocationManager}
     */
    private final LocationManager locationManager;

    /**
     * Instance of the {@link Location}
     */
    private Location location;

    /**
     * {@link String} value of the location name
     */
    private String locationName;

    /**
     * Default constructor
     *
     * @param context
     */
    private BSLocationManager(@NonNull Context context) {
        super(context);
        this.locationManager = (LocationManager) BSContextManager.getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Method which provide the initialization of {@link BSRandomManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSLocationManager(context);
        } else {
            Log.e(TAG, "BSRandomManager is already created");
        }
    }

    /**
     * Method which provide the getting of the instance of the {@link BSLocationManager}
     *
     * @return instance of the {@link BSLocationManager}
     */
    protected static BSLocationManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "BSLocationManager should be initialized the Application singleton");
        }
        return instance;
    }


    /**
     * Method which provide the getting of the instance of the {@link Location}
     *
     * @return instance of the {@link Location}
     */
    @Nullable
    @SuppressWarnings({"MissingPermission"})
    public static Location getLocation() {
        final String methodName = "Location getLocation()";
        if (getInstance() != null) {
            final BSLocationManager manager = getInstance();
            try {
                manager.setLocation(manager
                        .locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            } catch (Exception ex) {
                BSLogHelper.log(manager, methodName, ex, manager.locationManager);
            }

            try {
                if (manager.location == null) {
                    manager.setLocation(manager.locationManager
                            .getLastKnownLocation(NETWORK_PROVIDER));
                }
            } catch (Exception ex) {
                BSLogHelper.log(manager, methodName, ex, manager.locationManager);
            }
            return manager.location;
        }
        return null;
    }

    /**
     * Method which provide the getting of the instance of the {@link Location} name
     *
     * @return instance of the {@link String}
     */
    @Nullable
    public static String getLocationName() {
        final String methodName = "Location getLocation()";
        if (getInstance() != null) {
            final BSLocationManager manager = getInstance();
            if ((manager.locationName == null)
                    && (manager.location != null)) {
                manager.obtainName(manager.location);
            }
            return manager.locationName;
        }
        return null;
    }

    /**
     * Method which provide the starting of the {@link Location} monitoring
     *
     * @param activity instance of the {@link Activity}
     */
    @SuppressLint("MissingPermission")
    public static void startLocationMonitoring(@Nullable final Activity activity) {
        if (getInstance() != null) {
            final BSLocationManager manager = getInstance();
            if (manager.locationManager != null) {
                final Context context = manager.getContext();
                if (!BSPermissionHelper.isGranted(context,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            0b1010);
                    return;
                } else {
                    manager.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            0, 0, manager);
                }
            }
        }
    }

    /**
     * Method which provide the setting {@link Location}
     *
     * @param location instance of the {@link Location}
     */
    protected void setLocation(@Nullable Location location) {
        if (location != null) {
            this.location = location;
            this.obtainName(this.location);
        }
    }

    /**
     * Method which provide the obtaining the name from the {@link Location}
     *
     * @param location instance of the {@link Location}
     */
    protected void obtainName(@NonNull Location location) {
        final String methodName = "void obtainName(Location)";
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> listAddresses = geocoder
                    .getFromLocation(latitude, longitude, 1);
            if ((listAddresses != null)
                    && (listAddresses.size() > 0)) {
                String name = listAddresses.get(0).getAddressLine(0);
                if (name != null) {
                    this.locationName = name;
                }
            }
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
        }
    }

    /**
     * Method which provide the action when {@link Location} changed
     *
     * @param location instance of the {@link Location}
     */
    @Override
    public void onLocationChanged(Location location) {
        this.setLocation(location);
    }

    /**
     * Method which provide the action when the status changes
     *
     * @param s      instance of the {@link String}
     * @param i      instance of the {@link Integer}
     * @param bundle instance of the {@link Bundle}
     */
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    /**
     * Method which provide the action when the provider was enabled
     *
     * @param s instance of the {@link String}
     */
    @Override
    public void onProviderEnabled(String s) {

    }

    /**
     * Method which provide the action when the provider was disabled
     *
     * @param s instance of the {@link String}
     */
    @Override
    public void onProviderDisabled(String s) {

    }
}
