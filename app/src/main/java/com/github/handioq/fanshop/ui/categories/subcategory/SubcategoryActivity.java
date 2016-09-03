package com.github.handioq.fanshop.ui.categories.subcategory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

public class SubcategoryActivity extends BaseNavActivity {

    private static final String TAG = "SubcategoryActivity";
    private static final String SUBCATEGORY_FRAGMENT_TAG = "subcategory";
    private static final String CATEGORY_KEY = "id";

    public static Intent makeIntent(Context context, int id){
        Intent intent = new Intent(context, SubcategoryActivity.class);
        intent.putExtra(CATEGORY_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        int selectedItem = getIntent().getExtras().getInt(CATEGORY_KEY);

        Bundle bundle = new Bundle();
        bundle.putInt(CATEGORY_KEY, selectedItem);

        if (getSupportFragmentManager().findFragmentByTag(SUBCATEGORY_FRAGMENT_TAG) == null) {
            SubcategoryFragment subcategoryFragment = new SubcategoryFragment();
            subcategoryFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, subcategoryFragment, SUBCATEGORY_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new SubcategoryFragment");
        }
    }

}
