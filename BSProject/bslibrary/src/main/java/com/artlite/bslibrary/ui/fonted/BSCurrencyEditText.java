package com.artlite.bslibrary.ui.fonted;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.ime.BSImeHelper;
import com.artlite.bslibrary.listeners.BSFocusLostListener;
import com.artlite.bslibrary.managers.BSContextManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class BSCurrencyEditText extends BSEditText implements TextView.OnEditorActionListener,
        TextWatcher {

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
     * Instance of the {@link FrameLayout}
     */
    protected FrameLayout coverLayout;

    /**
     * {@link String} value of the inner text
     */
    private String innerText;

    /**
     * Instance of the {@link Locale}
     */
    protected Locale locale;

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
        this.onInitAttributes(attrs);
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
        this.onInitAttributes(attrs);
        this.onInit();
    }

    /**
     * Method which provide the init attributes
     *
     * @param attributes instance of the {@link AttributeSet}
     */
    protected void onInitAttributes(@NonNull AttributeSet attributes) {
        TypedArray attr = getContext().getTheme().obtainStyledAttributes(attributes,
                R.styleable.BSCurrencyEditText, 0, 0);
        int textSize = attr.getDimensionPixelSize(R.styleable.BSCurrencyEditText_currencyTextSize,
                getResources().getDimensionPixelSize(R.dimen.text_14));
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        attr.recycle();
    }

    /**
     * Method which provide the on init {@link BSCurrencyEditText}
     */
    protected void onInit() {
        this.innerText = "";
        this.setCursorVisible(false);
        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        this.addTextChangedListener(this);
        this.setOnEditorActionListener(this);
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
     * @param locale   instance of the {@link Locale}
     * @param callback instance of the {@link OnCurrencyEditCallback}
     */
    public void configure(@Nullable Locale locale,
                          @Nullable OnCurrencyEditCallback callback) {
        this.callback = callback;
        this.locale = locale;
        try {
            this.setText(this.getStringValue());
        } catch (Exception ex) {
            Log.e(TAG, "configure: ", ex);
        }
    }

    /**
     * Method which provide the action when the edit text finished
     */
    protected void onFinishEdit() {
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
     * Method which provide of the {@link TextView} editor action
     *
     * @param textView instance of the {@link TextView}
     * @param actionId {@link Integer} value of the action id
     * @param event    instance of the {@link KeyEvent}
     * @return {@link Boolean} value if the action needed
     */
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            if (event == null || !event.isShiftPressed()) {
                textView.clearFocus();
                BSImeHelper.hideKeyboard(BSContextManager.getApplicationContext(), textView);
                return true; // consume.
            }
        }
        return false;
    }

    /**
     * Method which provide the action before the {@link TextView} changed
     *
     * @param charSequence {@link CharSequence} of the text
     * @param i            instance of the {@link Integer}
     * @param i1           instance of the {@link Integer}
     * @param i2           instance of the {@link Integer}
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        this.innerText += charSequence;
    }

    /**
     * Method which provide the action of the {@link TextView} changed
     *
     * @param charSequence {@link CharSequence} of the text
     * @param i            instance of the {@link Integer}
     * @param i1           instance of the {@link Integer}
     * @param i2           instance of the {@link Integer}
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!charSequence.toString().equals(innerText)) {
            this.removeTextChangedListener(this);
            try {
                String cleanString = charSequence.toString()
                        .replaceAll(getRegEx(getLocale(), true), "").trim();
                double parsed = 0;
                try {
                    parsed = Double.parseDouble(cleanString);
                } catch (Exception ex) {
                    Log.e(TAG, "onTextChanged: ", ex);
                }
                String formatted = NumberFormat.getCurrencyInstance(this.getLocale())
                        .format((parsed / 100));
                this.innerText = formatted;
                this.setText(formatted);
                this.setSelection(formatted.length());
            } catch (Exception ex) {
                Log.e(TAG, "onTextChanged: ", ex);
            }
            this.addTextChangedListener(this);
        }
    }

    /**
     * Method which provide the action after text changed
     *
     * @param editable instance of the {@link Editable}
     */
    @Override
    public void afterTextChanged(Editable editable) {

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

    /**
     * Method which provide the getting text from the current component
     *
     * @return current String value
     */
    @Override
    public String getStringValue() {
        String text = super.getStringValue();
        String cleanString = text
                .replaceAll(getRegEx(getLocale(), false), "").trim()
                .replaceAll(",", ".");
        return cleanString;
    }

    /**
     * Method which provide the selection changed
     *
     * @param selStart {@link Integer} value of the start
     * @param selEnd   {@link Integer} value of the end
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        setSelection(this.length());
    }

    /**
     * Method which provide the getting {@link Locale}
     *
     * @return instance of the {@link Locale}
     */
    @NonNull
    protected Locale getLocale() {
        if (this.locale == null) {
            this.locale = Locale.getDefault();
        }
        return this.locale;
    }

    /**
     * Method which provide the getting of the regular expression
     *
     * @param locale        instance of the {@link Locale}
     * @param needSeparator {@link Boolean} value if it need separator
     * @return instance of the {@link String}
     */
    protected String getRegEx(@NonNull Locale locale, boolean needSeparator) {
        try {
            Currency currency = Currency.getInstance(locale);
            String currencySymbol = currency.getSymbol().replaceAll("\\w", "");
            String decimal = "" + ((DecimalFormat) DecimalFormat.getInstance(locale))
                    .getDecimalFormatSymbols().getDecimalSeparator() + "";
            String numberDivider = "" + ((DecimalFormat) DecimalFormat.getInstance(locale))
                    .getDecimalFormatSymbols().getGroupingSeparator() + "";
            if (numberDivider.equals(" ")) {
                numberDivider = "";
            }
            String regex = (needSeparator)
                    ? "[" + currencySymbol + "" + numberDivider + "" + decimal + "\\s]"
                    : "[" + currencySymbol + "" + numberDivider + "\\s]";
            return regex;
        } catch (Exception ex) {
            Log.e(TAG, "getRegEx: ", ex);
            return "";
        }
    }

}
