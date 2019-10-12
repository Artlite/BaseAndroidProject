package com.artlite.bslibrary.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.annotations.Warning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class which provide the thumbed progressbar functionality
 */
public class BSThumbProgressBar extends BSView {

    /**
     * {@link String} constants of the TAG
     */
    private static final String TAG = BSThumbProgressBar.class.getSimpleName();

    /**
     * {@link String} constants of the default color
     */
    private static final String K_DEFAULT_COLOR = "#2196F3";

    /**
     * {@link String} constants of the default format
     */
    private static final String K_DEFAULT_FORMAT = "%d%%";

    /**
     * Instance of the {@link BSThumbTextView}
     */
    protected BSThumbTextView textView;

    /**
     * Instance of the {@link ProgressBar}
     */
    protected ProgressBar progressBar;

    /**
     * Instance of the {@link Map}
     */
    @SuppressLint("UseSparseArrays")
    protected final Map<Integer, ColorStateList> sections = new HashMap<>();

    /**
     * Instance of the {@link List}
     */
    protected List<Integer> integers = null;

    /**
     * Instance of the {@link ColorStateList}
     */
    protected ColorStateList defaultColor;

    /**
     * Instance of the {@link ColorStateList}
     */
    protected ColorStateList labelColor;

    /**
     * {@link String} value of the default format
     */
    protected String defaultFormat = K_DEFAULT_FORMAT;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSThumbProgressBar(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSThumbProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSThumbProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.bs_thumb_progress_bar;
    }

    /**
     * Method which provide interface linking
     */
    @Override
    protected void onLinkInterface() {
        this.textView = findViewById(R.id.bs_label_thumb);
        this.progressBar = findViewById(R.id.bs_view_progress);
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        this.configure(100, K_DEFAULT_COLOR);
    }

    /**
     * Method which provide the setting min value and max value to progress view
     *
     * @param maxValue {@link Integer} value of the max
     */
    public void configure(@IntRange(from = 1, to = Integer.MAX_VALUE) int maxValue) {
        this.configure(maxValue, K_DEFAULT_COLOR);
    }

    /**
     * Method which provide the setting min value and max value to progress view
     *
     * @param maxValue        {@link Integer} value of the max
     * @param defaultColorHex {@link String} value of the default hex color
     */
    public void configure(@IntRange(from = 1, to = Integer.MAX_VALUE) int maxValue,
                          @Nullable String defaultColorHex) {
        this.configure(maxValue, defaultColorHex, true);
    }

    /**
     * Method which provide the setting min value and max value to progress view
     *
     * @param maxValue {@link Integer} value of the max
     */
    public void configure(@IntRange(from = 1, to = Integer.MAX_VALUE) int maxValue,
                          boolean isNeedProgressLabel) {
        this.configure(maxValue, K_DEFAULT_COLOR, isNeedProgressLabel);
    }

    /**
     * Method which provide the setting min value and max value to progress view
     *
     * @param maxValue        {@link Integer} value of the max
     * @param defaultColorHex {@link String} value of the default hex color
     */
    public void configure(@IntRange(from = 1, to = Integer.MAX_VALUE) int maxValue,
                          @Nullable String defaultColorHex,
                          boolean isNeedProgressLabel) {
        this.progressBar.setMax(maxValue);
        this.textView.setVisibility(isNeedProgressLabel ? VISIBLE : GONE);
        if (defaultColorHex != null) {
            try {
                this.defaultColor = ColorStateList.valueOf(Color.parseColor(defaultColorHex));
                this.setColor(this.defaultColor);
            } catch (Exception ex) {
                Log.e(TAG, "configure: ", ex);
            }
        }
    }

    /**
     * Method which provide the setting of the default format
     *
     * @param format {@link String} value of the default format
     */
    public void setDefaultFormat(String format) {
        this.defaultFormat = format;
    }

    /**
     * Method which provide the setting of the label color as HEX
     *
     * @param color {@link String} value of the HEX color
     */
    public void setLabelColor(String color) {
        try {
            this.labelColor = ColorStateList.valueOf(Color.parseColor(color));
        } catch (Exception ex) {
            this.labelColor = null;
            Log.e(TAG, "setLabelColor: ", ex);
        }
    }

    /**
     * Method which provide the setting of the progress
     *
     * @param progress {@link Integer} value of the progress
     */
    public void setProgress(int progress) {
        this.progressBar.setProgress(progress);
        this.setLabelValue(progress);
        this.textView.attach(this.progressBar);
        this.updateIntervalColors(progress);
    }

    /**
     * Method which provide the setting of the label value
     *
     * @param progress {@link Integer} value of the progress
     */
    @Warning(massage = "Could be overriding")
    @SuppressLint("DefaultLocale")
    protected void setLabelValue(int progress) {
        try {
            this.textView.setText(String.format(this.defaultFormat, progress));
        } catch (Exception ex) {
            this.textView.setText(String.format(K_DEFAULT_FORMAT, progress));
            Log.e(TAG, "setLabelValue: ", ex);
        }
    }

    /**
     * Method which provide the getting progress
     *
     * @return {@link Integer} value of the progress
     */
    public int getProgress() {
        return this.progressBar.getProgress();
    }


    /**
     * Method which provide the max value
     *
     * @return {@link Integer} value of the max
     */
    public int getMax() {
        return this.progressBar.getMax();
    }

    /**
     * Method which provide the set color
     *
     * @param hexColor {@link String} value of the color
     */
    public void setColor(@Nullable String hexColor) {
        if (hexColor == null) return;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setColor(ColorStateList.valueOf(Color.parseColor(hexColor)));
            }
        } catch (Exception ex) {
            this.setColor(K_DEFAULT_COLOR);
        }
    }

    /**
     * Method which provide the set color
     *
     * @param color {@link ColorStateList} value of the color
     */
    protected void setColor(@NonNull ColorStateList color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.progressBar.setProgressTintList(color);
                this.progressBar.setIndeterminateTintList(color);
            }
            this.textView.setTextColor((this.labelColor == null) ? color : this.labelColor);
        } catch (Exception ex) {
            Log.e(TAG, "setColor: ", ex);
        }
    }

    /**
     * Method which provide the add of the color section
     *
     * @param valueAfter {@link Integer} value after which we need to change color
     * @param hexColor   {@link String} value of the color
     */
    public BSThumbProgressBar addColorSection(int valueAfter,
                                              @Nullable String hexColor) {
        try {
            this.sections.put(valueAfter, ColorStateList.valueOf(Color.parseColor(hexColor)));
            this.integers = null;
        } catch (Exception ex) {
            Log.e(TAG, "addColorSection: ", ex);
        }
        return this;
    }

    /**
     * Method which provide the update of the interval colors
     */
    protected void updateIntervalColors(int progress) {
        if (this.integers == null) {
            this.integers = new ArrayList<>(this.sections.keySet());
            Collections.sort(this.integers);
        }
        if (this.integers.contains(progress)) {
            this.setColor(this.sections.get(progress));
        } else {
            ColorStateList colorStateList = null;
            List<Integer> copyIntegers = new ArrayList<>(this.integers);
            copyIntegers.add(progress);
            Collections.sort(copyIntegers);
            int index = copyIntegers.indexOf(progress);
            if (index > 0) {
                colorStateList = this.sections.get(index - 1);
            } else {
                colorStateList = this.defaultColor;
            }
            if (colorStateList != null) {
                this.setColor(colorStateList);
            }
        }
    }
}
