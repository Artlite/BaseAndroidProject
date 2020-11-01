package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.artlite.bslibrary.ui.fonted.BSButton;
import com.artlite.bslibrary.ui.fonted.BSTextView;

public class BSThumbTextView extends BSTextView {

    /**
     * Instance of the {@link LinearLayout.LayoutParams}
     */
    private LinearLayout.LayoutParams lp = new LinearLayout
            .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    /**
     * {@link Integer} value of the width
     */
    private int width = 0;

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context instance of {@link Context}
     */
    public BSThumbTextView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSThumbTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the creating of the {@link BSButton} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr value of default style attributes
     */
    public BSThumbTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide on measure functionality
     *
     * @param width  {@link Integer} value of the width
     * @param height {@link Integer} value of the height
     */
    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        if (this.width == 0)
            this.width = MeasureSpec.getSize(width);
    }


    /**
     * Method which provide to attach the {@link android.widget.TextView} to the {@link SeekBar}
     *
     * @param seekBar instance of the {@link SeekBar}
     */
    public void attach(@Nullable SeekBar seekBar) {
        String content = getText().toString();
        if (seekBar == null) return;
        if (TextUtils.isEmpty(content)) return;
        float contentWidth = this.getPaint().measureText(content);
        int realWidth = width - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
        int maxLimit = (int) (width - contentWidth - seekBar.getPaddingRight());
        int minLimit = seekBar.getPaddingLeft();
        float percent = (float) (1.0 * seekBar.getProgress() / seekBar.getMax());
        int left = minLimit + (int) (realWidth * percent - contentWidth / 2.0);
        left = left <= minLimit ? minLimit : left >= maxLimit ? maxLimit : left;
        lp.setMargins(left, 0, 0, 0);
        setLayoutParams(lp);
    }

    /**
     * Method which provide to attach the {@link android.widget.TextView} to the {@link SeekBar}
     *
     * @param progressBar instance of the {@link SeekBar}
     */
    public void attach(@Nullable ProgressBar progressBar) {
        String content = getText().toString();
        if (progressBar == null) return;
        if (TextUtils.isEmpty(content)) return;
        float contentWidth = this.getPaint().measureText(content);
        int realWidth = width - progressBar.getPaddingLeft() - progressBar.getPaddingRight();
        int maxLimit = (int) (width - contentWidth - progressBar.getPaddingRight());
        int minLimit = progressBar.getPaddingLeft();
        float percent = (float) (1.0 * progressBar.getProgress() / progressBar.getMax());
        int left = minLimit + (int) (realWidth * percent - contentWidth / 2.0);
        left = left <= minLimit ? minLimit : left >= maxLimit ? maxLimit : left;
        lp.setMargins(left, 0, 0, 0);
        setLayoutParams(lp);
    }
}
