package com.artlite.bslibrary.ui.fonted;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

import com.artlite.bslibrary.helpers.format.BSFormatHelper;
import com.artlite.bslibrary.listeners.BSEditFinishListener;
import com.artlite.bslibrary.listeners.BSFocusLostListener;

public class BSCurrencyEditText extends BSEditText {

    /**
     * Class which provide the action from {@link BSCurrencyEditText}
     */
    public interface OnCurrencyEditCallback {

        /**
         * Method which provide the action when the {@link BSCurrencyEditText} start
         * editing
         *
         * @param editText instance of the {@link BSCurrencyEditText}
         * @param editID   {@link Integer} value of the edit text ID
         * @param value    {@link Double} value of the value
         */
        void currencyEditStartEditing(@NonNull BSCurrencyEditText editText,
                                      int editID,
                                      double value);

        /**
         * Method which provide the action when the {@link BSCurrencyEditText} finish
         * editing
         *
         * @param editText instance of the {@link BSCurrencyEditText}
         * @param editID   {@link Integer} value of the edit text ID
         * @param value    {@link Double} value of the value
         */
        void currencyEditFinishEditing(@NonNull BSCurrencyEditText editText,
                                       int editID,
                                       double value);

    }


    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSCurrencyEditText.class.getSimpleName();

    /**
     * Instance of the {@link OnCurrencyEditCallback}
     */
    protected OnCurrencyEditCallback callback;

    /**
     * Constructor which provide the creating of the {@link BSCurrencyEditText} from
     *
     * @param context instance of {@link Context}
     */
    public BSCurrencyEditText(Context context) {
        super(context);
        this.onInit();
    }

    /**
     * Constructor which provide the creating of the {@link BSCurrencyEditText} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSCurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.onInit();
    }

    /**
     * Constructor which provide the creating of the {@link BSCurrencyEditText} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr value of default style attributes
     */
    public BSCurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onInit();
    }

    /**
     * Method which provide the on init {@link BSCurrencyEditText}
     */
    protected void onInit() {
        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        this.setOnEditorActionListener(new BSEditFinishListener());
        this.setOnFocusChangeListener(new BSFocusLostListener<BSCurrencyEditText>() {
            @Override
            public void onFocusLost(@NonNull BSCurrencyEditText view) {
                onFinishEdit();
            }

            @Override
            public void onFocusStart(@NonNull BSCurrencyEditText view) {
                onStartEdit();
            }
        });
    }

    /**
     * Method which provide the configuring of the {@link OnCurrencyEditCallback}
     *
     * @param callback instance of the {@link OnCurrencyEditCallback}
     */
    public void configure(@Nullable OnCurrencyEditCallback callback) {
        this.callback = callback;
    }

    /**
     * Method which provide the action when the edit text finished
     */
    protected void onFinishEdit() {
        double value = getValue() / 100;
        this.setText(BSFormatHelper.format(value, 2));
        try {
            this.callback.currencyEditFinishEditing(this, this.getId(), this.getValue());
        } catch (Exception ex) {
            Log.e(TAG, "onFinishEdit: ", ex);
        }
    }

    /**
     * Method which provide the start edit functional
     */
    protected void onStartEdit() {
        try {
            this.callback.currencyEditStartEditing(this, this.getId(), this.getValue());
        } catch (Exception ex) {
            Log.e(TAG, "onFinishEdit: ", ex);
        }
    }

    /**
     * Method which provide the getting value
     *
     * @return value of the currency
     */
    public double getValue() {
        double value = 0;
        try {
            value = Double.valueOf(this.getStringValue());
        } catch (Exception ex) {
            Log.e(TAG, "getValue: ", ex);
        }
        return value;
    }
}
