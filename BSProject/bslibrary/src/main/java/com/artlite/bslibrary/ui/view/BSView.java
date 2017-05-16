package com.artlite.bslibrary.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.artlite.bslibrary.helpers.dialog.BSDialogHelper;
import com.artlite.bslibrary.helpers.injector.BSInjector;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.popup.BSPopupHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

import java.lang.ref.WeakReference;

/**
 * Class which provide the base {@link View} functional
 */

public abstract class BSView extends LinearLayout
        implements View.OnClickListener, DialogInterface.OnDismissListener,
        DialogInterface.OnCancelListener, DialogInterface.OnShowListener {

    /**
     * Instance of {@link View}
     */
    protected View baseView;

    /**
     * Instance of {@link OnPopupCallback}
     */
    protected WeakReference<OnPopupCallback> popupCallback;

    /**
     * Instance of the {@link WeakReference}
     */
    protected WeakReference<Dialog> dialogReference;

    /**
     * Instance of the {@link OnDialogCallback}
     */
    protected WeakReference<OnDialogCallback> dialogCallbackReference;

    /**
     * Instance of the {@link OnDropdownCallback}
     */
    protected WeakReference<OnDropdownCallback> dropdownCallbackReference;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSView(Context context) {
        super(context);
        onInitializeView(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitializeView(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitializeView(context);
    }

    /**
     * Method which provide the {@link BSView} initializing
     *
     * @param context instance of {@link Context}
     */
    private void onInitializeView(Context context) {
        inflateView(context, getLayoutId());
        if (baseView != null) {
            BSInjector.inject(baseView);
            BSInjector.inject(this);
        }
        onCreateView();
    }

    /**
     * Method which provide the inflating of the view
     *
     * @param context  current context
     * @param layoutID layout id
     */
    private void inflateView(Context context, int layoutID) {
        LayoutInflater inflater = LayoutInflater.from(context);
        baseView = inflater.inflate(layoutID, this);
    }

    /**
     * Method which provide the action before {@link View} will be gone
     */
    @Override
    protected void onDetachedFromWindow() {
        final OnDropdownCallback callback = getDropdownCallback();
        final Context context = getContext();
        if (BSValidationHelper.validateEmpty(callback, context)) {
            callback.onClose(context, this);
        }
        super.onDetachedFromWindow();
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    protected abstract int getLayoutId();

    /**
     * Method which provide the creating of the {@link View}
     */
    protected abstract void onCreateView();

    /**
     * Method which provide starting the Activity
     *
     * @param activtyClass activity which should be starting
     */
    protected void startActivity(Class activtyClass) {
        getContext().startActivity(new Intent(getContext(), activtyClass));
    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param views current list of Views
     */
    protected void setOnClickListeners(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    /**
     * Method which provide the setting of the OnClickListener
     *
     * @param ids current list of {@link View} IDs
     */
    protected void setOnClickListeners(@IdRes Integer... ids) {
        for (Integer id : ids) {
            final View view = findViewById(id);
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }

    /**
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {

    }

    //==============================================================================================
    //                                          POPUP
    //==============================================================================================

    /**
     * Method which provide the setting of the {@link OnPopupCallback}
     *
     * @param popupCallback instance of {@link OnPopupCallback}
     */
    public void setPopupCallback(@Nullable final OnPopupCallback popupCallback) {
        if (!BSValidationHelper.isEmpty(popupCallback)) {
            this.popupCallback = new WeakReference<OnPopupCallback>(popupCallback);
        }
    }

    /**
     * Method which provide the {@link OnPopupCallback} getting from the {@link BSView}
     *
     * @return instance of the {@link OnPopupCallback}
     */
    @Nullable
    protected OnPopupCallback getPopupCallback() {
        return (popupCallback == null)
                ? null : popupCallback.get();
    }

    /**
     * Method which provide the setting of the instance of the {@link OnDropdownCallback}
     *
     * @param callbacks instance of the {@link OnDropdownCallback}
     */
    public void setDropdownCallback(@Nullable final OnDropdownCallback... callbacks) {
        if ((callbacks != null) && (callbacks.length > 0)) {
            final OnDropdownCallback callback = callbacks[0];
            if (callback != null) {
                this.dropdownCallbackReference = new WeakReference<OnDropdownCallback>(callback);
            }
        }
    }

    /**
     * Method which provide the getting of the instance of the {@link OnDropdownCallback}
     *
     * @return instance of the {@link OnDropdownCallback}
     */
    @Nullable
    protected OnDropdownCallback getDropdownCallback() {
        return (dropdownCallbackReference == null)
                ? null : dropdownCallbackReference.get();
    }

    /**
     * Method which provide the dismissing the pop up window if popUpListener isn't null
     */
    protected void dismissPopup() {
        final OnPopupCallback callback = getPopupCallback();
        if (callback != null) {
            callback.close();
        }
    }

    /**
     * Method which provide the show {@link BSView} as drop down to target {@link View}
     *
     * @param targetView instance of target {@link View}
     * @param callbacks  instance of the {@link OnDropdownCallback}
     */
    public void showAsDropdown(@Nullable final View targetView,
                               @Nullable final OnDropdownCallback... callbacks) {
        setDropdownCallback(callbacks);
        final OnDropdownCallback callback = getDropdownCallback();
        final Context context = getContext();
        BSPopupHelper.show(this, targetView);
        if (BSValidationHelper.validateEmpty(context, callback)) {
            callback.onShow(context, this);
        }
    }

    /**
     * Method which provide the show {@link BSView} as drop down to target {@link View}
     *
     * @param targetView instance of target {@link View}
     * @param paddingTop {@link Intent} value of the padding top
     * @param callbacks  instance of the {@link OnDropdownCallback}
     */
    public void showAsDropdown(@Nullable final View targetView,
                               int paddingTop,
                               @Nullable final OnDropdownCallback... callbacks) {
        BSPopupHelper.show(this, targetView, paddingTop);
    }

    /**
     * Method which provide the checking if {@link BSView} is destroyed
     *
     * @throws Throwable error while finalization
     */
    @Override
    protected void finalize() throws Throwable {
        final String methodName = "void finalize()";
        BSLogHelper.log(this, methodName, null, "Object is destroyed");
        super.finalize();
    }

    //==============================================================================================
    //                                          DIALOG
    //==============================================================================================

    /**
     * Method which provide the setting of the {@link Dialog} inside the {@link BSView}
     *
     * @param dialog instance of the {@link Dialog}
     */
    public void setDialog(@Nullable final Dialog dialog) {
        if (!BSValidationHelper.isNull(dialog)) {
            dialog.setOnDismissListener(this);
            dialog.setOnCancelListener(this);
            dialog.setOnShowListener(this);
            this.dialogReference = new WeakReference<Dialog>(dialog);
        }
    }

    /**
     * Method which provide the {@link Dialog} getting
     *
     * @return instance of the {@link Dialog}
     */
    @Nullable
    protected Dialog getDialog() {
        return (dialogReference == null)
                ? null : dialogReference.get();
    }

    /**
     * Method which provide the dismiss {@link Dialog} from the {@link BSView}
     */
    protected void dismissDialog() {
        final Dialog dialog = getDialog();
        if (!BSValidationHelper.isNull(dialog)) {
            dialog.dismiss();
        }
    }

    /**
     * Method which provide the dismiss {@link Dialog} from the {@link BSView}
     */
    protected void cancelDialog() {
        final Dialog dialog = getDialog();
        if (!BSValidationHelper.isNull(dialog)) {
            dialog.cancel();
        }
    }

    /**
     * Method which provide the getting of the instance of the {@link OnDialogCallback}
     *
     * @return instance of the {@link OnDialogCallback}
     */
    @Nullable
    protected OnDialogCallback getDialogCallback() {
        return (dialogCallbackReference == null)
                ? null : dialogCallbackReference.get();
    }

    /**
     * Method which provide the setting of the {@link OnDialogCallback}
     *
     * @param callback instance of the {@link OnDialogCallback}
     */
    public void setDialogCallback(@Nullable final OnDialogCallback callback) {
        if (callback != null) {
            this.dialogCallbackReference = new WeakReference<OnDialogCallback>(callback);
        }
    }

    /**
     * Method which provide the action when the {@link Dialog} is dismissing
     *
     * @param dialog instance of the {@link DialogInterface}
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        final OnDialogCallback callback = getDialogCallback();
        final Context context = getContext();
        if (!BSValidationHelper.isEmpty(context, callback)) {
            callback.onClose(context, this);
        }
    }

    /**
     * Method which provide the action when the {@link Dialog} is cancelling
     *
     * @param dialog instance of the {@link DialogInterface}
     */
    @Override
    public void onCancel(DialogInterface dialog) {
        final OnDialogCallback callback = getDialogCallback();
        final Context context = getContext();
        if (!BSValidationHelper.isEmpty(context, callback)) {
            callback.onCancel(context, this);
        }
    }

    /**
     * Method which provide the action when the {@link Dialog} is showing
     *
     * @param dialog instance of the {@link DialogInterface}
     */
    @Override
    public void onShow(DialogInterface dialog) {
        final OnDialogCallback callback = getDialogCallback();
        final Context context = getContext();
        if (!BSValidationHelper.isEmpty(context, callback)) {
            callback.onShow(context, this);
        }
    }

    /**
     * Method which provide the show of the {@link BSView} as {@link Dialog}
     */
    public void showAsDialog() {
        showAsDialog(true, null);
    }

    /**
     * Method which provide the show of the {@link BSView} as {@link Dialog}
     */
    public void showAsDialog(boolean isCancelable) {
        showAsDialog(isCancelable, null);
    }

    /**
     * Method which provide the show of the {@link BSView} as {@link Dialog}
     *
     * @param callback instance of the {@link OnDialogCallback}
     */
    public void showAsDialog(@Nullable final OnDialogCallback callback) {
        showAsDialog(true, callback);
    }

    /**
     * Method which provide the show of the {@link BSView} as {@link Dialog}
     *
     * @param callback instance of the {@link OnDialogCallback}
     */
    public void showAsDialog(boolean isCancelable, @Nullable final OnDialogCallback callback) {
        setDialogCallback(callback);
        final Dialog dialog = BSDialogHelper.create(getContext(), isCancelable, this);
        dialog.show();
    }

    //==============================================================================================
    //                                          EVENT
    //==============================================================================================

    /**
     * Method which provide the dismissing of the {@link BSView}
     */
    public void dismiss() {
        dismissPopup();
        dismissDialog();
    }

    //==============================================================================================
    //                                          EVENT
    //==============================================================================================

    /**
     * Method which provide the {@link Event} sending
     *
     * @param event instance of the {@link Event}
     */
    protected void sendEvent(@Nullable final Event event) {
        final OnDropdownCallback dropdownCallback = getDropdownCallback();
        final OnDialogCallback dialogCallback = getDialogCallback();
        final Context context = getContext();
        if (BSValidationHelper.validateEmpty(context, event)) {
            if (dropdownCallback != null) {
                dropdownCallback.onEventReceived(context, this, event);
            }
            if (dialogCallback != null) {
                dialogCallback.onEventReceived(context, this, event);
            }
        }
    }

    //==============================================================================================
    //                                          CLASSES
    //==============================================================================================

    /**
     * Listener which provide the interaction between
     * View and Popup Window
     */
    public interface OnPopupCallback {

        /**
         * Method which provide the closing functional
         */
        void close();
    }


    /**
     * Class which provide the dialog performance
     *
     * @param <T> class type
     */
    public interface OnDialogCallback<T extends BSView> {

        /**
         * Method which provide the functional when the {@link Dialog} is showing
         *
         * @param view    instance of the {@link BSView}
         * @param context instance of {@link Context}
         */
        void onShow(@NonNull final Context context, @NonNull final T view);

        /**
         * Method which provide the functional when {@link Dialog} is onCancel
         *
         * @param view    instance of the {@link BSView}
         * @param context instance of {@link Context}
         */
        void onCancel(@NonNull final Context context, @NonNull final T view);

        /**
         * Method which provide the action when dialog is closed
         *
         * @param view    instance of the {@link BSView}
         * @param context instance of {@link Context}
         */
        void onClose(@NonNull final Context context, @NonNull final T view);

        /**
         * Method which provide the action when {@link Event} received
         *
         * @param context instance of {@link Context}
         * @param view    instance of the {@link BSView}
         * @param event   instance of the {@link Event}
         */
        void onEventReceived(@NonNull final Context context,
                             @NonNull final T view,
                             @NonNull final Event event);
    }

    /**
     * Class which provide the drop down performance
     *
     * @param <T>
     */
    public interface OnDropdownCallback<T extends BSView> {

        /**
         * Method which provide the functional when the {@link Dialog} is showing
         *
         * @param view    instance of the {@link BSView}
         * @param context instance of {@link Context}
         */
        void onShow(@NonNull final Context context, @NonNull final T view);

        /**
         * Method which provide the action when dialog is closed
         *
         * @param view    instance of the {@link BSView}
         * @param context instance of {@link Context}
         */
        void onClose(@NonNull final Context context, @NonNull final T view);

        /**
         * Method which provide the action when {@link Event} received
         *
         * @param context instance of {@link Context}
         * @param view    instance of the {@link BSView}
         * @param event   instance of the {@link Event}
         */
        void onEventReceived(@NonNull final Context context,
                             @NonNull final T view,
                             @NonNull final Event event);

    }

    /**
     * Class which provide the action for {@link BSView}
     */
    public static class Event {
        /**
         * {@link Integer} value for the {@link Event}
         */
        private final Integer code;

        /**
         * {@link String} value fro the {@link Event}
         */
        private final String value;

        /**
         * Constructor which provide the create {@link Event} from code
         *
         * @param code {@link Integer} value of {@link Event}
         */
        public Event(int code) {
            this.code = code;
            this.value = null;
        }

        /**
         * Constructor which provide the create {@link Event} from code
         *
         * @param value {@link String} value of {@link Event}
         */
        public Event(String value) {
            this.value = value;
            this.code = null;
        }

        /**
         * Method which provide the equaling of the {@link Event}
         *
         * @param object instance of the other {@link Event}
         * @return equaling result
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            Event action = (Event) object;

            if (BSValidationHelper.validateEmpty(code, action.code)) {
                return code.intValue() == action.code.intValue();
            } else if (BSValidationHelper.validateEmpty(value, action.value)) {
                return value.equalsIgnoreCase(action.value);
            }
            return false;
        }

        /**
         * Method which provide the generate of the hash code for the {@link Event}
         *
         * @return {@link Integer} value of the hash code
         */
        @Override
        public int hashCode() {
            int result = code != null ? code.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        /**
         * Method which provide the {@link String} representation of the {@link Event}
         *
         * @return {@link String} representation of the {@link Event}
         */
        @Override
        public String toString() {
            return "Event{" +
                    "code=" + code +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
