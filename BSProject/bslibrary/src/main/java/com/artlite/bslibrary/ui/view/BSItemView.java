package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Class which provide the item view
 */
public abstract class BSItemView extends BSView {

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSItemView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the get of the object
     *
     * @return instance of the {@link Object}
     */
    @Nullable
    protected abstract Object getObject();

    /**
     * Method which provide the getting {@link View} of the drag handle
     * (View for the drag and drop view)
     *
     * @return instance of the {@link View}
     */
    @Nullable
    public abstract View getHandleView();

    /**
     * Method which provide to define if the {@link View} can be draggable
     *
     * @param index {@link Integer} value of the index
     * @return {@link Boolean} value if it draggable
     */
    protected abstract boolean draggable(int index);
}
