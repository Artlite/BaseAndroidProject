package com.artlite.bslibrary.tasks;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.artlite.bslibrary.managers.BSRandomManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class which provide the download task
 */
public class BSDownloadTask extends AsyncTask<Void, Void, Uri> {

    /**
     * {@link String} constants of the tag
     */
    protected static final String TAG = BSDownloadTask.class.getSimpleName();

    /**
     * Interface which provide the download callback
     */
    public interface OnDownloadCallback {

        /**
         * Method which provide the pre executing of the downloading task
         *
         * @param context       instance of the {@link Context}
         * @param task          instance of the {@link BSDownloadTask}
         * @param folderName    {@link String} value of the folder name
         * @param fileName      {@link String} value of the file name
         * @param fileExtension {@link String} value of the file extension
         */
        void downloadTaskPreExecute(@NonNull Context context,
                                    @NonNull BSDownloadTask task,
                                    @Nullable String folderName,
                                    @NonNull String fileName,
                                    @NonNull String fileExtension);

        /**
         * Method which provide the pre executing of the downloading task
         *
         * @param context       instance of the {@link Context}
         * @param task          instance of the {@link BSDownloadTask}
         * @param folderName    {@link String} value of the folder name
         * @param fileName      {@link String} value of the file name
         * @param fileExtension {@link String} value of the file extension
         * @param uri           instance of the {@link Uri}
         */
        void downloadTaskFinished(@NonNull Context context,
                                  @NonNull BSDownloadTask task,
                                  @Nullable String folderName,
                                  @NonNull String fileName,
                                  @NonNull String fileExtension,
                                  @Nullable Uri uri);
    }

    /**
     * Instance of the {@link Context}
     */
    @SuppressLint("StaticFieldLeak")
    private final Context context;

    /**
     * {@link String} value of the url
     */
    private final String url;

    /**
     * {@link String} constants of the folder
     */
    private final String folderName;

    /**
     * {@link String} constants of the file name
     */
    private final String fileName;

    /**
     * {@link String} value of the file extension
     */
    private final String fileExtension;

    /**
     * Instance of the {@link OnDownloadCallback}
     */
    private final OnDownloadCallback callback;

    /**
     * Instance of the {@link File}
     */
    private File storage;

    /**
     * Instance of the file
     */
    private File file;

    /**
     * {@link Boolean} value of the force reload
     */
    private final boolean forceReload;

    /**
     * Constructor which provide the creating of the {@link BSDownloadTask}
     *
     * @param context       instance of the {@link Context}
     * @param folderName    {@link String} value of the folder name
     * @param fileName      {@link String} value of the file name
     * @param fileExtension {@link String} value of the file extension
     */
    @SuppressLint("DefaultLocale")
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public BSDownloadTask(@NonNull Context context,
                          @NonNull String url,
                          @Nullable String folderName,
                          @Nullable String fileName,
                          @NonNull String fileExtension,
                          boolean forceReload,
                          @Nullable OnDownloadCallback callback) {
        this.context = context;
        this.url = url;
        this.folderName = (folderName == null) ? "" : String.format("/%s", folderName);
        this.fileName = (fileName != null)
                ? fileName : String.format("file_%d", Math.abs(url.hashCode()));
        this.fileExtension = fileExtension;
        this.callback = callback;
        this.forceReload = forceReload;
    }

    /**
     * Method which provide the on pre executing functionality
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.callback != null) {
            this.callback.downloadTaskPreExecute(this.context,
                    this, this.folderName, this.fileName, this.fileExtension);
        }
    }

    /**
     * Method which provide the doing on background functional
     *
     * @param voids instance of the {@link Void}
     * @return instance of the {@link Uri}
     */
    @Override
    protected Uri doInBackground(Void... voids) {
        try {
            final URL urlObject = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //If Connection response is not OK then show Logs
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage());
            }
            storage = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS).toString() + this.folderName);
            if (!storage.exists()) {
                storage.mkdir();
                Log.d(TAG, "doInBackground: The file storage was created");
            } else {
                Log.d(TAG, "doInBackground: The file storage was created previously");
            }
            file = new File(storage, String.format("%s.%s", this.fileName, this.fileExtension));
            if ((file.exists()) && (!forceReload)) {
                Log.d(TAG, "doInBackground: File was created was previously and loaded");
                return Uri.fromFile(file);
            } else {
                file.createNewFile();
                Log.d(TAG, "doInBackground: File was created");
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len1);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception ex) {
            file = null;
            Log.e(TAG, "download: ", ex);
            return null;
        }
        return Uri.fromFile(file);
    }

    /**
     * Method which provide the on post execution functional
     *
     * @param uri instance of the {@link Uri}
     */
    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        if (this.callback != null) {
            this.callback.downloadTaskFinished(context, this,
                    folderName, fileName, fileExtension, uri);
        }
    }
}
