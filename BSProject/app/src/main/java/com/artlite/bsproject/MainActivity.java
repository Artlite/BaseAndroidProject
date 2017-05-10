package com.artlite.bsproject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.helpers.dialog.BSDialogHelper;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.fonted.BSEditText;

public class MainActivity extends BSActivity {

    @FindViewBy(id = R.id.view_for_pop_up)
    private View forPopup;

    @FindViewBy(id = R.id.edit_popup)
    private BSEditText editText;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * Method which provide the action when Activity is created
     */
    @Override
    protected void onCreateActivity(Bundle bundle) {
        setOnClickListeners(R.id.button1, R.id.button2);

    }

    /**
     * Method which provide the defining if need to override of the transition animation
     *
     * @return defining results
     */
    @Override
    protected boolean isOverrideTransitionAnimation() {
        return true;
    }

    /**
     * Overriden method for the OnClickListener
     *
     * @param v current view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                startActivity(DetailsActivity.class);
                break;
            }
            case R.id.button2: {
                Dialog dialog = BSDialogHelper.create(this, "Resident evil", "Text", R.mipmap.ic_recycle);
                dialog.show();
                break;
            }
            default:
                break;
        }
    }
}
