package com.artlite.bsproject;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSGridLayout;
import com.artlite.bslibrary.ui.view.BSSearchView;

public class DetailsActivity extends BSActivity {

    /**
     * Instance of the {@link BSSearchView}
     */
    @FindViewBy(id = R.id.search_view)
    private BSSearchView searchView;

    @FindViewBy(id = R.id.linear_layout)
    private BSGridLayout bsLinearItemLayout;

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

    }

    /**
     * Method which provide the action when Activity is created (post creation)
     * Use it if you create any callback inside the activity like
     * <b>final |CallbackType| callback = new |CallbackType|</b>
     *
     * @param bundle
     */
    @Override
    protected void onActivityPostCreation(@Nullable Bundle bundle) {
        for (int i = 0; i < 100; i++) {
            this.bsLinearItemLayout.add(new DetailsItemView(this)
                    .setText("Text " + i));
        }
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
