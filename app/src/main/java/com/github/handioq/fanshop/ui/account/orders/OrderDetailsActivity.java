package com.github.handioq.fanshop.ui.account.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

public class OrderDetailsActivity extends BaseNavActivity {

    private static final String TAG = "OrderDetailsActivity";
    private static final String ORDER_DETAILS_FRAGMENT_TAG = "order_details";
    private static final String ORDER_ID_KEY = "order_id";

    public static Intent makeIntent(Context context, int id){
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(ORDER_ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        int orderId = getIntent().getExtras().getInt(ORDER_ID_KEY);

        if (getSupportFragmentManager().findFragmentByTag(ORDER_DETAILS_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, OrderDetailsFragment.newInstance(orderId), ORDER_DETAILS_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new OrderDetailsFragment");
        }
    }

}
