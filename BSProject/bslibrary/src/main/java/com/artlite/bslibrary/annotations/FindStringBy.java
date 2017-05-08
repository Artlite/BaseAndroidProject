package com.artlite.bslibrary.annotations;

import android.support.annotation.StringRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that allow to get string from Resource folder of Android
 * (USING FOR LOCALIZATION OF THE USER INTERFACE)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindStringBy {
    @StringRes int id();
}
