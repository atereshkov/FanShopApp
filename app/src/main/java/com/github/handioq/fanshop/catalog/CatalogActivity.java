package com.github.handioq.fanshop.catalog;

import android.content.Intent;
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
import com.github.handioq.fanshop.cart.CartActivity;
import com.github.handioq.fanshop.login.LoginActivity;

import butterknife.BindView;

public class CatalogActivity extends BaseNavActivity {

    private static final String TAG = "CatalogActivity";
    private static final String CATALOG_FRAGMENT_TAG = "catalog";

    @BindView(R.id.fab_catalog)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Log.e(TAG, "onCreate");

        if (getSupportFragmentManager().findFragmentByTag(CATALOG_FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new CatalogFragment(), CATALOG_FRAGMENT_TAG)
                    .commit();

            Log.e(TAG, "create new CatalogFragment");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(CatalogActivity.this, "fabOnclick", Toast.LENGTH_SHORT).show();
                startCart();
            }
        });

        startLogin();
        //startCart();
    }

    void startLogin() {
        Intent intent = new Intent(CatalogActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    void startCart() {
        Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
        startActivity(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}