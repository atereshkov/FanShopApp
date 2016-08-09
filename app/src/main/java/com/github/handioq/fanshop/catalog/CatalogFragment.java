package com.github.handioq.fanshop.catalog;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.github.handioq.fanshop.catalog.adapter.CatalogRecyclerAdapter;
import com.github.handioq.fanshop.catalog.adapter.PaginationOnScrollListener;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.Response;
import com.github.handioq.fanshop.util.BadgeDrawable;
import com.github.handioq.fanshop.util.NetworkConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CatalogFragment extends BaseFragment implements CatalogMvp.View, PaginationListener,
        SearchView.OnQueryTextListener, AddToCartMvp.View {

    @BindView(R.id.catalog_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private CatalogRecyclerAdapter adapter;

    private Menu optionsMenu;
    private boolean paginationLoading = true;

    @Inject
    CatalogMvp.Presenter catalogPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

    private final String TAG = "CatalogFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCatalogComponent().inject(this);

        adapter = new CatalogRecyclerAdapter(new ArrayList<ProductDTO>());

        addToCartPresenter.setView(this);
        catalogPresenter.setView(this);
        catalogPresenter.getProducts(0, NetworkConstants.PRODUCTS_LOAD_COUNT);

        initRecycler();
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
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onAddToCartEvent(AddToCartClickEvent event) {
        //Toast.makeText(getContext(), "AddToCartEvent: " + event.product, Toast.LENGTH_SHORT).show();

        addToCartPresenter.addProductToCart(500, event.product); // TODO change mock id for real
        Log.i(TAG, "onAddToCartEvent");
    }

    @Override
    public void onPaginationLoad(boolean state, int totalItemCount, int limit) {
        paginationLoading = state;

        setRefreshActionButtonState(true);
        catalogPresenter.getProducts(totalItemCount, limit);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        optionsMenu = menu;

        final MenuItem itemSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(itemSearch,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        Toast.makeText(getContext(), "on collapsed", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        Toast.makeText(getContext(), "on expanded", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

        MenuItem itemCart = menu.findItem(R.id.cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        BadgeDrawable.setCartBadgeCount(getContext(), icon, "3"); // test

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cart) {
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
        return true;
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

    @Override
    public void showProgress() {
        if (paginationLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        setRefreshActionButtonState(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        setRefreshActionButtonState(false);
    }

    @Override
    public void setProducts(List<ProductDTO> productDTOs) {
        adapter.addItems(productDTOs);
    }

    @Override
    public void showError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onProductAddSuccess(Response response) {
        Toast.makeText(getContext(), response.getStatusMessage() + " - " + response.getStatusCode(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onProductAddSuccess");
    }

    @Override
    public void onProductAddError(Throwable e) {
        e.printStackTrace();
        Log.e(TAG, "onProductAddError");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
