package com.github.handioq.fanshop.account;

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
    private static final String ACCOUNT_FRAGMENT_TAG = "account";

    @BindView(R.id.fab_account)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (getSupportFragmentManager().findFragmentByTag(ACCOUNT_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new AccountFragment(), ACCOUNT_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new AccountFragment");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
