package com.artlite.bsproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSDraggableLinearLayout;
import com.artlite.bslibrary.ui.view.BSSearchView;

public class DetailsActivity extends BSActivity {

    /**
     * Instance of the {@link BSSearchView}
     */
    @FindViewBy(id = R.id.search_view)
    private BSSearchView searchView;

    @FindViewBy(id = R.id.linear_layout)
    private BSDraggableLinearLayout bsLinearItemLayout;

    @FindViewBy(id = R.id.scroll_view)
    private ScrollView scrollView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    /**
     * Method which provide the action when Activity is created
     */
    @Override
    protected void onCreateActivity(Bundle bundle) {
        this.bsLinearItemLayout.configure(true, this.scrollView, null);
        searchView.setSearchCallback(new BSSearchView.OnSearchCallback() {
            @Override
            public void onSearch(String text) {
                Toast.makeText(getBaseContext(), "On search" + text, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(), "On cancel", Toast.LENGTH_LONG).show();
            }
        });
        for (int i = 0; i < 100; i++) {
            this.bsLinearItemLayout.add(new DetailsItemView(this));
        }
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
     * Method which provide the checking if need back button into {@link ActionBar}
     *
     * @return checking if need back button into {@link ActionBar}
     */
    @Override
    protected boolean isNeedBackButton() {
        return true;
    }
}
