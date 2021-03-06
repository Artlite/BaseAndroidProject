package com.artlite.bsproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.intent.BSIntentHelper;
import com.artlite.bslibrary.helpers.period.BSPeriodHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSApplicationManager;
import com.artlite.bslibrary.managers.BSLocationManager;
import com.artlite.bslibrary.managers.BSRandomManager;
import com.artlite.bslibrary.models.BSPackageModel;
import com.artlite.bslibrary.tasks.BSRepetitiveTask;
import com.artlite.bslibrary.ui.activity.BSAudioRecordActivity;
import com.artlite.bslibrary.ui.activity.BSLockableActivity;
import com.artlite.bslibrary.ui.fonted.BSCurrencyEditText;
import com.artlite.bslibrary.ui.fonted.BSEditText;
import com.artlite.bslibrary.ui.view.BSDraggableLinearLayout;
import com.artlite.bslibrary.ui.view.BSImageView;
import com.artlite.bslibrary.ui.view.BSThumbProgressBar;
import com.artlite.bslibrary.ui.view.BSView;

import org.threeten.bp.Period;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BSLockableActivity
        implements BSCurrencyEditText.OnCurrencyEditCallback,
        BSRepetitiveTask.OnActionCallback {

    /**
     * {@link String} constants of the TAG
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    @FindViewBy(id = R.id.activity_main)
    private BSDraggableLinearLayout linearItemLayout;

    @FindViewBy(id = R.id.edit_popup)
    private BSEditText editText;

    @FindViewBy(id = R.id.image_view)
    private BSImageView imageView;

    @FindViewBy(id = R.id.edit_currency)
    private BSCurrencyEditText currencyEditText;

    @FindViewBy(id = R.id.view_progress_bar)
    private BSThumbProgressBar progressBar;

    private BSRepetitiveTask repetitiveTask;

    private Rect rect;

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
        setOnClickListeners(R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5);
        BSLocationManager.startLocationMonitoring(this);
        this.repetitiveTask = new BSRepetitiveTask(100, this,
                BSRepetitiveTask.TimeMeasure.MILLISECONDS);
        this.editText.setTextColor(BSRandomManager.randomColor());
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
        Period period = BSPeriodHelper.getDifference(new Date(0));
        Log.d(TAG, "onActivityPostCreation: " + period.getDays()
                + ":" + period.getMonths()
                + ":" + period.getYears());
        this.lockActivity();
        this.currencyEditText.configure(Locale.GERMANY, this);
        this.unlockActivity();
        this.progressBar.configure(100, true);
        this.progressBar
                .addColorSection(10, "#8BC34A")
                .addColorSection(20, "#FF5722")
                .addColorSection(30, "#039BE5")
                .addColorSection(40, "#00695C")
                .addColorSection(90, "#b71c1c");
        this.repetitiveTask.start();
    }

    /**
     * Method which provide the getting class for the closing functional
     *
     * @return instance of the {@link Class}
     */
    @Nullable
    @Override
    protected Class getCloseClass() {
        return this.getClass();
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
                String locationName = BSLocationManager.getLocationName();
                Log.e("", "onClick: " + locationName, null);
                startActivity(MarkdownActivity.class);
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
                BSAudioRecordActivity.start(this, 120);
                break;
            }
            case R.id.button4: {
                startActivity(TagsActivity.class);
                break;
            }
            case R.id.button5: {
                startActivity(PDFActivity.class);
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
     * Method which provide the action when activity return result
     *
     * @param data current intent
     */
    @Override
    protected void onActivityResult(int requestCode, Intent data) {
        if (BSAudioRecordActivity.checkEvent(requestCode)) {
            final Uri uri = BSAudioRecordActivity.getExtras(data);
            Log.d("sdasdsda", "onActivityResult: " + uri);
        }
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

    /**
     * Method which provide the action performing
     */
    @Override
    public void onActionPerformed() {
        int progress = this.progressBar.getProgress();
        int max = this.progressBar.getMax();
        if (progress >= max) {
            this.repetitiveTask.stop();
            this.progressBar.setProgress(0);
        } else {
            this.progressBar.setProgress(progress + 1);
        }
    }
}
