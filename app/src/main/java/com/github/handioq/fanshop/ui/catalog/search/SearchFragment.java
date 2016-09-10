package com.github.handioq.fanshop.ui.catalog.search;

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
import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.ui.cart.interaction.AddToCartClickEvent;
import com.github.handioq.fanshop.ui.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.ui.catalog.PaginationListener;
import com.github.handioq.fanshop.ui.catalog.adapter.PaginationOnScrollListener;
import com.github.handioq.fanshop.ui.catalog.search.adapter.SearchRecyclerAdapter;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.NetworkConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class SearchFragment extends BaseFragment implements SearchMvp.View, SearchView.OnQueryTextListener,
        PaginationListener {

    public static final String QUERY_PRODUCT_NAME = "productName";
    @BindView(R.id.search_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private SearchRecyclerAdapter adapter;

    private String searchQuery;
    private Map<String, String> optionsMap;
    private boolean firstPaginationLoad = true;

    private final String TAG = "SearchFragment";

    @Inject
    SearchMvp.Presenter searchPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCatalogComponent().inject(this);

        searchPresenter.setView(this);
        adapter = new SearchRecyclerAdapter(new ArrayList<ProductDVO>());
        initRecycler();

        optionsMap = new HashMap<>();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 card in a row
        //ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());
        //final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCardsCount()); // n cards in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));  // FIXME: 10-Aug-16
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        final MenuItem itemSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(itemSearch,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        //Toast.makeText(getContext(), "on collapsed", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        //Toast.makeText(getContext(), "on expanded", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

        expandSearchView(itemSearch, true);

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void expandSearchView(MenuItem itemSearch, boolean expand) {
        if (expand) {
            itemSearch.expandActionView();
        } else {
            itemSearch.collapseActionView();
        }
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

        if (authPreferences.isUserLoggedIn()) {
            addToCartPresenter.addProductToCart(authPreferences.getUserId(), event.getProduct().getId());
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.cart_add_item_not_logged), Toast.LENGTH_SHORT).show();
        }

        Timber.i("onAddToCartEvent(), product id: %d", event.getProduct().getId());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        this.searchQuery = query;

        if (query.isEmpty()) {
            adapter.clearItems();
        } else {
            optionsMap.put(QUERY_PRODUCT_NAME, searchQuery);
            adapter.clearItems();

            recyclerView.clearOnScrollListeners(); // fix pagination previous count
            recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));

            searchPresenter.search(optionsMap, 0, NetworkConstants.PRODUCTS_LOAD_COUNT);
        }

        return true;
    }

    @Override
    public void onPaginationLoad(boolean state, int totalItemCount, int limit) {
        Timber.i("onPaginationLoad() - state: %b, totalItemCount: %d, limit: %d", state, totalItemCount, limit);

        firstPaginationLoad = state;
        searchPresenter.search(optionsMap, totalItemCount, limit);
    }

    @Override
    public void onSearchSuccess(ProductListDVO products) {
        Timber.i("onSearchSuccess() - products.size: %d, firstPaginationLoad: %b", products.getProducts().size(), firstPaginationLoad);

        if (firstPaginationLoad) {
            adapter.setItems(products.getProducts()); // new search
        } else {
            adapter.addItems(products.getProducts()); // add items (pagination load)
        }
    }

    @Override
    public void onSearchError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void showSearchProgress() {
        if (firstPaginationLoad) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideSearchProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
