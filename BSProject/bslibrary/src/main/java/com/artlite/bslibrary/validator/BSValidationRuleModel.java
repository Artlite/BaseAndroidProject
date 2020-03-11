package com.artlite.bslibrary.validator;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.lang.ref.WeakReference;

/**
 * Method which provide the model for the rule model
 */
public abstract class BSValidationRuleModel {

    /**
     * Instance of the {@link WeakReference}
     */
    private WeakReference<TextView> textReference;

    /**
     * Method which provide the setting of the {@link TextView}
     *
     * @param textView instance of the {@link TextView}
     */
    public void set(TextView textView) {
        this.textReference = new WeakReference<>(textView);
    }

    /**
     * Method which provide the getting of the {@link TextView}
     *
     * @return instance of the {@link TextView}
     */
    @Nullable
    private TextView getTextView() {
        return this.textReference.get();
    }

    /**
     * Method which provide the validation functional
     *
     * @return {@link Boolean} value of the validation result
     */
    public boolean validate() {
        final TextView textView = this.getTextView();
        if (textView == null) {
            return false;
        }
        return this.validateValue(textView.getText().toString().trim());
    }

    /**
     * Method which provide the validate value
     *
     * @param text instance of the {@link String} value
     * @return validation result
     */
    abstract boolean validateValue(@Nullable String text);

    /**
     * Method which provide the get message
     *
     * @return {@link Integer} value of the message
     */
    @StringRes
    abstract int getMessage();

}
