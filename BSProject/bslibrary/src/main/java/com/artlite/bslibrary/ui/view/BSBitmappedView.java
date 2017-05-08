package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSScreenManager;

/**
 * Class which provide the functionality for the create view with {@link Bitmap} representation
 */

public abstract class BSBitmappedView<T extends Object> extends BSView {

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSBitmappedView(Context context) {
        super(context);
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        onLinkInterface();
    }

    //==============================================================================================
    //                                          APPLY
    //==============================================================================================

    /**
     * Method which provide the applying of the content inside the view
     *
     * @warning Should be overriding in the
     */
    public Bitmap onApply(final T... objects) {
        return create(this);
    }

    //==============================================================================================
    //                                          MEASURE
    //==============================================================================================

    /**
     * Method which provide the doing of the measure for the {@link BSBitmappedView}
     *
     * @param width  {@link Integer} value of the width spec
     * @param height {@link Integer} value of the height spec
     */
    @Override
    protected void onMeasure(int width, int height) {
        final int screenWidth = BSScreenManager.getWidth();
        final int displayMeasure = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.AT_MOST);
        if (width > displayMeasure) {
            width = displayMeasure;
        }
        super.onMeasure(width, height);
    }

    //==============================================================================================
    //                                          STATIC
    //==============================================================================================

    /**
     * Method which provide the create {@link Bitmap} from instance of the {@link View}
     *
     * @param view instance of the {@link View}
     * @return instance of the {@link Bitmap}
     */
    @Nullable
    private static Bitmap create(@Nullable final View view) {
        if (BSValidationHelper.isEmpty(view)) {
            return null;
        }
        view.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }

    //==============================================================================================
    //                                          ABSTRACT
    //==============================================================================================

    /**
     * Method which provide the interface linking
     */
    protected abstract void onLinkInterface();

}
