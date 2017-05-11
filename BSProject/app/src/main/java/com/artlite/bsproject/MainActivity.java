package com.artlite.bsproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.fonted.BSEditText;
import com.artlite.bslibrary.ui.view.BSView;

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
                new UserView(this).showAsDialog(false, new BSView.OnDialogCallback() {
                    @Override
                    public void onShow(@NonNull Context context, @NonNull BSView view) {
                        Toast.makeText(context, "onShow", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(@NonNull Context context, @NonNull BSView view) {
                        Toast.makeText(context, "onCancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onClose(@NonNull Context context, @NonNull BSView view) {
                        Toast.makeText(context, "onClose", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onEventReceived(@NonNull Context context,
                                                @NonNull BSView view,
                                                @NonNull BSView.Event event) {
                        Toast.makeText(context, "onEventReceived", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            default:
                break;
        }
    }
}
