package com.artlite.bsproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.bslibrary.ui.view.BSItemView;
import com.artlite.bslibrary.ui.view.BSView;

public class DetailsItemView extends BSItemView {

    /**
     * Instance of the {@link ImageView}
     */
    @FindViewBy(id = R.id.drag_menu)
    private ImageView dragImage;

    /**
     * Instance of the {@link BSTextView}
     */
    @FindViewBy(id = R.id.label_text)
    private BSTextView labelText;

    /**
     * {@link String} value of the text
     */
    private String text;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public DetailsItemView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public DetailsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public DetailsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the get of the object
     *
     * @return instance of the {@link Object}
     */
    @Nullable
    @Override
    protected Object getObject() {
        return null;
    }

    /**
     * Method which provide the getting {@link View} of the drag handle
     * (View for the drag and drop view)
     *
     * @return instance of the {@link View}
     */
    @Nullable
    @Override
    public View getHandleView() {
        return this.dragImage;
    }

    /**
     * Method which provide to define if the {@link View} can be draggable
     *
     * @param index {@link Integer} value of the index
     * @return {@link Boolean} value if it draggable
     */
    @Override
    protected boolean draggable(int index) {
        return true;
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_details_item;
    }

    /**
     * Method which provide interface linking
     */
    @Override
    protected void onLinkInterface() {

    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        this.setText(this.text);
    }


    /**
     * Method which provide the text setting
     *
     * @param text {@link String} value of the text
     * @return instance of the {@link DetailsItemView}
     */
    public DetailsItemView setText(@Nullable String text) {
        this.text = text;
        this.labelText.setText(text);
        return this;
    }
}
