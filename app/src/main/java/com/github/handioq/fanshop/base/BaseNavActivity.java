package com.github.handioq.fanshop.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.ui.account.AccountActivity;
import com.github.handioq.fanshop.ui.cart.CartActivity;
import com.github.handioq.fanshop.ui.catalog.CatalogActivity;
import com.github.handioq.fanshop.ui.categories.CategoriesActivity;
import com.github.handioq.fanshop.ui.login.LoginActivity;
import com.github.handioq.fanshop.ui.stores.StoresActivity;
import com.github.handioq.fanshop.ui.wishlist.WishlistActivity;
import com.github.handioq.fanshop.util.AuthPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public abstract class BaseNavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BaseNavActivity";
    private Unbinder unbinder;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ActionBarDrawerToggle mToggle;

    private AuthPreferences authPreferences;
    Button loginButton;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(null);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }

        mToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mToggle);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        loginButton = (Button) header.findViewById(R.id.login_button);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authPreferences = new AuthPreferences(getApplicationContext());
    }

    @Override
    protected void onStart() { // onDrawerStateChanged?
        super.onStart();
        Timber.i("AuthState -> %s ", authPreferences.isUserLoggedIn());

        if (authPreferences.isUserLoggedIn()) {
            loginButton.setText(getResources().getString(R.string.action_logout));
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    authPreferences.logout();
                    Timber.i("AuthState -> %s, userID: %d ", authPreferences.isUserLoggedIn(), authPreferences.getUserId());
                    drawer.closeDrawer(GravityCompat.START);
                    ActivityCompat.invalidateOptionsMenu(BaseNavActivity.this);
                    Toast.makeText(BaseNavActivity.this, getString(R.string.success_logged_out), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            loginButton.setText(getResources().getString(R.string.action_sign_in));
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BaseNavActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            Intent intent = new Intent(BaseNavActivity.this, CategoriesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);

        } else if (id == R.id.nav_catalog) {
            Intent intent = new Intent(BaseNavActivity.this, CatalogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);

        } else if (id == R.id.nav_cart) {
            if (authPreferences.isUserLoggedIn()) {
                Intent intent = new Intent(BaseNavActivity.this, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            } else {
                Intent intent = new Intent(BaseNavActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_wishlist) {
            if (authPreferences.isUserLoggedIn()) {
                Intent intent = new Intent(BaseNavActivity.this, WishlistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            } else {
                Intent intent = new Intent(BaseNavActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_account) {
            if (authPreferences.isUserLoggedIn()) {
                Intent intent = new Intent(BaseNavActivity.this, AccountActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            } else {
                Intent intent = new Intent(BaseNavActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_delivery) {
            Toast.makeText(this, R.string.delivery_not_impl, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_stores) {
            Intent intent = new Intent(BaseNavActivity.this, StoresActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected final NavigationView getNavigationView() {
        return navigationView;
    }

    protected final DrawerLayout getDrawer() {
        return drawer;
    }

    protected final Unbinder getUnbinder() {
        return unbinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
