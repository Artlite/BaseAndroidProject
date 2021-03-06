package com.artlite.bslibrary.ui.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.artlite.bslibrary.annotations.Warning;
import com.artlite.bslibrary.managers.BSTypefaceManager;
import com.artlite.bslibrary.validator.BSFieldObject;
import com.artlite.bslibrary.validator.BSValidationRuleModel;

/**
 * Class which provide the customize font for {@link EditText}
 */

public class BSEditText extends AppCompatEditText {

    /**
     * Instance of the {@link BSFieldObject}
     */
    private BSFieldObject fieldObject;

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context instance of {@link Context}
     */
    public BSEditText(Context context) {
        super(context);
        onSetTypeface();
    }

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        onSetTypeface();
    }

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr value of default style attributes
     */
    public BSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onSetTypeface();
    }

    /**
     * Method which provide the setting of the interface for {@link BSButton}
     */
    private void onSetTypeface() {
        if (this.isInEditMode()) {
            return;
        }
        this.fieldObject = new BSFieldObject(this);
        Typeface typeface = this.getTypeface();
        Typeface font = getFont();
        if (typeface != null) {
            if (typeface.getStyle() == Typeface.NORMAL) {
                font = getFont();
            } else if (typeface.getStyle() == Typeface.BOLD) {
                font = getFontBold();
            } else if (typeface.getStyle() == Typeface.ITALIC) {
                font = getFontItalic();
            } else if (typeface.getStyle() == Typeface.BOLD_ITALIC) {
                font = getFontBoldItalic();
            }
        }
        this.setTypeface(font);
    }

    /**
     * Method which provide the formatting text from the HTML value
     *
     * @param formatedText current text
     */
    public void setTextFromHtml(String formatedText) {
        setText(Html.fromHtml(formatedText));
    }

    /**
     * Method which provide the formatting text from the HTML value
     *
     * @param stringId current text ID
     */
    public void setTextFromHtml(int stringId) {
        String formatedText = getContext().getString(stringId);
        setText(Html.fromHtml(formatedText));
    }

    /**
     * Method which provide the getting text from the current component
     *
     * @return current String value
     */
    public String getStringValue() {
        return this.getText().toString().trim();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFont() {
        return BSTypefaceManager.getDefault();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontBold() {
        return BSTypefaceManager.getDefaultBold();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontItalic() {
        return BSTypefaceManager.getDefaultItalic();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontBoldItalic() {
        return BSTypefaceManager.getDefaultBoldItalic();
    }

    /**
     * Method which provide the adding of the validator rule models
     *
     * @param models array of the {@link BSValidationRuleModel}
     */
    public synchronized void addValidators(@Nullable BSValidationRuleModel... models) {
        if (this.fieldObject == null) return;
        this.fieldObject.add(models);
    }

    /**
     * Method which provide the validate field
     *
     * @return validation result
     */
    public boolean validate() {
        if (this.fieldObject == null) return false;
        return this.fieldObject.validate();
    }
}
