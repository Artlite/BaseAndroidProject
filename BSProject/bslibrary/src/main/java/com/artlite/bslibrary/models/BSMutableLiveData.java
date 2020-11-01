package com.artlite.bslibrary.models;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.artlite.bslibrary.helpers.log.BSLogHelper;

import java.util.Date;

/**
 * Customized live data
 *
 * @param <T>
 */
public class BSMutableLiveData<T> extends MutableLiveData<T> {

    /**
     * Update functional only if value is not the same.
     *
     * @param value
     */
    public void updateIfNeeded(@Nullable T value) {
        try {
            if (value == null) {
                this.setValue(value);
                return;
            }
            if (value instanceof Date) {
                updateDate((Date) value);
                return;
            }
            if (value instanceof String) {
                updateString((String) value);
                return;
            }
            if (value instanceof Boolean) {
                updateBoolean((Boolean) value);
                return;
            }
            if (value instanceof Integer) {
                updateInteger((Integer) value);
                return;
            }
            if (value instanceof Float) {
                updateFloat((Float) value);
                return;
            }
            if (value instanceof Double) {
                updateDouble((Double) value);
                return;
            }
            if (value instanceof Long) {
                updateLong((Long) value);
                return;
            }
            this.setValue(value);
        } catch (Exception ex) {
            BSLogHelper.log(this, "updateIfNeeded", ex, null);
        }
    }

    /**
     * Update date.
     *
     * @param value
     */
    private void updateDate(@Nullable Date value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Date innerValue = (Date) this.getValue();
        if (innerValue.compareTo(value) == 0) return;
        this.setValue((T) value);
    }

    /**
     * Update string value.
     *
     * @param value
     */
    private void updateString(@Nullable String value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        String innerValue = (String) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

    /**
     * Update boolean value.
     *
     * @param value
     */
    private void updateBoolean(@Nullable Boolean value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Boolean innerValue = (Boolean) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

    /**
     * Update boolean value.
     *
     * @param value
     */
    private void updateInteger(@Nullable Integer value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Integer innerValue = (Integer) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

    /**
     * Update boolean value.
     *
     * @param value
     */
    private void updateFloat(@Nullable Float value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Float innerValue = (Float) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

    /**
     * Update boolean value.
     *
     * @param value
     */
    private void updateDouble(@Nullable Double value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Double innerValue = (Double) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

    /**
     * Update boolean value.
     *
     * @param value
     */
    private void updateLong(@Nullable Long value) {
        if (value == null || this.getValue() == null) {
            this.setValue((T) value);
            return;
        }
        Long innerValue = (Long) this.getValue();
        if (innerValue.equals(value)) return;
        this.setValue((T) value);
    }

}
