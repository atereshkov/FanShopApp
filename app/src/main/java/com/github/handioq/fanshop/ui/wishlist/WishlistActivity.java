package com.github.handioq.fanshop.ui.wishlist;

import android.os.Bundle;
import android.util.Log;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

public class WishlistActivity extends BaseNavActivity {

    private static final String TAG = "WishlistActivity";
    private static final String WISHLIST_FRAGMENT_TAG = "wishlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        if (getSupportFragmentManager().findFragmentByTag(WISHLIST_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new WishlistFragment(), WISHLIST_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new WishlistFragment");
        }

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

}
