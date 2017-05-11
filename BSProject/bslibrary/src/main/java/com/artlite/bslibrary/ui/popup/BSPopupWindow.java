package com.artlite.bslibrary.ui.popup;

import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.artlite.bslibrary.ui.view.BSView;

/**
 * Class which provide the Pop Up functional
 */

public class BSPopupWindow extends PopupWindow implements BSView.OnPopupCallback {

    /**
     * Constructor which provide create the {@link BSPopupWindow} from {@link BSView}
     *
     * @param contentView instance of {@link BSView}
     */
    public BSPopupWindow(BSView contentView) {
        super(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        //Initialize dismiss listener
        contentView.setPopupCallback(this);
        //Hack for onClose the pop up view when it lost the focus
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
    }

    /**
     * Method which provide the closing functional
     */
    @Override
    public void close() {
        this.dismiss();
    }

}
