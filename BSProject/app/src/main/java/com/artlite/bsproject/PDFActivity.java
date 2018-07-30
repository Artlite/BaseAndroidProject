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

public class PDFActivity extends BSActivity {

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
                null,
                new BSPDFView.OnPdfCallback() {
                    @Override
                    public void pdfViewDownloadComplete(@NonNull BSPDFView view,
                                                        @Nullable String url,
                                                        @Nullable Uri pdfUri) {

                    }
                });
    }
}
