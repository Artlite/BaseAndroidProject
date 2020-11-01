package com.artlite.bslibrary.ui.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.annotations.Info;
import com.artlite.bslibrary.annotations.Warning;
import com.artlite.bslibrary.tasks.BSDownloadTask;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.source.DocumentSource;

import java.io.File;
import java.io.InputStream;

/**
 * View which provide the show the pdf from sources
 */
public class BSPDFView extends BSView {

    /**
     * Callback which provide the callback with the pdf downloading
     */
    public interface OnConfigurationCallback {

        /**
         * Method which provide the configuring the {@link BSPDFView}
         *
         * @param configurator instance of the {@link PDFView.Configurator}
         */
        void pdfViewConfigure(@NonNull final PDFView.Configurator configurator);

        /**
         * Method which provide the complete of the {@link BSPDFView} loading
         *
         * @param view instance of the {@link BSPDFView}
         */
        void pdfViewLoadComplete(@NonNull final BSPDFView view);

    }

    /**
     * {@link String} constants of the TAG
     */
    protected static final String TAG = BSPDFView.class.getSimpleName();

    /**
     * Instance of the {@link PDFView}
     */
    protected PDFView viewPDF;

    /**
     * Instance of the {@link Uri}
     */
    protected Uri uri;

    /**
     * Instance of the file
     */
    protected File file;

    /**
     * Instance of the {@link OnConfigurationCallback}
     */
    protected OnConfigurationCallback callback;

    /**
     * {@link Float} value of the min zoom
     */
    protected float minZoom = 0f;

    /**
     * {@link Float} value of the mid zoom
     */
    protected float midZoom = 0f;

    /**
     * {@link Float} value of the max zoom
     */
    protected float maxZoom = 0f;

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
     * Method which provide the configuring of the {@link BSPDFView}
     *
     * @param callback instance of the {@link OnConfigurationCallback}
     */
    public final void configure(@Nullable OnConfigurationCallback callback) {
        this.callback = callback;
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
     * Method which provide the getting of the instance of the file
     *
     * @return instance of the {@link File}
     */
    public File getFile() {
        return file;
    }

    /**
     * Method which provide the getting of the instance of the {@link PDFView}
     *
     * @return instance of the {@link PDFView}
     */
    @NonNull
    public PDFView getPdfView() {
        return viewPDF;
    }

    /**
     * Method which provide the download the PDF from {@link String} value of the url
     *
     * @param url {@link String} value of the url
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void download(@Nullable final String url,
                         @Nullable final String fileName,
                         boolean forceReload) {
        new BSDownloadTask(getContext(), url, "pdf", fileName,
                "pdf", forceReload, new BSDownloadTask.OnDownloadCallback() {
            @Override
            public void downloadTaskPreExecute(@NonNull Context context,
                                               @NonNull BSDownloadTask task,
                                               @Nullable String folderName,
                                               @NonNull String fileName,
                                               @NonNull String fileExtension) {

            }

            @Override
            public void downloadTaskFinished(@NonNull Context context,
                                             @NonNull BSDownloadTask task,
                                             @Nullable String folderName,
                                             @NonNull String fileName,
                                             @NonNull String fileExtension,
                                             @Nullable Uri uri,
                                             @Nullable File file) {
                BSPDFView.this.file = file;
                download(uri);
            }
        }).execute();
    }

    /**
     * Method which provide to download and show pdf from {@link Uri}
     *
     * @param uri instance of the {@link Uri}
     */
    public void download(@Nullable final Uri uri) {
        this.uri = uri;
        final PDFView.Configurator configurator = this.viewPDF.fromUri(uri);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide to download and show pdf from {@link String} asset
     *
     * @param asset instance of the {@link String}
     */
    public void download(@Nullable final String asset) {
        final PDFView.Configurator configurator = this.viewPDF.fromAsset(asset);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide to download and show pdf from array of {@link Byte}
     *
     * @param bytes instance of the {@link Object}
     */
    public void download(@Nullable final byte[] bytes) {
        final PDFView.Configurator configurator = this.viewPDF.fromBytes(bytes);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide to download and show pdf from {@link File}
     *
     * @param file instance of the {@link Object}
     */
    public void download(@Nullable final File file) {
        final PDFView.Configurator configurator = this.viewPDF.fromFile(file);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide to download and show pdf from {@link InputStream}
     *
     * @param stream instance of the {@link Object}
     */
    public void download(@Nullable final InputStream stream) {
        final PDFView.Configurator configurator = this.viewPDF.fromStream(stream);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide to download and show pdf from {@link DocumentSource}
     *
     * @param source instance of the {@link Object}
     */
    public void download(@Nullable final DocumentSource source) {
        final PDFView.Configurator configurator = this.viewPDF.fromSource(source);
        this.preconfigure(configurator);
        this.onLoadCompleted();
    }

    /**
     * Method which provide the disable zoom
     */
    @Info(massage = "Could be better to use in the OnConfigurationCallback -> pdfViewLoadComplete")
    public final void disableZoom() {
        if (this.viewPDF != null) {
            this.minZoom = this.viewPDF.getMinZoom();
            this.midZoom = this.viewPDF.getMidZoom();
            this.maxZoom = this.viewPDF.getMaxZoom();
            this.viewPDF.setMinZoom(1f);
            this.viewPDF.setMidZoom(1f);
            this.viewPDF.setMaxZoom(1f);
        }
    }

    /**
     * Method which provide the recover zoom
     */
    @Info(massage = "Could be better to use in the OnConfigurationCallback -> pdfViewLoadComplete")
    public final void recoverZoom() {
        if (this.viewPDF != null) {
            if ((this.minZoom == 0f)
                    || (this.midZoom == 0f)
                    || (this.maxZoom == 0f)) {
                this.disableZoom();
            }
            this.viewPDF.setMinZoom(this.minZoom);
            this.viewPDF.setMidZoom(this.midZoom);
            this.viewPDF.setMaxZoom(this.maxZoom);
        }
    }

    /**
     * Method which provide the preconfigure of the {@link BSPDFView}
     *
     * @param configurator instance of the {@link PDFView.Configurator}
     */
    protected void preconfigure(@NonNull PDFView.Configurator configurator) {
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
    }

    /**
     * Method which provide the load completed
     */
    protected void onLoadCompleted() {
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

}
