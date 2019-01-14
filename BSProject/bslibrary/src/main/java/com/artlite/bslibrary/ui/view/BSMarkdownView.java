package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.artlite.bslibrary.libs.markdown.MarkdownView;
import com.artlite.bslibrary.libs.markdown.css.InternalStyleSheet;
import com.artlite.bslibrary.libs.markdown.css.styles.Github;

/**
 * Class which provide the display of the markdown
 */
public final class BSMarkdownView extends MarkdownView {

    /**
     * {@link String} constant of the scroll up rule
     */
    private static final String K_SCROLL_UP_RULE = ".scrollup";

    /**
     * Constructor which provide the create {@link BSMarkdownView} from
     *
     * @param context instance of {@link Context}
     */
    public BSMarkdownView(Context context) {
        super(context);
        this.onCreateView();
    }

    /**
     * Constructor which provide the create {@link BSMarkdownView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSMarkdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.onCreateView();
    }

    /**
     * Method which provide the creating view
     */
    private void onCreateView() {
        InternalStyleSheet css = new Github();
        css.removeRule(K_SCROLL_UP_RULE);
        this.addStyleSheet(css);
        this.setEscapeHtml(false);
    }

}
