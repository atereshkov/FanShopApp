package com.github.handioq.fanshop.catalog;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.cart.CartActivity;
import com.github.handioq.fanshop.cart.interaction.AddToCartClickEvent;
import com.github.handioq.fanshop.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.catalog.adapter.CatalogRecyclerAdapter;
import com.github.handioq.fanshop.catalog.adapter.PaginationOnScrollListener;
import com.github.handioq.fanshop.catalog.search.SearchActivity;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.BadgeDrawable;
import com.github.handioq.fanshop.util.NetworkConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CatalogFragment extends BaseFragment implements CatalogMvp.View, PaginationListener, AddToCartMvp.View {

    @BindView(R.id.catalog_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private CatalogRecyclerAdapter adapter;

    private Menu optionsMenu;
    private boolean firstPaginationLoad = true;

    @Inject
    CatalogMvp.Presenter catalogPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

    @Inject
    AuthPreferences authPreferences;

    private final String TAG = "CatalogFragment";
    private static final String CATEGORY_KEY = "category";
    private String category;

    public static CatalogFragment newInstance(String category) {
        CatalogFragment fragment = new CatalogFragment();

        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readBundle(getArguments());
        return inflater.inflate(R.layout.fragment_catalog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCatalogComponent().inject(this);

        adapter = new CatalogRecyclerAdapter(new ArrayList<ProductDVO>());

        addToCartPresenter.setView(this);
        catalogPresenter.setView(this);
        catalogPresenter.getProducts(category, 0, NetworkConstants.PRODUCTS_LOAD_COUNT);

        initRecycler();
        ((CatalogActivity) getActivity()).setFabVisible(true);
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 card in a row
        //ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());
        //final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCardsCount()); // n cards in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        ActivityCompat.invalidateOptionsMenu(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onAddToCartEvent(AddToCartClickEvent event) {
        Log.i(TAG, "onAddToCartEvent");
        //Toast.makeText(getContext(), "AddToCartEvent: " + event.product, Toast.LENGTH_SHORT).show();

        if (authPreferences.isUserLoggedIn()) {
            addToCartPresenter.addProductToCart(authPreferences.getUserId(), event.getProduct().getId());
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.cart_add_item_not_logged), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaginationLoad(boolean state, int totalItemCount, int limit) {
        firstPaginationLoad = state;

        setRefreshActionButtonState(true);
        catalogPresenter.getProducts(category, totalItemCount, limit);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        optionsMenu = menu;

        if (authPreferences.isUserLoggedIn()) {
            setBadgeCount(menu, "2");
        } else {
            MenuItem itemCart = menu.findItem(R.id.cart);
            itemCart.setVisible(false);
        }

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void setBadgeCount(Menu menu, String count) {
        MenuItem itemCart = menu.findItem(R.id.cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        BadgeDrawable.setCartBadgeCount(getContext(), icon, count);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.cart) {
            Intent intent = new Intent(getContext(), CartActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.refresh) {
            Toast.makeText(getContext(), "not impl", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(getContext(), "not impl", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu.findItem(R.id.refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            category = bundle.getString("category");
        }
    }

    @Override
    public void showLoadProductsProgress() {
        if (firstPaginationLoad) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        setRefreshActionButtonState(true);
    }

    @Override
    public void hideLoadProductsProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        setRefreshActionButtonState(false);
    }

    @Override
    public void setProducts(List<ProductDVO> products) {
        adapter.addItems(products);
    }

    @Override
    public void showLoadProductsError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onProductAddSuccess(Response response) {
        Toast.makeText(getContext(), response.getStatusMessage() + " - " + response.getStatusCode(), Toast.LENGTH_SHORT).show();
        // TODO inc badge in toolbar
        setBadgeCount(optionsMenu, "5");
        Log.i(TAG, "onProductAddSuccess");
    }

    @Override
    public void onProductAddError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
