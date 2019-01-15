package com.artlite.bsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSProgressDialogManager;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSMarkdownView;
import com.artlite.bslibrary.ui.view.BSPDFView;

public class MarkdownActivity extends BSActivity {

    /**
     * Instance of the {@link BSPDFView}
     */
    @FindViewBy(id = R.id.view_markdown)
    private BSMarkdownView view;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_markdown;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
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
        BSProgressDialogManager.show(false);
        BSThreadManager.main(1, new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                view.loadMarkdownFromAsset("Goaline project.md");
                BSProgressDialogManager.hide();
            }
        });
    }

}
