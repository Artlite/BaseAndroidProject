package com.artlite.bslibrary.transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.artlite.bslibrary.managers.BSContextManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.Target;

/**
 * Class which provide the circle image transformation
 */
public final class BSGlideCircleTransform extends BitmapTransformation {

    /**
     * Constructor which provide to create the {@link BSGlideCircleTransform}
     */
    public BSGlideCircleTransform() {
        super(BSContextManager.getApplicationContext());
    }

    /**
     * Constructor which provide to create the {@link BSGlideCircleTransform}
     *
     * @param context instance of the {@link Context}
     */
    public BSGlideCircleTransform(Context context) {
        super(context);
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
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    /**
     * Method which provide the circle crop
     *
     * @param pool   A {@link BitmapPool} that can be used
     * @param source instance of the {@link Bitmap}
     * @return instance of the {@link Bitmap}
     */
    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
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
    @Override
    public String getId() {
        return getClass().getName();
    }
}
