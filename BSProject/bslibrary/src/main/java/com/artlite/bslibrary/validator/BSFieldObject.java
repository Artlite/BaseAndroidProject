package com.artlite.bslibrary.validator;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.managers.BSContextManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Field validator object
 */
public class BSFieldObject {

    /**
     * {@link Integer} value of the field ID
     */
    @IdRes
    private final int fieldId;

    /**
     * Instance of the {@link WeakReference}
     */
    private final WeakReference<TextView> textReference;

    /**
     * Instance of the {@link ArrayList}
     */
    private final List<BSValidationRuleModel> validators = new ArrayList<>();

    /**
     * Default constructor
     *
     * @param textView instance of the {@link TextView}
     */
    public BSFieldObject(@NonNull TextView textView) {
        this.fieldId = textView.getId();
        this.textReference = new WeakReference<>(textView);
    }

    /**
     * Method which provide the add validators
     *
     * @param items array of the {@link BSValidationRuleModel}
     */
    public synchronized void add(@Nullable BSValidationRuleModel... items) {
        if ((items == null) || (items.length <= 0)) return;
        for (BSValidationRuleModel validator : items) {
            if (validator != null) this.validators.add(validator);
        }
    }

    /**
     * Method which provide the validation functional
     *
     * @return validation result
     */
    public boolean validate() {
        final TextView textView = this.textReference.get();
        final Context context = BSContextManager.getApplicationContext();
        if (textView == null) return false;
        if (this.validators.size() <= 0) return true;
        final ListIterator<BSValidationRuleModel> iterator = this.validators.listIterator();
        while (iterator.hasNext()) {
            final BSValidationRuleModel ruleModel = iterator.next();
            if (!ruleModel.validate()) {
                if (context != null) {
                    textView.setError(context.getString(ruleModel.getMessage()));
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Method which provide the equaling functional
     *
     * @param o instance of the {@link Object}
     * @return equaling result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BSFieldObject object = (BSFieldObject) o;
        return fieldId == object.fieldId;
    }

    /**
     * Method which provide the hash code
     *
     * @return {@link Integer} value of the hash
     */
    @Override
    public int hashCode() {
        return fieldId;
    }
}
