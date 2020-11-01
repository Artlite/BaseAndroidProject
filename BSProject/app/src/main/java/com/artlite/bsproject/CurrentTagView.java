package com.artlite.bsproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.bslibrary.ui.view.BSTagsView;
import com.artlite.bslibrary.ui.view.BSView;

public final class CurrentTagView extends BSTagsView.BaseTagView {

    @FindViewBy(id = R.id.label_text)
    private BSTextView labelText;

    @FindViewBy(id = R.id.view_content)
    private View viewContent;

    @FindViewBy(id = R.id.view_delete)
    private View viewDelete;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public CurrentTagView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public CurrentTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public CurrentTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the {@link TextView}
     *
     * @return instance of the {@link TextView}
     */
    @NonNull
    @Override
    protected TextView getTagLabel() {
        return this.labelText;
    }

    /**
     * Method which provide the getting of the get delete {@link View}
     *
     * @return instance of the delete {@link View}
     */
    @NonNull
    @Override
    protected View getDeleteView() {
        return this.viewDelete;
    }

    /**
     * Method which provide the getting of the content {@link View}
     *
     * @return instance of the {@link View}
     */
    @Nullable
    @Override
    protected View getContentView() {
        return this.viewContent;
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_current_tag;
    }

    /**
     * Object class
     */
    public static class Object implements BSTagsView.TagInterface {

        private final int index;

        public Object(int index) {
            this.index = index;
        }

        /**
         * Method which provide the getting of the {@link String} value of the tag text
         *
         * @return {@link String} value of the tag text
         */
        @SuppressLint("DefaultLocale")
        @Nullable
        @Override
        public String getTagText() {
            return String.format("%s%d", "Index: ", index);
        }

        /**
         * Method which provide the checking if the tag is removable
         *
         * @return {@link Boolean} value if the tag removable
         */
        @Override
        public boolean isTagRemovable() {
            return (index % 2) == 0;
        }
    }

}
