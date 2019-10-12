package com.artlite.bslibrary.annotations;


import androidx.annotation.ColorRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which provide the getting {@link android.graphics.Color} by ID
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindColorBy {
    @ColorRes int id();
}
