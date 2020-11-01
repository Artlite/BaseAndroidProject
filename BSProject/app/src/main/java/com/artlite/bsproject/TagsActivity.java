package com.artlite.bsproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSTagsView;

public class TagsActivity extends BSActivity implements BSTagsView.OnTagsViewCallback<CurrentTagView.Object, CurrentTagView> {

    @FindViewBy(id = R.id.view_tags)
    private BSTagsView<CurrentTagView.Object, CurrentTagView> tagsView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_tags_view;
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
        this.tagsView.configure(this);
        for (int i = 0; i < 100; i++) {
            this.tagsView.add(new CurrentTagView.Object(i));
        }
    }

    /**
     * Method which provide the checking if need back button into {@link ActionBar}
     *
     * @return checking if need back button into {@link ActionBar}
     */
    @Override
    protected boolean isNeedBackButton() {
        return true;
    }

    /**
     * Method which provide the {@link BSTagsView} item clicked
     *
     * @param view   instance of the {@link BSTagsView.BaseTagView}
     * @param object instance of the {@link Object}
     */
    @Override
    public void tagsViewOnClickItem(@NonNull BSTagsView.BaseTagView view,
                                    @NonNull CurrentTagView.Object object) {

    }

    /**
     * Method which provide the {@link BSTagsView} item clicked
     *
     * @param view   instance of the {@link BSTagsView.BaseTagView}
     * @param object instance of the {@link Object}
     */
    @Override
    public void tagsViewOnLongClickItem(@NonNull BSTagsView.BaseTagView view,
                                        @NonNull CurrentTagView.Object object) {

    }

    /**
     * Method which provide the {@link BSTagsView} item deleting
     *
     * @param object instance of the {@link Object}
     */
    @Override
    public void tagsViewOnDeleteItem(@NonNull CurrentTagView.Object object) {

    }

    /**
     * Method which provide the create the tags view externally
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BSTagsView.BaseTagView}
     */
    @NonNull
    @Override
    public CurrentTagView tagsViewCreateView(@NonNull Context context) {
        return new CurrentTagView(context);
    }
}
