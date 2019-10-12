package com.artlite.bslibrary.helpers.injector;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.artlite.bslibrary.annotations.FindColorBy;
import com.artlite.bslibrary.annotations.FindDrawableBy;
import com.artlite.bslibrary.annotations.FindLibraryViewBy;
import com.artlite.bslibrary.annotations.FindStringBy;
import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.callbacks.BSAnnotationCallback;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.helpers.annotation.BSAnnotationHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Class which provide the {@link Annotation} injecting
 */

public final class BSInjector extends BSBaseHelper {

    /**
     * Method which provide the injecting from the activity
     *
     * @param activity instance of the {@link Activity}
     */
    public static void inject(@Nullable final Activity activity) {
        if (validate(activity)) {
            inject(activity, null, activity.getApplicationContext());
        }
    }

    /**
     * Method which provide the injection from {@link Fragment}
     *
     * @param fragment instance of {@link Fragment}
     * @param view     view for injection
     */
    public static void inject(@Nullable final Fragment fragment,
                              @Nullable final View view) {
        if (validate(fragment, view)) {
            final Activity activity = fragment.getActivity();
            if (validate(activity)) {
                final Context context = activity.getApplicationContext();
                if (validate(context)) {
                    inject(fragment, view, context);
                }
            }
        }
    }

    /**
     * Method which provide the injection from {@link Fragment}
     *
     * @param view instance of {@link View}
     */
    public static void inject(@Nullable final View view) {
        if (validate(view)) {
            final Context context = view.getContext();
            if (validate(context)) {
                inject(view, null, context);
            }
        }
    }

    /**
     * Method which provide the inject annotation from {@link Object}
     *
     * @param object  instance of {@link Object}
     * @param context instance of {@link Context}
     */
    public static void inject(@Nullable final Object object,
                              @Nullable final Context context) {
        if (validate(context, object)) {
            inject(object, null, context);
        }
    }

    /**
     * Method which provide the {@link Annotation} injection to object
     *
     * @param object  object
     * @param context context
     * @param <T>     class of owner
     */
    protected static <T extends Object> void inject(@Nullable final T object,
                                                    @Nullable final View fragmentView,
                                                    @Nullable final Context context) {
        if (validate(object, context)) {
            final BSAnnotationCallback<T> callback =
                    new BSAnnotationCallback<T>() {
                        @Override
                        public void onFoundAnnotation(@NonNull T object,
                                                      @NonNull Annotation annotation,
                                                      @NonNull Field field) throws IllegalAccessException {
                            //======================================================================
                            //                             FindStringBy
                            //======================================================================
                            if (annotation instanceof FindStringBy) {
                                BSAnnotationHelper.annotateString(object, context, field, annotation);
                                //==================================================================
                                //             FindDrawableBy
                                //==================================================================
                            } else if (annotation instanceof FindDrawableBy) {
                                BSAnnotationHelper.annotateDrawable(object, context, field, annotation);
                                //==================================================================
                                //              FindColorBy
                                //==================================================================
                            } else if (annotation instanceof FindColorBy) {
                                BSAnnotationHelper.annotateColor(object, context, field, annotation);
                                //==================================================================
                                //             FindLibraryViewBy and FindViewBy
                                //==================================================================
                            } else if ((annotation instanceof FindLibraryViewBy) ||
                                    (annotation instanceof FindViewBy)) {
                                if (object instanceof View) {
                                    BSAnnotationHelper.annotateView((View) object, field, annotation);
                                } else if (object instanceof Activity) {
                                    BSAnnotationHelper.annotateActivity((Activity) object, field, annotation);
                                } else if (object instanceof Fragment) {
                                    BSAnnotationHelper.annotateFragment((Fragment) object,
                                            fragmentView, field, annotation);
                                }
                            }
                        }
                    };
            BSAnnotationHelper.annotate(object, callback, FindViewBy.class, FindStringBy.class,
                    FindLibraryViewBy.class, FindDrawableBy.class, FindColorBy.class);
        }
    }
}
