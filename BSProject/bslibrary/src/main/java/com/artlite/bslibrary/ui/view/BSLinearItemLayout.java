package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.managers.BSThreadManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which provide the add item to the linear layout as list
 *
 * @see BSItemView
 */
public final class BSLinearItemLayout extends BSDragLinearLayout {

    /**
     * {@link Boolean} value if the layout is draggable
     */
    private boolean draggable = false;

    /**
     * {@link String} constants of the tag
     */
    private static final String TAG = BSLinearItemLayout.class.getSimpleName();

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context instance of {@link Context}
     */
    public BSLinearItemLayout(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSLinearItemLayout(Context context,
                              @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link View} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSLinearItemLayout(Context context,
                              @Nullable AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    public BSLinearItemLayout(Context context,
                              AttributeSet attrs,
                              int defStyleAttr,
                              int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Method which provide the set of the all view draggable
     */
    protected void setViewDraggable() {
        BSThreadManager.main(1, new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child instanceof BSItemView) {
                        final BSItemView itemView = (BSItemView) child;
                        if (itemView.draggable(i)) {
                            setViewDraggable(child, child);
                        }
                    } else {
                        // the child will act as its own drag handle
                        setViewDraggable(child, child);
                    }
                }
            }
        });
    }

    /**
     * Method which provide the add of the view
     *
     * @param view instance of the {@link BSItemView}
     * @param <T>  type of the {@link BSItemView}
     */
    @MainThread
    public final <T extends BSItemView> void add(@Nullable final T view) {
        int orientation = getOrientation();
        if (view != null) {
            if (orientation == LinearLayout.HORIZONTAL) {
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.MATCH_PARENT));
            } else {
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            if (draggable) {
                this.addView(view);
                this.setViewDraggable();
            } else {
                this.addView(view);
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
        int orientation = getOrientation();
        if (view != null) {
            if (orientation == LinearLayout.HORIZONTAL) {
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.MATCH_PARENT));
            } else {
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            if (draggable) {
                this.addView(view, index);
                this.setViewDraggable();
            } else {
                this.addView(view, index);
            }
        }
    }

    /**
     * Method which provide the getting of the list of the inner objects
     *
     * @param <T> instance of the type of the {@link Object}
     * @return {@link List} of the objects
     */
    @NonNull
    public final <T extends Object> List<T> getObjects() {
        final List<T> objects = new ArrayList<>();
        for (int i = 0; i < this.getChildCount(); i++) {
            final View view = this.getChildAt(i);
            if (view instanceof BSItemView) {
                try {
                    final BSItemView itemView = (BSItemView) view;
                    objects.add((T) itemView.getObject());
                } catch (Exception ex) {
                    Log.e(TAG, String.format("Can\'t convert object to required type. Error: %s.",
                            ex.toString()));
                }
            }
        }
        return objects;
    }


    /**
     * Method which provide the configure view as draggable
     *
     * @param draggable {@link Boolean} value if it draggable
     * @param callback  instance of the {@link BSDragLinearLayout.OnViewSwapListener}
     */
    public final void configure(boolean draggable,
                                @Nullable BSLinearItemLayout.OnViewSwapListener callback) {
        if (draggable) {
            this.draggable = draggable;
            this.setViewDraggable();
            this.setOnViewSwapListener(callback);
        }
    }
}
