package com.artlite.bslibrary.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artlite.bslibrary.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class which provide the tags view functional
 */
public final class BSTagsView<T extends BSTagsView.TagInterface, K extends BSTagsView.BaseTagView>
        extends BSView {

    /**
     * {@link String} constant of the tag
     */
    private static final String TAG = BSTagsView.class.getSimpleName();

    /**
     * Instance of the {@link FlexboxLayout}
     */
    private FlexboxLayout flexboxLayout;

    /**
     * Instance of the {@link OnTagViewCallback}
     */
    private OnTagViewCallback tagViewCallback;

    /**
     * Instance of the {@link OnTagsViewCallback}
     */
    private OnTagsViewCallback<T, K> callback;

    /**
     * Instance of the tags {@link HashSet}
     */
    private Set<String> tagSet = new HashSet<>();

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public BSTagsView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public BSTagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public BSTagsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.bs_tags_view;
    }

    /**
     * Method which provide interface linking
     */
    @Override
    protected void onLinkInterface() {
        this.flexboxLayout = this.findViewById(R.id.grid_layout);
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
    }

    /**
     * Method which provide the configuring of the {@link BaseTagView}
     *
     * @param callback instance of the {@link OnTagsViewCallback}
     */
    public void configure(@Nullable OnTagsViewCallback callback) {
        this.callback = callback;
    }

    /**
     * Method which provide the adding of the {@link BaseTagView}
     *
     * @param object instance of the {@link TagInterface}
     * @return instance of the {@link BaseTagView}
     */
    @NonNull
    public <T extends TagInterface> void set(@Nullable T object) {
        this.flexboxLayout.removeAllViews();
        this.tagSet.clear();
        this.add(object);
    }

    /**
     * Method which provide the adding of the {@link BaseTagView}
     *
     * @param objects {@link List} of the instance of the {@link TagInterface}
     * @return instance of the {@link BaseTagView}
     */
    @NonNull
    public <T extends TagInterface> void set(@Nullable List<T> objects) {
        this.flexboxLayout.removeAllViews();
        this.tagSet.clear();
        this.add(objects);
    }

    /**
     * Method which provide the adding of the {@link BaseTagView}
     *
     * @param object instance of the {@link TagInterface}
     * @return instance of the {@link BaseTagView}
     */
    @NonNull
    public <T extends TagInterface> void add(@Nullable T object) {
        if (object != null) {
            this.add(this.flexboxLayout, object);
        }
    }

    /**
     * Method which provide the adding of the {@link BaseTagView}
     *
     * @param objects {@link List} of the instance of the {@link TagInterface}
     * @return instance of the {@link BaseTagView}
     */
    @NonNull
    public <T extends TagInterface> void add(@Nullable List<T> objects) {
        if (objects != null) {
            for (TagInterface object : objects) {
                this.add(object);
            }
        }
    }

    /**
     * Method which provide the checking if the tag is already containing
     *
     * @param tag {@link String} value of the tag
     * @return {@link Boolean} value if the tag is exists
     */
    public boolean containsTag(@Nullable String tag) {
        if (tag != null) {
            if (this.tagSet.contains(tag)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Method which provide the adding of the {@link BaseTagView}
     *
     * @param layout instance of the {@link FlexboxLayout}
     * @param object instance of the {@link TagInterface}
     * @return instance of the {@link BaseTagView}
     */
    @Nullable
    protected BaseTagView add(@NonNull FlexboxLayout layout,
                              @Nullable TagInterface object) {
        if ((this.callback == null) || (getContext() == null)) {
            return null;
        }
        final BaseTagView tagView = this.callback.tagsViewCreateView(getContext());
        if (layout == null) {
            return tagView;
        }
        if ((object != null) && (object.getTagText() != null)) {
            this.tagSet.add(object.getTagText());
        }
        tagView.configure(this.getTagViewCallback(), object);
        layout.addView(tagView);
        return tagView;
    }

    /**
     * Method which provide the getting callback
     *
     * @return instance of the {@link OnTagViewCallback}
     */
    @NonNull
    protected OnTagViewCallback getTagViewCallback() {
        if (this.tagViewCallback == null) {
            this.tagViewCallback = new OnTagViewCallback() {
                @Override
                public void tagViewOnClick(@NonNull BaseTagView view) {
                    onViewClicked(view);
                }

                @Override
                public void tagViewOnLongClick(@NonNull BaseTagView view) {
                    onViewLongClicked(view);
                }

                @Override
                public void tagViewOnDelete(@NonNull BaseTagView view) {
                    onViewRemoved(view);
                }
            };
        }
        return this.tagViewCallback;
    }

    /**
     * Method which provide the {@link BaseTagView} clicked
     *
     * @param view instance of the {@link BaseTagView}
     */
    protected void onViewRemoved(@NonNull BaseTagView view) {
        try {
            this.flexboxLayout.removeView(view);
            T object = (T) view.getObject();
            this.callback.tagsViewOnDeleteItem(object);
            this.tagSet.remove(object.getTagText());
        } catch (Exception ex) {
            Log.e(TAG, "onViewClicked: ", ex);
        }
    }

    /**
     * Method which provide the {@link BaseTagView} clicked
     *
     * @param view instance of the {@link BaseTagView}
     */
    protected void onViewClicked(@NonNull BaseTagView view) {
        try {
            if (view.getObject() != null) {
                this.callback.tagsViewOnClickItem(view, (T) view.getObject());
            }
        } catch (Exception ex) {
            Log.e(TAG, "onViewClicked: ", ex);
        }
    }

    /**
     * Method which provide the {@link BaseTagView} clicked
     *
     * @param view instance of the {@link BaseTagView}
     */
    protected void onViewLongClicked(@NonNull BaseTagView view) {
        try {
            if (view.getObject() != null) {
                this.callback.tagsViewOnLongClickItem(view, (T) view.getObject());
            }
        } catch (Exception ex) {
            Log.e(TAG, "onViewClicked: ", ex);
        }
    }

    // TAG VIEW

    /**
     * Class which provide the base tag view
     */
    public static abstract class BaseTagView extends BSView {

        /**
         * Instance of the {@link TagInterface}
         */
        protected TagInterface object;

        /**
         * Instance of the {@link OnTagViewCallback}
         */
        protected OnTagViewCallback callback;

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context instance of {@link Context}
         */
        public BaseTagView(Context context) {
            super(context);
        }

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context instance of {@link Context}
         * @param attrs   instance of {@link AttributeSet}
         */
        public BaseTagView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /**
         * Constructor which provide the create {@link BSView} from
         *
         * @param context      instance of {@link Context}
         * @param attrs        instance of {@link AttributeSet}
         * @param defStyleAttr attribute style
         */
        public BaseTagView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        /**
         * Method which provide interface linking
         */
        @Override
        protected void onLinkInterface() {

        }

        /**
         * Method which provide the creating of the {@link View}
         */
        @Override
        protected void onCreateView() {
            this.setOnClickListeners(getDeleteView(), getContentView());
            if (getContentView() != null) {
                getContentView().setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onLongClickView();
                        return true;
                    }
                });
            }
        }

        /**
         * Method which provide the on click functional
         *
         * @param view instance of {@link View}
         */
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == getDeleteView().getId()) {
                onDeleteView();
            } else if (id == getContentView().getId()) {
                onClickView();
            }
        }

        /**
         * Method which provide the configuring of the {@link BaseTagView}
         *
         * @param callback instance of the {@link OnTagViewCallback}
         */
        public void configure(@Nullable OnTagViewCallback callback,
                              @Nullable TagInterface object) {
            this.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            this.callback = callback;
            this.setObject(object);
        }

        /**
         * Method which provide the set {@link String} value of the text
         *
         * @param text {@link String} value of the text
         */
        public void setText(@Nullable String text) {
            this.getTagLabel().setText(text);
        }

        /**
         * Method which provide the setting of the {@link TagInterface}
         *
         * @param object instance of the {@link TagInterface}
         */
        public void setObject(TagInterface object) {
            this.object = object;
            if (object != null) {
                this.getDeleteView().setVisibility((object.isTagRemovable()) ? VISIBLE : GONE);
                this.setText(object.getTagText());
            }
        }

        /**
         * Method which provide the getting {@link TagInterface}
         *
         * @return instance of the {@link TagInterface}
         */
        @Nullable
        public TagInterface getObject() {
            return object;
        }

        /**
         * Method which provide the getting of the {@link TextView}
         *
         * @return instance of the {@link TextView}
         */
        @NonNull
        protected abstract TextView getTagLabel();

        /**
         * Method which provide the getting of the get delete {@link View}
         *
         * @return instance of the delete {@link View}
         */
        @NonNull
        protected abstract View getDeleteView();

        /**
         * Method which provide the getting of the content {@link View}
         *
         * @return instance of the {@link View}
         */
        @Nullable
        protected abstract View getContentView();

        /**
         * Method which provide the on delete view
         */
        protected final void onDeleteView() {
            if (callback != null) {
                callback.tagViewOnDelete(this);
            }
        }

        /**
         * Method which provide the on delete view
         */
        protected final void onClickView() {
            if (callback != null) {
                callback.tagViewOnClick(this);
            }
        }

        /**
         * Method which provide the on delete view
         */
        protected final void onLongClickView() {
            if (callback != null) {
                callback.tagViewOnLongClick(BaseTagView.this);
            }
        }
    }

    // CALLBACKS

    /**
     * Interface which provide the callback for the {@link BaseTagView}
     */
    protected interface OnTagViewCallback {
        /**
         * Method which provide the {@link BaseTagView} click
         *
         * @param view instance of the {@link BaseTagView}
         */
        void tagViewOnClick(@NonNull BaseTagView view);

        /**
         * Method which provide the {@link BaseTagView} click
         *
         * @param view instance of the {@link BaseTagView}
         */
        void tagViewOnLongClick(@NonNull BaseTagView view);

        /**
         * Method which provide the {@link BaseTagView} click
         *
         * @param view instance of the {@link BaseTagView}
         */
        void tagViewOnDelete(@NonNull BaseTagView view);
    }

    /**
     * Interface which provide the callback for the {@link BSTagsView}
     */
    public interface OnTagsViewCallback<T extends TagInterface, K extends BaseTagView> {

        /**
         * Method which provide the {@link BSTagsView} item clicked
         *
         * @param view   instance of the {@link BaseTagView}
         * @param object instance of the {@link Object}
         */
        void tagsViewOnClickItem(@NonNull final BaseTagView view,
                                 @NonNull final T object);

        /**
         * Method which provide the {@link BSTagsView} item clicked
         *
         * @param view   instance of the {@link BaseTagView}
         * @param object instance of the {@link Object}
         */
        void tagsViewOnLongClickItem(@NonNull final BaseTagView view,
                                     @NonNull final T object);

        /**
         * Method which provide the {@link BSTagsView} item deleting
         *
         * @param object instance of the {@link Object}
         */
        void tagsViewOnDeleteItem(@NonNull final T object);

        /**
         * Method which provide the create the tags view externally
         *
         * @param context instance of {@link Context}
         * @return instance of the {@link BaseTagView}
         */
        @NonNull
        K tagsViewCreateView(@NonNull final Context context);
    }

    // INTERFACES

    /**
     * Interface which provide the tag functionality
     */
    public interface TagInterface {

        /**
         * Method which provide the getting of the {@link String} value of the tag text
         *
         * @return {@link String} value of the tag text
         */
        @Nullable
        String getTagText();

        /**
         * Method which provide the checking if the tag is removable
         *
         * @return {@link Boolean} value if the tag removable
         */
        boolean isTagRemovable();

    }

}
