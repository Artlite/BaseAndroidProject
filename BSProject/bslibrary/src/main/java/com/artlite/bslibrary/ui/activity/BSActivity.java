package com.artlite.bslibrary.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.annotations.Warning;
import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.intent.BSIntentHelper;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.listeners.BSSwipeListener;
import com.artlite.bslibrary.managers.BSActivityManager;
import com.artlite.bslibrary.managers.BSLocalNotificationManager;
import com.artlite.bslibrary.managers.BSProgressDialogManager;
import com.artlite.bslibrary.managers.BSThreadManager;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public abstract class BSActivity
        extends AppCompatActivity
        implements View.OnClickListener,
        View.OnTouchListener {

    //==============================================================================================
    //                                      CONSTANTS
    //==============================================================================================

    /**
     * {@link String} constants of the tag
     */
    protected static final String TAG = BSActivity.class.getSimpleName();

    /**
     * {@link Integer} value of the pick image result
     */
    protected static final int K_ON_RESULT_PICK_IMAGE = 0b11;

    /**
     * {@link Integer} value of the request permission code
     */
    protected static final int K_REQUEST_PERMISSION_PHOTO = 0b10;

    /**
     * {@link Integer} value of the base activity results
     */
    protected static final int ON_BASE_ACTIVITY_RESULTS = 0b1;

    /**
     * {@link String} value of the results of the extra key
     */
    protected static final String ON_RESULT_EXTRA_KEY = "ON_RESULT_EXTRA_KEY";

    /**
     * {@link Integer} value of the none menu
     */
    protected static final int K_NONE_MENU = Integer.MIN_VALUE;

    /**
     * {@link String} constants of the {@link java.net.URI} key
     */
    protected static final String K_FULLSCREEN_KEY = "BSActivity:Fullscreen";

    //==============================================================================================
    //                                      FIELDS
    //==============================================================================================

    /**
     * {@link Boolean} value if it is launch activity
     */
    protected Boolean isLaunchActivity = null;

    /**
     * Instance of the {@link GestureDetector}
     */
    private GestureDetector gestureDetector;

    /**
     * Instance of the {@link BroadcastReceiver}
     */
    private BroadcastReceiver closeReceiver;

    /**
     * {@link Boolean} value if the Activity is full screen
     */
    private boolean fullScreened;

    //==============================================================================================
    //                                      CREATE
    //==============================================================================================

    /**
     * Perform initialization of all fragments and loaders
     *
     * @param bundle instance of {@link Bundle}
     */
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        // Registering of the close receiver
        BSLocalNotificationManager.register(this.getCloseReceiver(),
                this.getCloseKey());
        onInitBackButton();
        onInitGestures();
        BSInjector.inject(this);
        BSActivityManager.registerActivity(this);
        onCreateActivity((bundle == null) ? getIntent().getExtras() : bundle);
        BSThreadManager.main(new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                onActivityPostCreation((bundle == null) ? getIntent().getExtras() : bundle);
            }
        });
    }

    /**
     * Method which provide the onResume functionality
     */
    @Override
    protected void onResume() {
        super.onResume();
        BSActivityManager.registerActivity(this);
    }

    /**
     * Method which provide the on pause functionality
     */
    @Override
    protected void onPause() {
        super.onPause();
        BSProgressDialogManager.hide();
    }

    /**
     * Method which provide the action with destroying functional
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregistering of the close receiver
        BSLocalNotificationManager.unregister(this.getCloseReceiver());
    }

    /**
     * Method which provide the saving of the {@link Bundle} state
     *
     * @param bundle instance of the {@link Bundle}
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(K_FULLSCREEN_KEY, this.fullScreened);
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
    //                                         GESTURES
    //==============================================================================================

    /**
     * Method which provide the init gestures detector
     */
    protected void onInitGestures() {
        this.gestureDetector = new GestureDetector(this, this.swipeListener);
    }

    /**
     * Method which provide the touch functional
     *
     * @param view        instance of the {@link View}
     * @param motionEvent instance of the {@link MotionEvent}
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.gestureDetector != null) {
            this.gestureDetector.onTouchEvent(motionEvent);
        }
        return false;
    }

    /**
     * Method which provide of the swipe functional
     *
     * @param direction instance of the {@link BSSwipeListener.Direction}
     */
    protected boolean onSwipe(BSSwipeListener.Direction direction) {
        // TODO: 15.02.2018 Implement the swipe functional (override this method)
        return false;
    }

    /**
     * Instance of the {@link BSSwipeListener}
     */
    protected final BSSwipeListener swipeListener = new BSSwipeListener() {
        @Override
        public boolean onSwipe(Direction direction) {
            return BSActivity.this.onSwipe(direction);
        }
    };

    // ==============================================================================================
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
    protected void setOnClickListeners(@IdRes int... ids) {
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
     * Method which provide the starting activity for picking results
     */
    protected void startActivityForPickImage() {
        BSPermissionHelper.requestPermissions(this, new BSPermissionCallback() {
            @Override
            public void onPermissionGranted() {
                startActivityForResult(Intent.createChooser(BSIntentHelper.pickImage(),
                        getString(R.string.bs_text_select_image)), K_ON_RESULT_PICK_IMAGE);
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            // The user picked a contact.
            // The Intent's data Uri identifies which contact was selected.
            if ((requestCode == K_ON_RESULT_PICK_IMAGE) && (data != null)) {
                onActivityImageResults(data);
            } else {
                onActivityResult(requestCode, data);
            }
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
     * Method which provide the on activity pick image results
     *
     * @param data instance of the {@link Intent}
     */
    private void onActivityImageResults(@NonNull final Intent data) {
        final String methodName = "void onActivityImageResults(Intent)";
        Bitmap bitmap = null;
        try {
            Uri selectedImage = data.getData();
            InputStream imageStream = getContentResolver().openInputStream(selectedImage);
            bitmap = BitmapFactory.decodeStream(imageStream);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
        }
        if (bitmap != null) {
            onActivityImageResults(bitmap);
        }
    }

    /**
     * Method which provide the callback when image picking finished
     *
     * @param bitmap instance of the {@link Bitmap}
     */
    protected void onActivityImageResults(@NonNull final Bitmap bitmap) {

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
        }
        setResult(RESULT_OK, intent);
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
        overridePendingTransition(R.anim.bs_anim_none, R.anim.bs_anim_exit);
    }

    /**
     * Method which provide the starting activity
     *
     * @param intent instance of {@link Intent}
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.bs_anim_enter, R.anim.bs_anim_none);
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
    //                                   CLOSE ACTIVITY FUNCTIONAL
    //==============================================================================================

    /**
     * Method which provide the getting class for the closing functional
     *
     * @return instance of the {@link Class}
     */
    @Warning(massage = "THis method provide the class for the closing of the activity")
    @Nullable
    protected Class getCloseClass() {
        return null;
    }

    /**
     * Method which provide the getting of the closing key
     *
     * @return instance of the {@link String}
     */
    @Nullable
    protected String getCloseKey() {
        return getCloseKey(getCloseClass());
    }

    /**
     * Method which provide the getting of the closing key
     *
     * @param aClass instance of the {@link Class}
     * @return instance of the {@link String}
     */
    @Nullable
    protected static String getCloseKey(@Nullable Class aClass) {
        try {
            return String.format("CloseReceiver:%s", aClass.getName());
        } catch (Exception ex) {
            Log.e(TAG, "getCloseKey: ", ex);
        }
        return null;
    }

    /**
     * Method which provide the close receiver functional
     *
     * @return instance of the {@link BroadcastReceiver}
     */
    @NonNull
    protected BroadcastReceiver getCloseReceiver() {
        if (this.closeReceiver == null) {
            this.closeReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    finish();
                }
            };
        }
        return this.closeReceiver;
    }

    /**
     * Method which provide the closing of the all class activity
     *
     * @param aClass instance of the {@link Class}
     */
    public static void closeActivityByClass(@Nullable Class aClass) {
        final String key = getCloseKey(aClass);
        BSLocalNotificationManager.send(key);
    }


    //==============================================================================================
    //                                   FULLSCREEN
    //==============================================================================================

    /**
     * Method which provide to apply full screen if needed
     *
     * @param bundle instance of the {@link Bundle}
     */
    protected void applyFullscreenIfNeeded(@Nullable Bundle bundle) {
        if (bundle == null) return;
        this.fullScreened = bundle.getBoolean(K_FULLSCREEN_KEY);
        if (this.fullScreened) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * Method which provide the add fullscreen flag if needed
     *
     * @param activity instance of the {@link Activity}
     * @param intent   instance of the {@link Intent}
     * @return {@link Boolean} value if it added
     */
    protected static boolean addFullscreenFlagIfNeeded(@Nullable Activity activity,
                                                       @Nullable Intent intent) {
        try {
            final boolean fullscreened = (activity.getWindow().getAttributes().flags
                    & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
            intent.putExtra(K_FULLSCREEN_KEY, fullscreened);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "addFullscreenFlagIfNeeded: ", ex);
        }
        return false;
    }

    // =============================================================================================
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

    /**
     * Method which provide the action when Activity is created (post creation)
     * Use it if you create any callback inside the activity like
     * <b>final |CallbackType| callback = new |CallbackType|</b>
     */
    protected abstract void onActivityPostCreation(@Nullable final Bundle bundle);

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
