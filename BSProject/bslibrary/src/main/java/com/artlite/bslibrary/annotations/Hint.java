package com.artlite.bslibrary.annotations;


import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation which provide the hint functional
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Hint {
    @NonNull String message();
}
