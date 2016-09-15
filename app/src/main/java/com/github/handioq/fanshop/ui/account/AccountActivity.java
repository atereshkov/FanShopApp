package com.github.handioq.fanshop.ui.account;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

import butterknife.BindView;

public class AccountActivity extends BaseNavActivity {

    private static final String TAG = "AccountActivity";
    private static final String ORDERS_FRAGMENT_TAG = "orders";
    private static final String USER_FRAGMENT_TAG = "user";

    @BindView(R.id.fab_account)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (getSupportFragmentManager().findFragmentByTag(ORDERS_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_orders, new OrdersFragment(), ORDERS_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new OrdersFragment");
        }

        if (getSupportFragmentManager().findFragmentByTag(USER_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_user, new UserFragment(), USER_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new UserFragment");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.edit_user_info_not_impl, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
