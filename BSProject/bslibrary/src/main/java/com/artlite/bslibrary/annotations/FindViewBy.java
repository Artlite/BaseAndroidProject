package com.artlite.bslibrary.annotations;


import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which provide to inject view by {@link java.lang.annotation.Annotation}
 * (SIMILAR TO BUTTERKNIFE)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindViewBy {
    @IdRes int id();
}
