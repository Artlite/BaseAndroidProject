package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.ime.BSImeHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

/**
 * Created by dlernatovich on 22.06.2017.
 */

public final class BSSearchView extends BSView {

    /**
     * Instance of {@link LinearLayout}
     */
    private LinearLayout layoutContent;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView imageSearch;

    /**
     * Instance of the {@link EditText}
     */
    private EditText editSearch;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView imageClose;

    /**
     * {@link Integer} value of the close button click count
     */
    private int closeClickCount;

    /**
     * Instance of the {@link OnSearchCallback}
     */
    private OnSearchCallback callback;

    /**
     * {@link Boolean} value if the {@link BSSearchView} in search state
     */
    private boolean isSearching = false;

    //==============================================================================================
    //                                      STYLE
    //==============================================================================================

    /**
     * Instance of the {@link ColorStateList}
     */
    private ColorStateList generalTint;

    /**
     * Instance of {@link ColorStateList}
     */
    private ColorStateList searchIconTint;

    /**
     * Instance of {@link ColorStateList}
     */
    private ColorStateList textTint;

    /**
     * Instance of {@link ColorStateList}
     */
    private ColorStateList textHintTint;

    /**
     * Instance of {@link ColorStateList}
     */
    private ColorStateList closeIconTint;

    /**
     * Instance of the {@link String}
     */
    private String hint;

    /**
     * Instance of the {@link Drawable}
     */
    private Drawable background;

    /**
     * {@link Integer} value of the text size
     */
    private int textSize;

    /**
     * {@link Integer} value of the text style
     */
    private int textStyle;

    /**
     * {@link Integer} value of the text gravity
     */
    private int textGravity;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSSearchView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide interface linking
     */
    @Override
    protected void onLinkInterface() {
        layoutContent = (LinearLayout) findViewById(R.id.content);
        imageSearch = (ImageView) findViewById(R.id.image_search);
        editSearch = (EditText) findViewById(R.id.edit_search);
        imageClose = (ImageView) findViewById(R.id.image_close);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.bs_layout_search_view;
    }

    /**
     * Method which provide the init attributes
     *
     * @param attributes instance of the {@link AttributeSet}
     */
    @Override
    protected void onInitAttributes(@NonNull AttributeSet attributes) {
        TypedArray attr = getContext().getTheme().obtainStyledAttributes(attributes,
                R.styleable.BSSearchView, 0, 0);
        this.generalTint = attr.getColorStateList(R.styleable.BSSearchView_generalTint);
        this.searchIconTint = attr.getColorStateList(R.styleable.BSSearchView_searchIconTint);
        this.textTint = attr.getColorStateList(R.styleable.BSSearchView_textTint);
        this.textHintTint = attr.getColorStateList(R.styleable.BSSearchView_textHintTint);
        this.closeIconTint = attr.getColorStateList(R.styleable.BSSearchView_closeIconTint);
        this.hint = attr.getString(R.styleable.BSSearchView_searchHintText);
        this.background = attr.getDrawable(R.styleable.BSSearchView_searchBackground);
        this.textSize = attr.getDimensionPixelSize(R.styleable.BSSearchView_searchTextSize,
                getResources().getDimensionPixelSize(R.dimen.text_16));
        this.textStyle = attr.getInteger(R.styleable.BSSearchView_searchTextStyle,
                1);
        this.textGravity = attr.getInteger(R.styleable.BSSearchView_searchTextGravity,
                1);

        //Set typeface
        switch (textStyle) {
            case 0:
                editSearch.setTypeface(null, Typeface.BOLD);
                break;
            case 1:
                editSearch.setTypeface(null, Typeface.NORMAL);
                break;
            case 2:
                editSearch.setTypeface(null, Typeface.ITALIC);
                break;
            default:
                editSearch.setTypeface(null, Typeface.NORMAL);
                break;
        }

        //Set gravity
        switch (textGravity) {
            case 0:
                editSearch.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 1:
                editSearch.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
            case 2:
                editSearch.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                break;
            default:
                editSearch.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
        }

        editSearch.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        if (background != null) {
            layoutContent.setBackground(background);
        }

        //Set general tints
        if (generalTint != null) {
            imageClose.setColorFilter(generalTint.getDefaultColor());
            imageSearch.setColorFilter(generalTint.getDefaultColor());
            editSearch.setTextColor(generalTint);
            editSearch.setHintTextColor(textHintTint);
        }

        if (searchIconTint != null) {
            imageSearch.setColorFilter(searchIconTint.getDefaultColor());
        }

        if (textTint != null) {
            editSearch.setTextColor(textTint);
        }

        if (closeIconTint != null) {
            imageClose.setColorFilter(closeIconTint.getDefaultColor());
        }

        if (BSValidationHelper.validateEmpty(hint)) {
            editSearch.setHint(hint);
        }

        if (textHintTint != null) {
            editSearch.setHintTextColor(textHintTint);
        }

        attr.recycle();
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        imageClose.setVisibility(INVISIBLE);
        editSearch.setClickable(false);
        editSearch.setOnEditorActionListener(actionListener);
        setOnClickListeners(R.id.image_search, R.id.image_close);
    }

