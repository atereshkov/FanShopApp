package com.github.handioq.fanshop.categories;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

import butterknife.BindView;

public class CategoriesActivity extends BaseNavActivity {

    private static final String TAG = "CategoriesActivity";
    private static final String CATEGORIES_FRAGMENT_TAG = "categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        if (getSupportFragmentManager().findFragmentByTag(CATEGORIES_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new CategoriesFragment(), CATEGORIES_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new CategoriesFragment");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
