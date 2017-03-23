package com.artlite.bslibrary.ui.fonted;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.EditText;

import com.artlite.bslibrary.annotations.Warning;
import com.artlite.bslibrary.managers.BSTypefaceManager;

/**
 * Class which provide the customize font for {@link EditText}
 */

public class BSEditText extends AppCompatEditText {
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
        return BSTypefaceManager.getBariol();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontBold() {
        return BSTypefaceManager.getBariolBold();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontItalic() {
        return BSTypefaceManager.getBariolItalic();
    }

    /**
     * Method which provide the getting of the default {@link Typeface}
     *
     * @return instance of {@link Typeface}
     */
    @Warning(massage = "This method should be overriden if you want to change typeface")
    protected Typeface getFontBoldItalic() {
        return BSTypefaceManager.getBariolBoldItalic();
    }
}
