package com.artlite.bsproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSRandomManager;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.bslibrary.ui.view.BSLockableView;
import com.artlite.bslibrary.ui.view.BSView;

/**
 * Created by dlernatovich on 5/10/2017.
 */

public final class UserView extends BSLockableView {

    @FindViewBy(id = R.id.label_description)
    private BSTextView labelDescription;

    @FindViewBy(id = R.id.label_header)
    private BSTextView labelHeader;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public UserView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public UserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public UserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_user_view;
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
        this.lockView();
        labelHeader.setText(BSRandomManager.generateSentence(20));
        labelDescription.setText(BSRandomManager.generateSentence(20));
        setOnClickListeners(R.id.button_close, R.id.button_cancel, R.id.button_event);
        this.unlockView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_close: {
                dismissPopup();
                dismissDialog();
                break;
            }
            case R.id.button_cancel: {
                dismissPopup();
                cancelDialog();
                break;
            }
            case R.id.button_event: {
                sendEvent(new Event("ResidentEvil:UserView"));
                break;
            }
            default:
                break;
        }
    }
}
