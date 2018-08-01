package com.artlite.bslibrary.ui.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;

import com.artlite.bslibrary.R;
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
    private PDFView viewPDF;

    /**
     * Instance of the {@link Uri}
     */
    private Uri uri;

    /**
     * Instance of the file
     */
    private File file;

    /**
     * Instance of the {@link OnConfigurationCallback}
     */
    private OnConfigurationCallback callback;

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
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

    /**
     * Method which provide to download and show pdf from {@link String} asset
     *
     * @param asset instance of the {@link String}
     */
    public void download(@Nullable final String asset) {
        final PDFView.Configurator configurator = this.viewPDF.fromAsset(asset);
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

    /**
     * Method which provide to download and show pdf from array of {@link Byte}
     *
     * @param bytes instance of the {@link Object}
     */
    public void download(@Nullable final byte[] bytes) {
        final PDFView.Configurator configurator = this.viewPDF.fromBytes(bytes);
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

    /**
     * Method which provide to download and show pdf from {@link File}
     *
     * @param file instance of the {@link Object}
     */
    public void download(@Nullable final File file) {
        final PDFView.Configurator configurator = this.viewPDF.fromFile(file);
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

    /**
     * Method which provide to download and show pdf from {@link InputStream}
     *
     * @param stream instance of the {@link Object}
     */
    public void download(@Nullable final InputStream stream) {
        final PDFView.Configurator configurator = this.viewPDF.fromStream(stream);
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

    /**
     * Method which provide to download and show pdf from {@link DocumentSource}
     *
     * @param source instance of the {@link Object}
     */
    public void download(@Nullable final DocumentSource source) {
        final PDFView.Configurator configurator = this.viewPDF.fromSource(source);
        if (this.callback != null) {
            this.callback.pdfViewConfigure(configurator);
        }
        configurator.load();
        if (this.callback != null) {
            this.callback.pdfViewLoadComplete(this);
        }
    }

}
