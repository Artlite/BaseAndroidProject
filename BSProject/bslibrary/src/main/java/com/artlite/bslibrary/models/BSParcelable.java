package com.artlite.bslibrary.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class which provide the {@link Parcelable} functional
 * Created by dlernatovich on 2/17/2017.
 */
public interface BSParcelable extends Parcelable {
    /**
     * Method which provide the applying {@link Parcel}
     *
     * @param parcel instance of {@link Parcel}
     */
    void apply(Parcel parcel);
}
