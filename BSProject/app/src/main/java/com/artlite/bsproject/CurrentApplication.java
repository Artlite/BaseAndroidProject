package com.artlite.bsproject;

import android.app.Application;

import com.artlite.bslibrary.constants.BSTypeface;
import com.artlite.bslibrary.core.BSInstance;

/**
 * Created by dlernatovich on 3/23/2017.
 */

public class CurrentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BSInstance.init(this, BSTypeface.ROBOTO_CONSENDET);
    }
}
