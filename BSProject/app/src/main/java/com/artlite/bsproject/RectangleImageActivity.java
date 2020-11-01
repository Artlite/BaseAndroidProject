package com.artlite.bsproject;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.helpers.image.BSImageHelper;
import com.artlite.bslibrary.managers.BSImageManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSImageView;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.otaliastudios.zoom.ZoomApi;
import com.otaliastudios.zoom.ZoomLayout;

public class RectangleImageActivity extends BSActivity implements BSImageView.OnImageViewCallback {

    /**
     * Instance of the {@link BSImageView}
     */
    @FindViewBy(id = R.id.image_view)
    private BSImageView imageView;

    @FindViewBy(id = R.id.layout_zoom)
    private ZoomLayout zoomLayout;


    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rectangle_image;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {

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
        this.imageView.setCallback(this);
//        final String url = "https://orig00.deviantart.net/2333/f/2015/150/d/c/ada_wong_by_gladiusgaming-d8vbviz.jpg";
        final String url = "https://image.ibb.co/eSF3zJ/Nijiya_market.jpg";
        this.zoomLayout.getEngine().setTransformation(ZoomApi.TRANSFORMATION_CENTER_CROP,
                Gravity.TOP);
        BSImageManager.create(this.imageView, url)
                .setPositionType(BSImageHelper.ImagePositionType.NONE)
                .setPlaceholder(android.R.drawable.ic_notification_clear_all)
                .download(new SimpleTarget<Bitmap>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        imageView.drawRectRound(android.R.color.holo_red_dark,
                                180,
                                10,
                                10,
                                200,
                                200);
                        imageView.drawRectRound(android.R.color.holo_green_dark,
                                180,
                                210,
                                10,
                                400,
                                200);
                    }
                });
    }

    /**
     * Method which provide the action when the {@link Rect} added to the {@link BSImageView}
     *
     * @param imageView instance of the {@link BSImageView}
     * @param id        {@link String} value of the rect ID
     * @param rect      instance of the {@link Rect}
     */
    @Override
    public void baseImageViewRectAdded(@NonNull BSImageView imageView,
                                       @NonNull String id,
                                       @NonNull Rect rect) {

    }

    /**
     * Method which provide the action when the {@link Rect} clicked
     *
     * @param imageView instance of the {@link BSImageView}
     * @param id        {@link String} value of the rect ID
     * @param rect      instance of the {@link Rect}
     */
    @Override
    public void baseImageViewRectClicked(@NonNull BSImageView imageView,
                                         @NonNull String id,
                                         @NonNull Rect rect) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}
