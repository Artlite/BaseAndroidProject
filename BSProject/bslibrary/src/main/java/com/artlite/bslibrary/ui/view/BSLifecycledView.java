package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public abstract class BSLifecycledView extends BSView implements LifecycleOwner {

    /**
     * Lifecycle registry.
     */
    private LifecycleRegistry registry = new LifecycleRegistry(this);

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSLifecycledView(Context context) {
        this(context, null, 0);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSLifecycledView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSLifecycledView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.registry.setCurrentState(Lifecycle.State.CREATED);
    }

    /**
     * On attach to window functional.
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.registry.setCurrentState(Lifecycle.State.STARTED);
    }

    /**
     * On detach from window functional.
     */
    @Override
    protected void onDetachedFromWindow() {
        this.registry.setCurrentState(Lifecycle.State.DESTROYED);
        super.onDetachedFromWindow();
    }
}