    /**
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.image_search) {
            startSearching();
        } else if (id == R.id.image_close) {
            cancelSearch();
        }
    }

    /**
     * Method which provide the setting of the {@link OnSearchCallback}
     *
     * @param callback instance of the {@link OnSearchCallback}
     */
    public void setSearchCallback(OnSearchCallback callback) {
        this.callback = callback;
    }

    /**
     * Method which provide the start searching
     */
    private void startSearching() {
        if (isSearching) {
            return;
        }
        BSImeHelper.showKeyboard(getContext(), this);
        imageClose.setVisibility(VISIBLE);
        editSearch.setText("");
        editSearch.setClickable(true);
        editSearch.setEnabled(true);
        editSearch.requestFocus();
        isSearching = true;
    }

    /**
     * Method which provide the stop searching
     */
    private void stopSearching() {
        BSImeHelper.hideKeyboard(getContext(), this);
        imageClose.setVisibility(INVISIBLE);
        editSearch.setText("");
        editSearch.setClickable(false);
        editSearch.setEnabled(false);
        layoutContent.requestFocus();
        isSearching = false;
    }

    /**
     * Method which provide the text searching
     *
     * @param text {@link String} value of the searched text
     */
    private void onSearch(String text) {
        BSImeHelper.hideKeyboard(getContext(), this);
        if (callback != null) {
            callback.onSearch(text);
        }
    }

    /**
     * Method which provide the cancel searching
     */
    private void cancelSearch() {
        if (callback != null) {
            callback.onCancel();
        }
        stopSearching();
    }

    //==============================================================================================
    //                                      LISTENERS
    //==============================================================================================

    /**
     * Instance of the {@link TextView.OnEditorActionListener}
     */
    private final TextView.OnEditorActionListener actionListener =
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        final String searchText = (v != null) ? v.getText().toString() : "";
                        onSearch(searchText);
                        return true;
                    }
                    return false;
                }
            };

    //==============================================================================================
    //                                      CLASSES
    //==============================================================================================

    /**
     * Callback which provide the search actions
     */
    public interface OnSearchCallback {

        /**
         * Method which provide the text searching
         *
         * @param text {@link String} value of the searched text
         */
        void onSearch(String text);

        /**
         * Method which provide the cancel searching
         */
        void onCancel();

    }

    /**
     HOW TO CUSTOMIZE:
     <com.artlite.bslibrary.ui.view.BSSearchView
     android:id="@+id/search_view"
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     app:generalTint="@android:color/holo_red_dark"
     app:closeIconTint="@android:color/holo_blue_dark"
     app:textTint="@android:color/holo_purple"
     app:searchHintText="jkshksdgfdhsgfsdhkfgs"
     app:searchBackground="@drawable/background_round"
     app:searchTextSize="@dimen/text_20"
     app:searchIconTint="@android:color/holo_green_dark"/>
     */
}
