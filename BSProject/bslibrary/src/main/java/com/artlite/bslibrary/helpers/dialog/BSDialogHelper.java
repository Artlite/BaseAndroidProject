package com.artlite.bslibrary.helpers.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.ui.view.BSView;

/**
 * Class which provide the simplifying of the {@link android.app.Dialog} functional
 */

public final class BSDialogHelper extends BSBaseHelper {

    //==============================================================================================
    //                                 DIALOG WITH VIEW
    //==============================================================================================

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable final BSView view) {
        return create(context, true, null, null, null, view, null, null,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final BSView view) {
        return create(context, isCancelable, null, null, null, view, null, null,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final BSView view,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive) {
        return create(context, isCancelable, null, null, null, view, positiveTitle, positive,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @param negativeTitle @link String} value of the negative title
     * @param negative      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final BSView view,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive,
                                     @Nullable final String negativeTitle,
                                     @Nullable final DialogInterface.OnClickListener negative) {
        return create(context, isCancelable, null, null, null, view, positiveTitle, positive,
                negativeTitle, negative, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @param negativeTitle @link String} value of the negative title
     * @param negative      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final BSView view,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive,
                                     @Nullable final String negativeTitle,
                                     @Nullable final DialogInterface.OnClickListener negative,
                                     @Nullable final String neutralTitle,
                                     @Nullable final DialogInterface.OnClickListener neutral) {
        return create(context, isCancelable, null, null, null, view, positiveTitle, positive,
                negativeTitle, negative, neutralTitle, neutral);
    }

    //==============================================================================================
    //                                 DIALOG WITH MESSAGE
    //==============================================================================================

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context instance of {@link Context}
     * @param message {@link String} value of the message
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable final String message) {
        return create(context, true, null, message, null, null, null, null,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context instance of {@link Context}
     * @param message {@link String} value of the message
     * @param icon    {@link Integer} value of the icon for {@link AlertDialog}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable final String message,
                                     @DrawableRes Integer icon) {
        return create(context, true, null, message, icon, null, null, null,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context instance of {@link Context}
     * @param title   {@link String} value of the title
     * @param message {@link String} value of the message
     * @param icon    {@link Integer} value of the icon for {@link AlertDialog}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable final String title,
                                     @Nullable final String message,
                                     @DrawableRes Integer icon) {
        return create(context, true, title, message, icon, null, null, null,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param title         {@link String} value of the title
     * @param message       {@link String} value of the message
     * @param icon          {@link Integer} value of the icon for {@link AlertDialog}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final String title,
                                     @Nullable final String message,
                                     @DrawableRes Integer icon,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive) {
        return create(context, isCancelable, title, message, icon, null, positiveTitle, positive,
                null, null, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param title         {@link String} value of the title
     * @param message       {@link String} value of the message
     * @param icon          {@link Integer} value of the icon for {@link AlertDialog}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @param negativeTitle @link String} value of the negative title
     * @param negative      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final String title,
                                     @Nullable final String message,
                                     @DrawableRes Integer icon,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive,
                                     @Nullable final String negativeTitle,
                                     @Nullable final DialogInterface.OnClickListener negative) {
        return create(context, isCancelable, title, message, icon, null, positiveTitle, positive,
                negativeTitle, negative, null, null);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param title         {@link String} value of the title
     * @param message       {@link String} value of the message
     * @param icon          {@link Integer} value of the icon for {@link AlertDialog}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @param negativeTitle @link String} value of the negative title
     * @param negative      instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    public static AlertDialog create(@Nullable final Context context,
                                     @Nullable Boolean isCancelable,
                                     @Nullable final String title,
                                     @Nullable final String message,
                                     @DrawableRes Integer icon,
                                     @Nullable final String positiveTitle,
                                     @Nullable final DialogInterface.OnClickListener positive,
                                     @Nullable final String negativeTitle,
                                     @Nullable final DialogInterface.OnClickListener negative,
                                     @Nullable final String neutralTitle,
                                     @Nullable final DialogInterface.OnClickListener neutral) {
        return create(context, isCancelable, title, message, icon, null, positiveTitle, positive,
                negativeTitle, negative, neutralTitle, neutral);
    }

    /**
     * Method which provide the create the of the {@link AlertDialog} from
     *
     * @param context       instance of {@link Context}
     * @param title         {@link String} value of the title
     * @param message       {@link String} value of the message
     * @param icon          {@link Integer} value of the icon for {@link AlertDialog}
     * @param isCancelable  {@link Boolean} value if it cancellable
     * @param view          instance of the {@link BSView}
     * @param positiveTitle {@link String} value of the positive title
     * @param positive      instance of the {@link DialogInterface.OnClickListener}
     * @param negativeTitle @link String} value of the negative title
     * @param negative      instance of the {@link DialogInterface.OnClickListener}
     * @param neutralTitle  @link String} value of the neutral title
     * @param neutral       instance of the {@link DialogInterface.OnClickListener}
     * @return instance of the {@link AlertDialog}
     */
    @Nullable
    protected static AlertDialog create(@Nullable final Context context,
                                        @Nullable Boolean isCancelable,
                                        @Nullable final String title,
                                        @Nullable final String message,
                                        @DrawableRes Integer icon,
                                        @Nullable final BSView view,
                                        @Nullable final String positiveTitle,
                                        @Nullable final DialogInterface.OnClickListener positive,
                                        @Nullable final String negativeTitle,
                                        @Nullable final DialogInterface.OnClickListener negative,
                                        @Nullable final String neutralTitle,
                                        @Nullable final DialogInterface.OnClickListener neutral) {
        //Check if Context is null
        if (BSValidationHelper.isEmpty(context)) {
            return null;
        }

        //Create result
        AlertDialog result = null;

        //Is have buttons
        boolean isHaveButtons = false;

        //Start building
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set dialog title
        builder.setTitle((title == null) ? "" : title);

        //Set cancelable
        builder.setCancelable((isCancelable == null) ? true : isCancelable);

        //Set content of the AlertDialog
        if (message != null) {
            builder.setMessage(message);
            //Set icon
            if (icon != null) {
                builder.setIcon(icon);
            }
        } else if (view != null) {
            builder.setView(view);
        } else {
            builder.setMessage("");
            //Set icon
            if (icon != null) {
                builder.setIcon(icon);
            }
        }

        //Set positive button
        if (!BSValidationHelper.isEmpty(positiveTitle, positive)) {
            builder.setPositiveButton(positiveTitle, positive);
            isHaveButtons = true;
        }

        //Set negative button
        if (!BSValidationHelper.isEmpty(negativeTitle, negative)) {
            builder.setNegativeButton(negativeTitle, negative);
            isHaveButtons = true;
        }

        //Set neutral button
        if (!BSValidationHelper.isEmpty(positiveTitle, positive)) {
            builder.setNeutralButton(neutralTitle, neutral);
            isHaveButtons = true;
        }

        // If Dialog don't have any buttons than do it cancelable
        if ((!isHaveButtons) && (view == null)) {
            builder.setCancelable(true);
        }

        //Create dialog
        result = builder.create();

        //Clear background if needed
        if ((message == null) && (view != null) && (isHaveButtons == false)) {
            result.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        //Set AlertDialog inside the BSView
        if (view != null) {
            view.setDialog(result);
        }

        //Return result
        return result;
    }

}
