package com.artlite.bslibrary.ui.view;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.managers.BSRandomManager;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.wasabeef.glide.transformations.internal.Utils;

/**
 * View which provide the show the pdf from sources
 */
public class BSPDFView extends BSView {

    /**
     * Callback which provide the callback with the pdf downloading
     */
    public interface OnPdfCallback {

        /**
         * Method which provide the action when the pdf download was completed
         *
         * @param view   instance of the {@link BSPDFView}
         * @param url    {@link String} value of the url
         * @param pdfUri instance of the {@link Uri}
         */
        void pdfViewDownloadComplete(@NonNull BSPDFView view,
                                     @Nullable String url,
                                     @Nullable Uri pdfUri);
    }

    /**
     * {@link String} constants of the TAG
     */
    protected static final String TAG = BSPDFView.class.getSimpleName();

    /**
     * Instance of the {@link PDFView}
     */
    private PDFView viewPDF;

    /**
     * Instance of the {@link Uri}
     */
    private Uri uri;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSPDFView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSPDFView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSPDFView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.bs_view_pdf;
    }

    /**
     * Method which provide interface linking
     */
    @Override
    protected void onLinkInterface() {
        this.viewPDF = findViewById(R.id.bs_library_view_pdf);
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {

    }

    /**
     * Method which provide the getting instance of the {@link PDFView}
     *
     * @return instance of the {@link PDFView}
     */
    @NonNull
    public final PDFView getPdfView() {
        return this.viewPDF;
    }

    /**
     * Method which provide the getting of the {@link Uri}
     *
     * @return instance of the {@link Uri}
     */
    @Nullable
    public Uri getUri() {
        return uri;
    }

    /**
     * Method which provide the download the PDF from {@link String} value of the url
     *
     * @param url {@link String} value of the url
     */
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void download(@Nullable final String url,
                         @Nullable String fileName,
                         @Nullable OnPdfCallback callback) {
        new PDFDownloadTask(this, fileName, url, callback).execute();
    }

    /**
     * Task which provide the downloading of the PDF
     */
    protected static class PDFDownloadTask extends AsyncTask<Void, Void, Uri> {

        /**
         * {@link String} value of the url
         */
        private final String url;

        /**
         * Instance of the {@link OnPdfCallback}
         */
        private final OnPdfCallback callback;

        /**
         * Instance of the {@link WeakReference}
         */
        private final WeakReference<BSPDFView> viewReference;

        /**
         * Instance of the {@link File}
         */
        private File pdfStorage = null;

        /**
         * Instance of the {@link File}
         */
        private File pdfFile = null;

        /**
         * {@link String} value of the file name
         */
        private final String fileName;

        /**
         * Constructor which provide the create of the {@link PDFDownloadTask}
         *
         * @param view     instance of the {@link BSPDFView}
         * @param url      {@link String} value of the url
         * @param callback instance of the {@link OnPdfCallback}
         */
        public PDFDownloadTask(@Nullable BSPDFView view,
                               @Nullable String fileName,
                               @Nullable String url,
                               @Nullable OnPdfCallback callback) {
            this.url = url;
            this.callback = callback;
            this.viewReference = new WeakReference<>(view);
            this.fileName = (fileName == null)
                    ? BSRandomManager.generateString(30, true) : fileName;
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
                final URL urlObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                //If Connection response is not OK then show Logs
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                }
                pdfStorage = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS).toString() + "/pdf");
                if (!pdfStorage.exists()) {
                    pdfStorage.mkdir();
                    Log.d(TAG, "doInBackground: Pdf storage was created");
                } else {
                    Log.d(TAG, "doInBackground: Pdf storage was created previously");
                }
                pdfFile = new File(pdfStorage, String.format("%s.pdf", fileName));
                if (!pdfFile.exists()) {
                    pdfFile.createNewFile();
                    Log.d(TAG, "doInBackground: File was created");
                } else {
                    Log.d(TAG, "doInBackground: File was created was previously");
                }
                FileOutputStream outputStream = new FileOutputStream(pdfFile);
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len1);
                }
                outputStream.close();
                inputStream.close();
            } catch (Exception ex) {
                pdfFile = null;
                Log.e(TAG, "download: ", ex);
                return null;
            }
            return Uri.fromFile(pdfFile);
        }

        /**
         * Method which provide the on post execute functional
         *
         * @param uri instance of the {@link Uri}
         */
        @Override
        protected void onPostExecute(Uri uri) {
            final BSPDFView view = (this.viewReference == null) ? null : this.viewReference.get();
            if ((view != null) && (this.callback != null)) {
                view.uri = uri;
                view.viewPDF.fromUri(uri)
                        .enableAntialiasing(true)
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .load();
                this.callback.pdfViewDownloadComplete(view, url, uri);
            }
            super.onPostExecute(uri);
        }
    }
}
