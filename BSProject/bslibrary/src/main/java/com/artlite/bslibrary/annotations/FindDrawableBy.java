package com.artlite.bslibrary.annotations;

import android.support.annotation.DrawableRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which provide the getting of {@link android.graphics.drawable.Drawable} by ID
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindDrawableBy {
    @DrawableRes int id();
}
