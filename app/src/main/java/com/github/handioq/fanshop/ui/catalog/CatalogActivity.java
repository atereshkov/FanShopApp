package com.github.handioq.fanshop.ui.catalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import com.github.handioq.fanshop.ui.cart.CartActivity;
import com.github.handioq.fanshop.ui.login.LoginActivity;
import com.github.handioq.fanshop.util.AuthPreferences;

import butterknife.BindView;

public class CatalogActivity extends BaseNavActivity {

    private static final String TAG = "CatalogActivity";
    private static final String CATALOG_FRAGMENT_TAG = "catalog";
    private static final String KEY_CATEGORY = "category";

    @BindView(R.id.fab_catalog)
    FloatingActionButton fab;

    private Menu optionsMenu;
    private long categoryId;

    public static Intent makeIntent(Context context, long categoryId){
        Intent intent = new Intent(context, CatalogActivity.class);
        intent.putExtra(KEY_CATEGORY, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Log.i(TAG, "onCreate");

        if (getIntent().hasExtra(KEY_CATEGORY)) {
            categoryId = getIntent().getExtras().getLong(KEY_CATEGORY);
            //setTitle(category);
        }

        if (getSupportFragmentManager().findFragmentByTag(CATALOG_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, CatalogFragment.newInstance(categoryId), CATALOG_FRAGMENT_TAG)
                    .commit();

            Log.i(TAG, "create new CatalogFragment");
        }

        setFabVisible(false);
        final AuthPreferences authPreferences = new AuthPreferences(this);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (authPreferences.isUserLoggedIn()) {
                    Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void setFabVisible(boolean visible) {
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setAnchorId(View.NO_ID);
        fab.setLayoutParams(p);

        if (visible) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}