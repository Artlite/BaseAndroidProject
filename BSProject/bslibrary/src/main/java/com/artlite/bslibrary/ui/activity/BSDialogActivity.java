package com.artlite.bslibrary.ui.activity;

import android.content.Intent;

public abstract class BSDialogActivity extends BSLockableActivity {

    //==============================================================================================
    //                                     ANIMATION
    //==============================================================================================

    /**
     * Method which provide the finishing activity
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

}
