package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.artlite.bslibrary.R;

import java.util.List;

/**
 * Class which provide the add item to the linear layout as list
 *
 * @see BSItemView
 */
public final class BSGridLayout extends GridLayout {

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSGridLayout.class.getSimpleName();

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context instance of {@link Context}
     */
    public BSGridLayout(Context context) {
        super(context);
        this.onCreateView(context);
    }

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSGridLayout(Context context,
                        @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.onCreateView(context);
    }

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSGridLayout(Context context,
                        @Nullable AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onCreateView(context);
    }

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     * @param defStyleRes  def style
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BSGridLayout(Context context,
                        AttributeSet attrs,
                        int defStyleAttr,
                        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.onCreateView(context);
    }

    /**
     * Method which provide the create view
     *
     * @param context instance of {@link Context}
     */
    protected void onCreateView(@NonNull Context context) {
    }

    /**
     * Method which provide the add of the view
     *
     * @param view instance of the {@link BSItemView}
     * @param <T>  type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends View> void add(@Nullable final T view) {
        if (view != null) {
            int columnCount = this.getColumnCount();
            int viewsCount = this.getChildCount();
            int column = (viewsCount % columnCount);
            int row = viewsCount / columnCount;
            final GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();
            params.height = LayoutParams.WRAP_CONTENT;
            params.width = (this.getWidth() / columnCount) - getResources()
                    .getDimensionPixelSize(R.dimen.dimen_8);
            params.setGravity(Gravity.CENTER);
            params.columnSpec = GridLayout.spec(column);
            params.rowSpec = GridLayout.spec(row);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dimen_4);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dimen_4);
            view.setLayoutParams(params);
            this.addView(view);
        }
    }

    /**
     * Method which provide the add of the view
     *
     * @param views instance of the {@link BSItemView} of array
     * @param <T>   type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends View> void add(@Nullable final T... views) {
        if (views != null) {
            for (T view : views) {
                this.add(view);
            }
        }
    }

    /**
     * Method which provide the add of the view
     *
     * @param views instance of the {@link BSItemView} of {@link List}
     * @param <T>   type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends BSItemView> void add(@Nullable final List<T> views) {
        if (views != null) {
            for (T view : views) {
                this.add(view);
            }
        }
    }

    /**
     * Method which provide the add of the view
     *
     * @param view  instance of the {@link BSItemView}
     * @param index {@link Integer} value of the index
     * @param <T>   type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends BSItemView> void add(@Nullable final T view,
                                                 int index) {
        if (view != null) {
            int columnCount = this.getColumnCount();
            int viewsCount = this.getChildCount();
            int column = (viewsCount % columnCount);
            int row = viewsCount / columnCount;
            final GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();
            params.height = LayoutParams.WRAP_CONTENT;
            params.width = (this.getWidth() / columnCount) - getResources()
                    .getDimensionPixelSize(R.dimen.dimen_8);
            params.setGravity(Gravity.CENTER);
            params.columnSpec = GridLayout.spec(column);
            params.rowSpec = GridLayout.spec(row);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dimen_4);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dimen_4);
            view.setLayoutParams(params);
            this.addView(view, index);
        }
    }

    /**
     * Method which provide the add of the view
     *
     * @param view instance of the {@link BSItemView}
     * @param <T>  type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends View> void delete(@Nullable final T view) {
        if (view != null) {
            this.removeView(view);
        }
    }

    /**
     * Method which provide the configure view as draggable
     *
     * @param rows    {@link Integer} value of the rows
     * @param columns {@link Integer} value of the columns
     */
    @NonNull
    public final BSGridLayout configure(int rows,
                                        int columns) {
        this.setRowCount(rows);
        this.setColumnCount(columns);
        return this;
    }
}
