package com.artlite.bslibrary.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.ui.fonted.BSTextView;

import java.io.File;
import java.util.Date;

/**
 * Activity which provide the recording of the audio
 */
public class BSAudioRecordActivity
        extends BSDialogActivity
        implements MediaPlayer.OnCompletionListener {

    /**
     * States for the {@link BSAudioRecordActivity}
     */
    private enum ActivityState {
        NONE,
        RECORDING,
        PLAYING
    }

    /**
     * States for the {@link BSAudioRecordActivity}
     */
    private enum RecordingState {
        NONE,
        RECORDED
    }


    /**
     * {@link String} constants of the TAG
     */
    private static final String TAG = BSAudioRecordActivity.class.getSimpleName();

    /**
     * {@link Integer} value of the on record result
     */
    private static final int K_ON_RECORD_RESULT = 0b101110001100110;

    /**
     * {@link String} constants of the {@link java.net.URI} key
     */
    private static final String K_URI_KEY = "BSAudioRecordActivity:AudioUri";

    /**
     * {@link String} array of the permissions
     */
    private final String[] permissions = {Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    /**
     * Instance of the {@link MediaPlayer}
     */
    private MediaPlayer player = null;

    /**
     * Instance of the {@link MediaPlayer}
     */
    private MediaRecorder recorder = null;

    /**
     * {@link String} value of the file name
     */
    private String fileName = null;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView buttonRecord;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView buttonPlay;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView buttonSave;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView buttonDelete;

    /**
     * Array of the {@link ImageView}
     */
    private ImageView[] buttons;

    /**
     * Instance of the {@link BSTextView}
     */
    private BSTextView labelCounter;

    /**
     * Instance of the {@link ActivityState}
     */
    private ActivityState state = ActivityState.NONE;

    /**
     * Instance of the {@link ActivityState}
     */
    private RecordingState recordingState = RecordingState.NONE;

    /**
     * Instance of the {@link Handler}
     */
    private final Handler handler = new Handler();

    /**
     * {@link Integer} values of the time
     */
    private int hour, minute, second;// variables holding the hour and minute

    /**
     * Instance of the {@link Runnable}
     */
    private final Runnable updateTimeRunnable = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            second += 1;
            // just some checks to keep everything in order
            if (second >= 60) {
                second = 0;
                minute += 1;

            }
            if (minute >= 60) {
                minute = 0;
                hour += 1;
            }
            if (hour >= 24) {
                hour = 0;
            }
            // or call your method
            updateTime();
            handler.postDelayed(this, 1000);
        }
    };

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.bs_audio_record_activity;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        this.applyFullscreenIfNeeded(bundle);
        this.lockActivity();
        this.setOnClickListeners(R.id.container_ar);
        this.buttonRecord = ((Activity) this).findViewById(R.id.bs_button_record);
        this.buttonPlay = ((Activity) this).findViewById(R.id.bs_button_play);
        this.buttonSave = ((Activity) this).findViewById(R.id.bs_button_save);
        this.buttonDelete = ((Activity) this).findViewById(R.id.bs_button_delete);
        this.labelCounter = ((Activity) this).findViewById(R.id.bs_label_counter);
        this.buttons = new ImageView[]{this.buttonRecord,
                this.buttonPlay,
                this.buttonSave,
                this.buttonDelete};
        this.setOnClickListeners(this.buttonRecord,
                this.buttonPlay,
                this.buttonSave,
                this.buttonDelete);
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
        this.unlockRecordActivity();
        this.enableInterface();
    }

    /**
     * Method which provide the unlocking of the activity
     */
    protected void unlockRecordActivity() {
        BSPermissionHelper.requestPermissions(this, new BSPermissionCallback() {
            @Override
            public void onPermissionGranted() {
                unlockActivity();
            }
        }, permissions);
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *                     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.unlockRecordActivity();
    }

    /**
     * Overriden method for the OnClickListener
     *
     * @param v current view
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.container_ar) {
            this.onDeletePressed(false);
            this.sendActivityResult(null);
        } else if (id == R.id.bs_button_record) {
            if ((this.state == ActivityState.NONE) || (this.state == ActivityState.RECORDING)) {
                this.onRecordPressed();
            }
        } else if (id == R.id.bs_button_play) {
            if ((this.state == ActivityState.NONE) || (this.state == ActivityState.PLAYING)) {
                this.onPlayPressed();
            }
        } else if (id == R.id.bs_button_save) {
            if (this.state == ActivityState.NONE) {
                this.onSavePressed();
            }
        } else if (id == R.id.bs_button_delete) {
            if (this.state == ActivityState.NONE) {
                this.onDeletePressed(true);
            }
        }
    }

    /**
     * Method which provide the on record pressed
     */
    protected void onRecordPressed() {
        if (this.state == ActivityState.NONE) {
            this.state = ActivityState.RECORDING;
            this.startCounting();
            this.startRecording();
        } else {
            this.state = ActivityState.NONE;
            this.recordingState = RecordingState.RECORDED;
            this.stopCounting();
            this.stopRecording();
        }
        this.updateInterface();
    }

    /**
     * Method which provide the start recording
     */
    protected void startRecording() {
        this.stopRecording();
        if (this.recorder == null) {
            this.generateFileName();
            this.recorder = new MediaRecorder();
            this.recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            this.recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            this.recorder.setOutputFile(this.fileName);
            this.recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                this.recorder.prepare();
                this.recorder.start();
            } catch (Exception ex) {
                this.stopRecording();
                this.recordingState = RecordingState.NONE;
                Log.e(TAG, "startRecording: ", ex);
            }
        }
    }

    /**
     * Method which provide the stop recording
     */
    protected void stopRecording() {
        if (this.recorder != null) {
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
            } catch (Exception ex) {
                this.recordingState = RecordingState.NONE;
                Log.e(TAG, "stopRecording: ", ex);
            }
        }
    }

    /**
     * Method which provide the on record pressed
     */
    protected void onPlayPressed() {
        if (recordingState == RecordingState.NONE) return;
        if (this.state == ActivityState.NONE) {
            this.state = ActivityState.PLAYING;
            this.startPlaying();
            this.startCounting();
        } else {
            this.state = ActivityState.NONE;
            this.stopPlaying();
            this.stopCounting();
        }
        this.updateInterface();
    }

    /**
     * Method which provide the start recording
     */
    protected void startPlaying() {
        this.stopPlaying();
        if (this.player == null) {
            this.player = new MediaPlayer();
            try {
                this.player.setOnCompletionListener(this);
                this.player.setDataSource(this.fileName);
                this.player.prepare();
                this.player.start();
            } catch (Exception ex) {
                this.stopPlaying();
                Log.e(TAG, "startPlaying: ", ex);
            }
        }
    }

    /**
     * Method which provide the stop recording
     */
    protected void stopPlaying() {
        if (this.player != null) {
            try {
                this.player.release();
                this.player = null;
            } catch (Exception ex) {
                Log.e(TAG, "stopPlaying: ", ex);
            }
        }
    }

    /**
     * Called when the end of a media source is reached during playback.
     *
     * @param mp the MediaPlayer that reached the end of the file
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        this.onPlayPressed();
    }

    /**
     * Method which provide the on record pressed
     */
    protected void onSavePressed() {
        if (recordingState == RecordingState.NONE) return;
        try {
            this.stopRecording();
            this.stopPlaying();
            final File file = new File(this.fileName);
            final Uri uri = Uri.fromFile(file);
            this.sendActivityResult(uri);
        } catch (Exception ex) {
            Log.e(TAG, "onSavePressed: ", ex);
        }
    }

    /**
     * Method which provide the on record pressed
     *
     * @param updateInterface {@link Boolean} value if the activity need the update the interface
     */
    protected void onDeletePressed(boolean updateInterface) {
        if (this.recordingState == RecordingState.NONE) return;
        try {
            this.stopRecording();
            this.stopPlaying();
            final File file = new File(this.fileName);
            if (file.exists()) file.delete();
            this.fileName = null;
            this.recordingState = RecordingState.NONE;
            if (updateInterface) this.updateInterface();
        } catch (Exception ex) {
            Log.e(TAG, "onSavePressed: ", ex);
        }
    }

    /**
     * Method which provide the updating of the interface
     */
    protected void updateInterface() {
        if (this.state == ActivityState.NONE) {
            this.enableInterface();
        } else {
            this.lockInterface();
        }
    }

    /**
     * Method which provide the interface resetting
     */
    private void enableInterface() {
        for (int i = 0; i < this.buttons.length; i++) {
            final ImageView imageView = this.buttons[i];
            imageView.setAlpha(1.0f);
            switch (i) {
                case 0:
                    imageView.setImageResource(R.drawable.bs_ic_microphone_red);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.bs_ic_play);
                    if (recordingState == RecordingState.NONE) {
                        imageView.setAlpha(0.4f);
                    } else {
                        imageView.setAlpha(1.0f);
                    }
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.bs_ic_save);
                    if (recordingState == RecordingState.NONE) {
                        imageView.setAlpha(0.4f);
                    } else {
                        imageView.setAlpha(1.0f);
                    }
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.bs_ic_delete);
                    if (recordingState == RecordingState.NONE) {
                        imageView.setAlpha(0.4f);
                    } else {
                        imageView.setAlpha(1.0f);
                    }
                    break;
            }
        }
    }

    /**
     * Method which provide the lock of the interface
     */
    private void lockInterface() {
        for (int i = 0; i < this.buttons.length; i++) {
            final ImageView imageView = this.buttons[i];
            if (this.state == ActivityState.RECORDING) {
                if (i == 0) {
                    imageView.setAlpha(1.0f);
                } else {
                    imageView.setAlpha(0.4f);
                }
            } else if (this.state == ActivityState.PLAYING) {
                if (i == 1) {
                    imageView.setAlpha(1.0f);
                } else {
                    imageView.setAlpha(0.4f);
                }
            }
            switch (i) {
                case 0:
                    if (this.state == ActivityState.RECORDING) {
                        imageView.setImageResource(R.drawable.bs_ic_stop);
                    } else {
                        imageView.setImageResource(R.drawable.bs_ic_microphone_red);
                    }
                    break;
                case 1:
                    if (this.state == ActivityState.PLAYING) {
                        imageView.setImageResource(R.drawable.bs_ic_stop);
                    } else {
                        imageView.setImageResource(R.drawable.bs_ic_play);
                    }
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.bs_ic_save);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.bs_ic_delete);
                    break;
            }
        }
    }

    /**
     * Method which provide the start timer counting
     */
    protected void startCounting() {
        this.resetTime();
        this.updateTime();
        this.handler.postDelayed(this.updateTimeRunnable, 1000);
    }

    /**
     * Method which provide the start timer counting
     */
    protected void stopCounting() {
        this.resetTime();
        this.updateTime();
        handler.removeCallbacks(this.updateTimeRunnable);
    }

    /**
     * Method which provide the time resetting
     */
    protected void resetTime() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    /**
     * Method which provide the time updating
     */
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    protected void updateTime() {
        this.labelCounter.setText(String.format("%02d", hour)
                + ":" + String.format("%02d", minute)
                + ":" + String.format("%02d", second));
    }

    /**
     * Method which provide the generating of the file name
     */
    protected void generateFileName() {
        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS).toString();
        File myDir = new File(root);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        @SuppressLint("DefaultLocale") String fileName = String.format("record_%d.3gp",
                new Date().getTime());
        File file = new File(myDir, fileName);
        if (file.exists()) file.delete();
        this.fileName = file.getAbsolutePath();
        Log.d(TAG, "generateFileName: \n\t\tFile name:" + fileName);

    }

    /**
     * Method which provide the sending of the Activity results
     *
     * @param value current extra value
     */
    protected <T extends Parcelable> void sendActivityResult(T value) {
        final Intent intent = new Intent();
        if (value != null) {
            intent.putExtra(K_URI_KEY, value);
        }
        setResult(RESULT_OK, intent);
        this.finish();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        this.onDeletePressed(false);
        this.sendActivityResult(null);
    }

    /**
     * Method which provide the start of the {@link BSAudioRecordActivity}
     *
     * @param activity instance of the parent {@link Activity}
     */
    public static void start(@Nullable Activity activity) {
        if (activity == null) return;
        final Intent intent = new Intent(activity, BSAudioRecordActivity.class);
        addFullscreenFlagIfNeeded(activity, intent);
        activity.startActivityForResult(intent, K_ON_RECORD_RESULT);
        activity.overridePendingTransition(android.R.anim.fade_in, 0);
    }

    /**
     * Method which provide the checking of the event equaling
     *
     * @param requestCode {@link Integer} value of the request code
     * @return instance of the {@link Boolean}
     */
    public static boolean checkEvent(int requestCode) {
        return K_ON_RECORD_RESULT == requestCode;
    }

    /**
     * Method which provide the get of the URI extras
     *
     * @param data instance of the {@link Intent}
     * @return instance of the {@link Uri}
     */
    @Nullable
    public static Uri getExtras(@Nullable Intent data) {
        try {
            return data.getParcelableExtra(K_URI_KEY);
        } catch (Exception ex) {
            Log.e(TAG, "getExtras: ", ex);
        }
        return null;
    }

}
