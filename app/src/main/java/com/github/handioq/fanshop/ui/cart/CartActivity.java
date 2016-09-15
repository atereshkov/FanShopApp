package com.github.handioq.fanshop.ui.cart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;
import com.github.handioq.fanshop.ui.checkout.CheckoutEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class CartActivity extends BaseNavActivity {

    private static final String TAG = "CartActivity";
    private static final String CART_FRAGMENT_TAG = "cart";

    @BindView(R.id.fab_cart)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (getSupportFragmentManager().findFragmentByTag(CART_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new CartFragment(), CART_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new CartFragment");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(CartActivity.this, "Not implemented", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new CheckoutEvent());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
