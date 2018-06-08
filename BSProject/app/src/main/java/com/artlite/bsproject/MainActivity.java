package com.artlite.bsproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.image.BSImageHelper;
import com.artlite.bslibrary.helpers.intent.BSIntentHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSImageManager;
import com.artlite.bslibrary.managers.BSLocationManager;
import com.artlite.bslibrary.managers.BSStatusBarManager;
import com.artlite.bslibrary.transformation.BSGlideCropSquareTransformation;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.fonted.BSCurrencyEditText;
import com.artlite.bslibrary.ui.fonted.BSEditText;
import com.artlite.bslibrary.ui.view.BSDraggableLinearLayout;
import com.artlite.bslibrary.ui.view.BSView;

public class MainActivity extends BSActivity implements BSCurrencyEditText.OnCurrencyEditCallback {

    @FindViewBy(id = R.id.view_for_pop_up)
    private View forPopup;

    @FindViewBy(id = R.id.activity_main)
    private BSDraggableLinearLayout linearItemLayout;

    @FindViewBy(id = R.id.edit_popup)
    private BSEditText editText;

    @FindViewBy(id = R.id.image_view)
    private ImageView imageView;

    @FindViewBy(id = R.id.edit_currency)
    private BSCurrencyEditText currencyEditText;

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
        setOnClickListeners(R.id.button1, R.id.button2, R.id.button3);
        BSLocationManager.startLocationMonitoring(this);
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
        this.currencyEditText.configure(this);
        final String url = "https://img00.deviantart.net/2234/i/2017/085/2/c/ada_wong_by_artsbycarlos-db3bvd4.jpg";
        BSImageManager.create(this.imageView, url)
                .setPositionType(BSImageHelper.ImagePositionType.NONE)
                .setTransformation(new BSGlideCropSquareTransformation(R.dimen.dimen_10))
                .setPlaceholder(android.R.drawable.ic_notification_clear_all)
                .setSize(300, 300)
                .download();
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
                Location location = BSLocationManager.getLocation();
                String name = BSLocationManager.getLocationName();
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
            case R.id.button3: {
                BSStatusBarManager.open();

//                startActivityForPickImage();
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityImageResults(@NonNull final Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
        BSPermissionHelper.requestPermissions(this, new BSPermissionCallback() {
            @Override
            public void onPermissionGranted() {
                @SuppressLint("MissingPermission") final Intent intent
                        = BSIntentHelper.shareBitmap("Share receipt",
                        getBaseContext(), bitmap);
                if (!BSValidationHelper.isEmpty(intent)) {
                    startActivity(intent);
                }
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * Method which provide the action when the {@link BSCurrencyEditText} start
     * editing
     *
     * @param editText instance of the {@link BSCurrencyEditText}
     * @param editID   {@link Integer} value of the edit text ID
     * @param value    {@link Double} value of the value
     */
    @Override
    public void currencyEditStartEditing(@NonNull BSCurrencyEditText editText,
                                         int editID,
                                         double value) {
        editText.setText("");
    }

    /**
     * Method which provide the action when the {@link BSCurrencyEditText} finish
     * editing
     *
     * @param editText instance of the {@link BSCurrencyEditText}
     * @param editID   {@link Integer} value of the edit text ID
     * @param value    {@link Double} value of the value
     */
    @Override
    public void currencyEditFinishEditing(@NonNull BSCurrencyEditText editText,
                                          int editID,
                                          double value) {

    }
}
