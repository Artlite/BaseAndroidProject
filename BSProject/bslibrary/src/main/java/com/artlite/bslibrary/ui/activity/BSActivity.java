package com.artlite.bslibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.artlite.bslibrary.managers.BSThreadManager;

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
    protected final Handler MAIN_THREAD_HANDLER = new Handler();

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
        onCreateActivity();
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
     */
    protected void startActivity(Class activtyClass) {
        startActivity(new Intent(this, activtyClass));
    }

    //==============================================================================================
    //                                    ACTIVITY FOR RESULTS
    //==============================================================================================

    /**
     * Method which provide starting the Activity for results
     *
     * @param activtyClass activity which should be starting
     */
    protected void startActivityForResults(Class activtyClass) {
        startActivityForResult(new Intent(this, activtyClass), ON_BASE_ACTIVITY_RESULTS);
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
    //                                      CLASSES
    //==============================================================================================

    /**
     * Interface which provide the doing some action inside the Handler thread
     */
    protected interface OnActionPerformer {
        void onPerform();
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
    protected abstract void onCreateActivity();

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
