//package com.artlite.bslibrary.ui.view;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.widget.ImageView;
//
//import pl.droidsonroids.gif.GifDrawable;
//import pl.droidsonroids.gif.GifImageView;
//
//public final class BSImageView extends GifImageView {
//
//    /**
//     * A corresponding superclass constructor wrapper.
//     *
//     * @param context
//     * @see ImageView#ImageView(Context)
//     */
//    public BSImageView(Context context) {
//        super(context);
//    }
//
//    /**
//     * Like equivalent from superclass but also try to interpret src and background
//     * attributes as {@link GifDrawable}.
//     *
//     * @param context
//     * @param attrs
//     * @see ImageView#ImageView(Context, AttributeSet)
//     */
//    public BSImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    /**
//     * Like equivalent from superclass but also try to interpret src and background
//     * attributes as GIFs.
//     *
//     * @param context
//     * @param attrs
//     * @param defStyle
//     * @see ImageView#ImageView(Context, AttributeSet, int)
//     */
//    public BSImageView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    /**
//     * Like equivalent from superclass but also try to interpret src and background
//     * attributes as GIFs.
//     *
//     * @param context
//     * @param attrs
//     * @param defStyle
//     * @param defStyleRes
//     * @see ImageView#ImageView(Context, AttributeSet, int, int)
//     */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public BSImageView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
//        super(context, attrs, defStyle, defStyleRes);
//    }
//}
