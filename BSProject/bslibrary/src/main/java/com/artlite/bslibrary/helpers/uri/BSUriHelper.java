package com.artlite.bslibrary.helpers.uri;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * CLass which provide the functional for {@link Uri}
 */

public final class BSUriHelper extends BSBaseHelper {

    /**
     * Method which provide the getting of the image from the URI
     *
     * @param uri current URI
     * @return current instance of the Bitmap
     * @throws IOException
     */
    public static Bitmap getBitmapFromUri(Context context, Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /**
     * Method which provide the getting of the real file path from the URI
     *
     * @param contentUri current URI
     * @return the file path String value
     */
    public static String getPathFromURI(Activity activity, Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
