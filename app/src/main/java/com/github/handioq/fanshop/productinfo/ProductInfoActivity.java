package com.github.handioq.fanshop.productinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;

public class ProductInfoActivity extends BaseNavActivity {

    private static final String TAG = "ProductInfoActivity";
    private static final String PRODUCT_FRAGMENT_TAG = "product_info";

    public static Intent makeIntent(Context context, int id){
        Intent intent = new Intent(context, ProductInfoActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        int selectedItem = getIntent().getExtras().getInt("id"); // TODO get item

        Bundle bundle = new Bundle();
        bundle.putInt("id", selectedItem);

        if (getSupportFragmentManager().findFragmentByTag(PRODUCT_FRAGMENT_TAG) == null) {
            ProductInfoFragment productInfoFragment = new ProductInfoFragment();
            productInfoFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, productInfoFragment, PRODUCT_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new ProductInfoFragment");
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
