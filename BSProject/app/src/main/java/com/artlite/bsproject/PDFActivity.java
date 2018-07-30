package com.artlite.bsproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.artlite.bslibrary.annotations.FindViewBy;

import android.os.Bundle;

import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSPDFView;
import com.github.barteksc.pdfviewer.PDFView;

public class PDFActivity extends BSActivity implements BSPDFView.OnConfigurationCallback {

    /**
     * Instance of the {@link BSPDFView}
     */
    @FindViewBy(id = R.id.view_pdf)
    private BSPDFView view;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
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
        this.view.configure(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0b1010);
            return;
        }
        view.download("http://www.pdf995.com/samples/pdf.pdf",
                null, true);
    }

    /**
     * Method which provide the configuring the {@link BSPDFView}
     *
     * @param configurator instance of the {@link PDFView.Configurator}
     */
    @Override
    public void pdfViewConfigure(@NonNull PDFView.Configurator configurator) {
        configurator.enableAntialiasing(true);
    }

    /**
     * Method which provide the complete of the {@link BSPDFView} loading
     *
     * @param view instance of the {@link BSPDFView}
     */
    @Override
    public void pdfViewLoadComplete(@NonNull BSPDFView view) {

    }
}