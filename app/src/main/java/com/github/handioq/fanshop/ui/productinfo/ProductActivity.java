package com.github.handioq.fanshop.ui.productinfo;

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

public class ProductActivity extends BaseNavActivity {

    private static final String TAG = "ProductActivity";
    private static final String PRODUCT_FRAGMENT_TAG = "product_info";
    private static final String ID_KEY = "id";

    public static Intent makeIntent(Context context, int id){
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        int selectedItem = getIntent().getExtras().getInt(ID_KEY);

        if (getSupportFragmentManager().findFragmentByTag(PRODUCT_FRAGMENT_TAG) == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, ProductFragment.newInstance(selectedItem), PRODUCT_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new ProductFragment");
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

        return super.onOptionsItemSelected(item);
    }
}
