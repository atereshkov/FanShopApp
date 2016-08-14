package com.github.handioq.fanshop.catalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseNavActivity;
import com.github.handioq.fanshop.cart.CartActivity;
import com.github.handioq.fanshop.login.LoginActivity;

import butterknife.BindView;

public class CatalogActivity extends BaseNavActivity {

    private static final String TAG = "CatalogActivity";
    private static final String CATALOG_FRAGMENT_TAG = "catalog";

    @BindView(R.id.fab_catalog)
    FloatingActionButton fab;

    private Menu optionsMenu;
    private String category;

    public static Intent makeIntent(Context context, String category){
        Intent intent = new Intent(context, CatalogActivity.class);
        intent.putExtra("category", category);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Log.i(TAG, "onCreate");

        if (getIntent().hasExtra("category")) {
            category = getIntent().getExtras().getString("category");
            setTitle(category);
        }

        Bundle bundle = new Bundle();
        bundle.putString("category", category);

        if (getSupportFragmentManager().findFragmentByTag(CATALOG_FRAGMENT_TAG) == null) {
            CatalogFragment catalogFragment = new CatalogFragment();
            catalogFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, catalogFragment, CATALOG_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new CatalogFragment");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
                startActivity(intent);
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
        //getMenuInflater().inflate(R.menu.catalog, menu);

        this.optionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.catalog, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}