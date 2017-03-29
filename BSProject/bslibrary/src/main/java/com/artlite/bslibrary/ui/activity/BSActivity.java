package com.artlite.bslibrary.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSThreadManager;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public abstract class BSActivity extends AppCompatActivity implements View.OnClickListener {

    //==============================================================================================
    //                                      CONSTANTS
    //==============================================================================================

    protected static final int ON_BASE_ACTIVITY_RESULTS = 0x1;
    protected static final String ON_RESULT_EXTRA_KEY = "ON_RESULT_EXTRA_KEY";
    protected static final int K_NONE_MENU = Integer.MIN_VALUE;

    //==============================================================================================
    //                                      FIELDS
    //==============================================================================================
    protected Boolean isLaunchActivity = null;

    //==============================================================================================
    //                                      CREATE
    //==============================================================================================

    /**
     * Perform initialization of all fragments and loaders
     *
     * @param bundle instance of {@link Bundle}
     */
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        onInitBackButton();
        onCreateActivity((bundle == null) ? getIntent().getExtras() : bundle);
    }

    //==============================================================================================
    //                                      MENU
    //==============================================================================================

    /**
     * Method which provide the {@link Menu} creating
     *
     * @param menu instance of {@link Menu}
     * @return creating result
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = getMenuId();
        if (menuId == K_NONE_MENU) {
            return false;
        }
        getMenuInflater().inflate(menuId, menu);
        return true;
    }

    /**
     * Method which provide the getting of the menu ID
     * (Can be as K_NONE_MENU)
     *
     * @return current menu ID
     */
    protected int getMenuId() {
        return K_NONE_MENU;
    }

    /**
     * Method which provide the action when user check option {@link Menu}
     *
     * @param item instance of {@link MenuItem}
     * @return checking results
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //==============================================================================================
    //                                         ON CLICK
    //==============================================================================================

    /**
     * Overriden method for the OnClickListener
     *
     * @param v current view
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param views current list of views
     */
    protected void setOnClickListeners(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param ids current list of views
     */
    protected void setOnClickListeners(int... ids) {
        final String methodName = "void setOnClickListeners(int... ids)";
        for (int id : ids) {
            try {
                findViewById(id).setOnClickListener(this);
            } catch (Exception ex) {
                BSLogHelper.log(this, methodName, ex, null);
            }
        }
    }

    //==============================================================================================
    //                                       FRAGMENTS
    //==============================================================================================

    /**
     * Method which provide the replace of the fragment inside the activity
     *
     * @param fragment     current fragment
     * @param container_id current container id
     */
    public void replace(Fragment fragment, int container_id) {
        getFragmentTransaction().replace(container_id, fragment,
                getFragmentTag()).commit();
    }


    /**
     * Method which provide the getting of the FragmentTransaction
     *
     * @return current FragmentTransaction
     */
    private FragmentTransaction getFragmentTransaction() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        return ft;
    }

    /**
     * Method which provide the getting of the fragment tag
     *
     * @return current fragment Tag
     */
    private String getFragmentTag() {
        return getClass().getName();
    }

    //==============================================================================================
    //                                      NAVIGATION
    //==============================================================================================

    /**
     * Method which provide starting the Activity
     *
     * @param activtyClass activity which should be starting
     * @param callbacks    instances of {@link OnStartActivityCallback}
     */
    protected void startActivity(Class activtyClass,
                                 @Nullable final OnStartActivityCallback... callbacks) {
        startActivity(activtyClass, false, callbacks);
    }

    /**
     * Method which provide starting the Activity
     *
     * @param activtyClass activity which should be starting
     * @param isNeedClear  is need clear {@link Activity} stack
     * @param callbacks    instances of {@link OnStartActivityCallback}
     */
    protected void startActivity(Class activtyClass,
                                 boolean isNeedClear,
                                 @Nullable final OnStartActivityCallback... callbacks) {
        final Intent intent = new Intent(this, activtyClass);
        if (isNeedClear) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if ((callbacks != null) && (callbacks.length > 0)) {
            for (final OnStartActivityCallback callback : callbacks) {
                if (callback != null) {
                    callback.onPreExecute(intent);
                }
            }
        }
        startActivity(intent);
    }

    //==============================================================================================
    //                                    ACTIVITY FOR RESULTS
    //==============================================================================================

    /**
     * Method which provide the getting of the extras from {@link Intent}
     *
     * @param intent instance of {@link Intent}
     * @param key    instance of {@link String} key
     * @param <T>    class type
     * @return instance of {@link Serializable}
     */
    @Nullable
    protected <T extends Parcelable> T getExtras(@Nullable final Intent intent,
                                                 @Nullable final String key) {
        if (!BSValidationHelper.isEmpty(intent, key)) {
            return getExtras(intent.getExtras(), key);
        }
        return null;
    }

    /**
     * Method which provide the getting of the extras from {@link Intent}
     *
     * @param bundle instance of {@link Bundle}
     * @param key    instance of {@link String} key
     * @param <T>    class type
     * @return instance of {@link Serializable}
     */
    @Nullable
    protected <T extends Parcelable> T getExtras(@Nullable final Bundle bundle,
                                                 @Nullable final String key) {
        final String methodName = "T getExtras(intent, key)";
        try {
            return bundle.getParcelable(key);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
        }
        return null;
    }


    /**
     * Method which provide starting the Activity for results
     *
     * @param activtyClass activity which should be starting
     * @param callbacks    instances of {@link OnStartActivityCallback}
     */
    protected void startActivityForResults(Class activtyClass,
                                           @Nullable final OnStartActivityCallback... callbacks) {
        final Intent intent = new Intent(this, activtyClass);
        if ((callbacks != null) && (callbacks.length > 0)) {
            for (final OnStartActivityCallback callback : callbacks) {
                if (callback != null) {
                    callback.onPreExecute(intent);
                }
            }
        }
        startActivityForResult(intent, ON_BASE_ACTIVITY_RESULTS);
    }

    /**
     * Dispatch incoming result to the correct fragment
     *
     * @param requestCode request code
     * @param resultCode  result code
     * @param data        intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            // The user picked a contact.
            // The Intent's data Uri identifies which contact was selected.
            onActivityResult(requestCode, data);
        }

    }

    /**
     * Method which provide the action when activity return result
     *
     * @param data current intent
     */
    protected void onActivityResult(int requestCode, Intent data) {

    }

    /**
     * Method which provide the sending of the Activity results
     *
     * @param value current extra value
     */
    protected <T extends Parcelable> void sendActivityResult(T value) {
        final Intent intent = new Intent();
        if (value != null) {
            intent.putExtra(ON_RESULT_EXTRA_KEY, value);
            setResult(RESULT_OK, intent);
        }
        onBackPressed();
    }

    //==============================================================================================
    //                                      PERFORMING
    //==============================================================================================

    /**
     * Method which provide the doing action on UI thread after the delaying time
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    protected void main(@Nullable final BSThreadManager.OnThreadCallback callback) {
        main(0, callback);
    }

    /**
     * Method which provide the doing action on UI thread after the delaying time
     *
     * @param delayTime delaying time (in seconds)
     * @param callback  instance of {@link BSThreadManager.OnThreadCallback}
     */
    protected void main(int delayTime,
                        @Nullable final BSThreadManager.OnThreadCallback callback) {
        BSThreadManager.main(delayTime, callback);
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    public static void background(@Nullable final BSThreadManager.OnThreadCallback callback) {
        BSThreadManager.background(callback);
    }

    /**
     * Method which provide the executing action on background thread
     *
     * @param callback instance of {@link BSThreadManager.OnThreadCallback}
     */
    public static void execute(@Nullable final BSThreadManager.OnExecutionCallback callback) {
        BSThreadManager.execute(callback);
    }

    //==============================================================================================
    //                                     ANIMATION
    //==============================================================================================

    /**
     * Method which provide the finishing activity
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    /**
     * Method which provide the starting activity
     *
     * @param intent instance of {@link Intent}
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        if (isOverrideTransitionAnimation() == true) {
            overridePendingTransition(getStartEnterAnim(), getStartEndAnim());
        }
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        if ((isOverrideTransitionAnimation() == true) && (isLaunchActivity() == false)) {
            overridePendingTransition(getFinishStartAnim(), getFinishEndAnim());
        }
    }

    /**
     * Method which provide the getting of the start enter animation
     *
     * @return id for start enter animation
     */
    @AnimRes
    protected int getStartEnterAnim() {
        return R.anim.slide_from_right;
    }

    /**
     * Method which provide the getting of the start end animation
     *
     * @return id for start end animation
     */
    @AnimRes
    protected int getStartEndAnim() {
        return R.anim.slide_to_left;
    }

    /**
     * Method which provide the getting of the finish start animation
     *
     * @return id for start end animation
     */
    @AnimRes
    protected int getFinishStartAnim() {
        return R.anim.slide_from_left;
    }

    /**
     * Method which provide the getting of the finish start animation
     *
     * @return id for start end animation
     */
    @AnimRes
    protected int getFinishEndAnim() {
        return R.anim.slide_to_right;
    }

    /**
     * Method which provide the defining if need to override of the transition animation
     *
     * @return defining results
     */
    protected boolean isOverrideTransitionAnimation() {
        return false;
    }

    /**
     * Method which provide the checking if current {@link Activity} is launch
     *
     * @return checking if current {@link Activity} is launch
     */
    private boolean isLaunchActivity() {
        if (isLaunchActivity == null) {
            Intent intent = getIntent();
            String action = intent.getAction();
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                isLaunchActivity = categories.contains("android.intent.category.LAUNCHER");
            } else {
                isLaunchActivity = false;
            }
        }
        return isLaunchActivity;
    }

    //==============================================================================================
    //                                    BACK PRESSED
    //==============================================================================================

    /**
     * Method which provide the checking if need back button into {@link ActionBar}
     *
     * @return checking if need back button into {@link ActionBar}
     */
    protected boolean isNeedBackButton() {
        return false;
    }

    /**
     * Method which provide the initializing of the back button in {@link ActionBar}
     */
    private void onInitBackButton() {
        if ((isNeedBackButton() == true) && (isLaunchActivity() == false)) {
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            } else if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    //==============================================================================================
    //                                      CLASSES
    //==============================================================================================

    /**
     * Interface which provide the doing some action inside the Handler thread
     */
    protected interface OnActionPerformer {
        void onPerform();
    }

    /**
     * Callback which provide the action with intent before start {@link android.app.Activity}
     */
    protected interface OnStartActivityCallback {
        /**
         * Method which provide the action before starting activity
         *
         * @param intent instance of {@link Intent}
         */
        void onPreExecute(@NonNull final Intent intent);
    }

    //==============================================================================================
    //                                   ABSTRACT METHODS
    //==============================================================================================

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    protected abstract int getLayoutId();

    /**
     * Method which provide the action when Activity is created
     */
    protected abstract void onCreateActivity(@Nullable final Bundle bundle);

    //TODO Example for the onOptionsItemSelected
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        // Handle action bar item clicks here. The action bar will
    //        // automatically handle clicks on the Home/Up button, so long
    //        // as you specify a parent activity in AndroidManifest.xml.
    //        int id = item.getItemId();
    //
    //        //noinspection SimplifiableIfStatement
    //        if (id == R.id.action_settings) {
    //            return true;
    //        }
    //
    //        return super.onOptionsItemSelected(item);
    //    }
}
