package com.artlite.bslibrary.transformation;

/**
 * Copyright (C) 2018 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.DimenRes;

import com.artlite.bslibrary.managers.BSContextManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.Target;

/**
 * Transformer which provide the round corners
 */
public class BSGlideCropSquareTransformation extends BitmapTransformation {

    /**
     * {@link Integer} value of the radius
     */
    private final int radius;

    /**
     * Constructor which provide to create the {@link BSGlideCircleTransform}
     */
    public BSGlideCropSquareTransformation(int radius) {
        this(BSContextManager.getApplicationContext(), radius);
    }

    /**
     * Constructor which provide to create the {@link BSGlideCircleTransform}
     *
     * @param context instance of the {@link Context}
     */
    public BSGlideCropSquareTransformation(Context context, @DimenRes int radius) {
        super(context);
        if (context != null) {
            this.radius = context.getResources().getDimensionPixelSize(radius);
        } else {
            this.radius = 0;
        }
    }

    /**
     * Transforms the given {@link Bitmap} based on the given dimensions and returns the transformed
     * result.
     * <p>
     * <p>
     * The provided Bitmap, toTransform, should not be recycled or returned to the pool. Glide will automatically
     * recycle and/or reuse toTransform if the transformation returns a different Bitmap. Similarly implementations
     * should never recycle or return Bitmaps that are returned as the result of this method. Recycling or returning
     * the provided and/or the returned Bitmap to the pool will lead to a variety of runtime exceptions and drawing
     * errors. See #408 for an example. If the implementation obtains and discards intermediate Bitmaps, they may
     * safely be returned to the BitmapPool and/or recycled.
     * </p>
     * <p>
     * <p>
     * outWidth and outHeight will never be {@link Target#SIZE_ORIGINAL}, this
     * class converts them to be the size of the Bitmap we're going to transform before calling this method.
     * </p>
     *
     * @param pool        A {@link BitmapPool} that can be used to obtain and
     *                    return intermediate {@link Bitmap}s used in this transformation. For every
     *                    {@link Bitmap} obtained from the pool during this transformation, a
     *                    {@link Bitmap} must also be returned.
     * @param toTransform The {@link Bitmap} to transform.
     * @param outWidth    The ideal width of the transformed bitmap (the transformed width does not need to match exactly).
     * @param outHeight   The ideal height of the transformed bitmap (the transformed heightdoes not need to match
     */
    @Override
    protected Bitmap transform(BitmapPool pool,
                               Bitmap toTransform,
                               int outWidth,
                               int outHeight) {
        return circleCrop(pool, toTransform);
    }

    /**
     * Method which provide the circle crop
     *
     * @param pool   A {@link BitmapPool} that can be used
     * @param source instance of the {@link Bitmap}
     * @return instance of the {@link Bitmap}
     */
    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source.getWidth() >= source.getHeight()) {
            source = Bitmap.createBitmap(
                    source,
                    source.getWidth() / 2 - source.getHeight() / 2,
                    0,
                    source.getHeight(),
                    source.getHeight()
            );
        } else {
            source = Bitmap.createBitmap(
                    source,
                    0,
                    source.getHeight() / 2 - source.getWidth() / 2,
                    source.getWidth(),
                    source.getWidth()
            );
        }
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(0, 0, source.getWidth() - 0,
                source.getHeight() - 0), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }

        return output;
    }

    /**
     * A method to get a unique identifier for this particular transformation that can be used as part of a cache key.
     * The fully qualified class name for this class is appropriate if written out, but getClass().getName() is not
     * because the name may be changed by proguard.
     * <p>
     * <p>
     * If this transformation does not affect the data that will be stored in cache, returning an empty string here
     * is acceptable.
     * </p>
     *
     * @return A string that uniquely identifies this transformation.
     */
    @SuppressLint("DefaultLocale")
    @Override
    public String getId() {
        return String.format("artlite: BSGlideCropSquareTransformation(%d)", this.radius);
    }
}
