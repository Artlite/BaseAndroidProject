package com.artlite.bslibrary.validator;

import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Class which provide the field validator
 */
public class BSFieldsValidator {

    /**
     * Instance of the {@link ArrayList}
     */
    private final List<BSFieldObject> objects = new ArrayList<>();

    /**
     * Method which provide the add validators to the {@link TextView}
     *
     * @param textView instance of the {@link TextView}
     * @param models   array of the {@link BSValidationRuleModel}
     */
    public synchronized void add(@Nullable final TextView textView,
                                 @Nullable BSValidationRuleModel... models) {
        if (textView == null) {
            return;
        }
        if (models == null) {
            return;
        }
        if (models.length <= 0) {
            return;
        }
        final BSFieldObject object = new BSFieldObject(textView);
        for (BSValidationRuleModel model : models) {
            if (model != null) {
                model.set(textView);
                object.add(model);
            }
        }
    }

    /**
     * Method which provide the validate all field in the groups
     *
     * @return validation result
     */
    public boolean validateAll() {
        boolean result = true;
        if (this.objects.size() <= 0) {
            return false;
        }
        final ListIterator<BSFieldObject> iterator = this.objects.listIterator();
        while (iterator.hasNext()) {
            final BSFieldObject object = iterator.next();
            if (!object.validate()) {
                result = false;
            }
        }
        return result;
    }
}
