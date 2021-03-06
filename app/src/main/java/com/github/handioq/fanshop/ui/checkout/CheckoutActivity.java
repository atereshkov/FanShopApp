package com.github.handioq.fanshop.ui.checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;
import com.github.handioq.fanshop.model.dto.PassOrderDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

import butterknife.BindView;

public class CheckoutActivity extends BaseNavActivity {

    private static final String TAG = "CheckoutActivity";
    private static final String FRAGMENT_TAG = "checkout";
    private static final String ORDER_KEY = "order";

    public static Intent makeIntent(Context context, PassOrderDTO passOrder){
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.putExtra(ORDER_KEY, passOrder);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        PassOrderDTO passOrder = (PassOrderDTO) getIntent().getSerializableExtra(ORDER_KEY);

        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, CheckoutFragment.newInstance(passOrder), FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new CheckoutFragment");
        }
    }

}
