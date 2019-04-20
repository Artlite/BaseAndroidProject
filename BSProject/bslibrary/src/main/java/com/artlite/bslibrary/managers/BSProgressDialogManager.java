package com.artlite.bslibrary.managers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.bslibrary.ui.view.BSView;

/**
 * Class which provide the dialog of the progress
 */
public final class BSProgressDialogManager extends BSBaseManager implements BSView.OnDialogCallback {

    /**
     * Instance of the {@link BSProgressDialogManager}
     */
    private static BSProgressDialogManager instance;

    /**
     * Instance of the {@link DialogView}
     */
    private DialogView dialogView;

    /**
     * Method which provide the getting of the instance of the {@link BSProgressDialogManager}
     *
     * @return instance of the {@link BSProgressDialogManager}
     */
    public static BSProgressDialogManager getInstance() {
        return instance;
    }

    /**
     * Method which provide the initialization of {@link BSRandomManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSProgressDialogManager(context);
        } else {
            Log.e(TAG, "BSProgressDialogManager is already created");
        }
    }

    /**
     * Default constructor
     *
     * @param context
     */
    private BSProgressDialogManager(@NonNull Context context) {
        super(context);
    }

    /**
     * Method which provide the show of the show of the dialog
     *
     * @param text         instance of the {@link String}
     * @param isCancelable {@link Boolean} value if the dialog is cancellable
     */
    public static void show(@StringRes int text,
                            boolean isCancelable) {
        try {
            show(BSActivityManager.getForegroundActivity(), text, isCancelable);
        } catch (Exception ex) {
            Log.e(TAG, "show: ", ex);
        }
    }

    /**
     * Method which provide the show of the show of the dialog
     *
     * @param context      instance of the {@link Context}
     * @param text         instance of the {@link String}
     * @param isCancelable {@link Boolean} value if the dialog is cancellable
     */
    public static void show(@Nullable Context context,
                            @StringRes int text,
                            boolean isCancelable) {
        try {
            show(context, context.getString(text), isCancelable);
        } catch (Exception ex) {
            Log.e(TAG, "show: ", ex);
        }
    }

    /**
     * Method which provide the show of the show of the dialog
     *
     * @param isCancelable {@link Boolean} value if the dialog is cancellable
     */
    public static void show(boolean isCancelable) {
        show(BSActivityManager.getForegroundActivity(), null, isCancelable);
    }

    /**
     * Method which provide the show of the show of the dialog
     *
     * @param text         instance of the {@link String}
     * @param isCancelable {@link Boolean} value if the dialog is cancellable
     */
    public static void show(@Nullable String text,
                            boolean isCancelable) {
        show(BSActivityManager.getForegroundActivity(), text, isCancelable);
    }

    /**
     * Method which provide the show of the show of the dialog
     *
     * @param context      instance of the {@link Context}
     * @param text         instance of the {@link String}
     * @param isCancelable {@link Boolean} value if the dialog is cancellable
     */
    public static void show(@Nullable Context context,
                            @Nullable String text,
                            boolean isCancelable) {
        final BSProgressDialogManager instance = getInstance();
        if ((instance != null) && (context != null)) {
            hide();
            instance.dialogView = new DialogView(context);
            if (!TextUtils.isEmpty(text)) {
                instance.dialogView.labelText.setTextFromHtml(text);
                instance.dialogView.labelText.setVisibility(View.VISIBLE);
            } else {
                instance.dialogView.labelText.setVisibility(View.GONE);
            }
            instance.dialogView.showAsDialog(isCancelable, instance);
        }
    }

    /**
     * Method which provide the hide dialog
     */
    public static void hide() {
        final BSProgressDialogManager instance = getInstance();
        if ((instance != null) && (instance.dialogView != null)) {
            instance.dialogView.dismiss();
            instance.dialogView = null;
        }
    }

    /**
     * Method which provide the functional when the {@link Dialog} is showing
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onShow(@NonNull Context context,
                       @NonNull BSView view) {
        // TODO: 1/8/19 No need to do anything
    }

    /**
     * Method which provide the functional when {@link Dialog} is onCancel
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onCancel(@NonNull Context context,
                         @NonNull BSView view) {
        this.dialogView = null;
    }

    /**
     * Method which provide the action when dialog is closed
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onClose(@NonNull Context context,
                        @NonNull BSView view) {
        this.dialogView = null;
    }

    /**
     * Method which provide the action when {@link BSEventManager.Event} received
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     * @param event   instance of the {@link BSEventManager.Event}
     */
    @Override
    public void onEventReceived(@NonNull Context context,
                                @NonNull BSView view,
                                @NonNull BSView.Event event) {
        // TODO: 1/8/19 No need to do anything
    }

    //==============================================================================================
    //                                      DIALOG CLASS
    //==============================================================================================

    /**
     * Class which provide the dialog interface
     */
    private static class DialogView extends BSView {

        /**
         * Instance of the {@link BSTextView}
         */
        private BSTextView labelText;

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context instance of {@link Context}
         */
        public DialogView(Context context) {
            super(context);
        }

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context instance of {@link Context}
         * @param attrs   instance of {@link AttributeSet}
         */
        public DialogView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context      instance of {@link Context}
         * @param attrs        instance of {@link AttributeSet}
         * @param defStyleAttr attribute style
         */
        public DialogView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        /**
         * Method which provide the getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.bs_progress_dialog;
        }

        /**
         * Method which provide interface linking
         */
        @Override
        protected void onLinkInterface() {
            this.labelText = this.findViewById(R.id.label_text);
        }

        /**
         * Method which provide the creating of the {@link View}
         */
        @Override
        protected void onCreateView() {

        }
    }

}
