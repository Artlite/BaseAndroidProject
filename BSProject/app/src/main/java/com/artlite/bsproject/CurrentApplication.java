package com.artlite.bsproject;

import android.app.Application;

import com.artlite.bslibrary.constants.BSTypeface;
import com.artlite.bslibrary.core.BSInstance;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.managers.BSCryptManager;
import com.artlite.bslibrary.managers.BSCryptSharedPreferenceManager;
import com.artlite.bslibrary.managers.BSSignManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dlernatovich on 3/23/2017.
 */

public class CurrentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BSInstance.init(this);

        final String text = "Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing Elit Sed Do Eiusmod Tempor Inc";
        final Date date = new Date();
        final List<String> strings = new ArrayList<>(Arrays.asList(new String[]{
                "Lorem Ipsum Dolor Sit Amet Co",
                "Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing Elit Sed Do Eiusmo",
                "Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing Elit Sed Do Eiusmod Temp"

        }));

        BSCryptSharedPreferenceManager.save(date, "K_DATE");
        BSCryptSharedPreferenceManager.save(text, "K_TEXT");
        BSCryptSharedPreferenceManager.save(strings, "K_TEXTS");

        final String text1 = BSCryptSharedPreferenceManager.getString("K_TEXT");
        final Date date1 = BSCryptSharedPreferenceManager.getDate("K_DATE");
        final List<String> strings1 = BSCryptSharedPreferenceManager.getList("K_TEXTS");

        BSLogHelper.log(this, "onCreate1", null, text1);
        BSLogHelper.log(this, "onCreate1", null, date1);
        BSLogHelper.log(this, "onCreate1", null, strings1);
    }

}
