package com.github.handioq.fanshop.catalog.search;

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
import com.github.handioq.fanshop.catalog.AddToCartClickEvent;
import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.catalog.PaginationListener;
import com.github.handioq.fanshop.catalog.adapter.PaginationOnScrollListener;
import com.github.handioq.fanshop.catalog.search.adapter.SearchRecyclerAdapter;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.util.NetworkConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements SearchMvp.View, SearchView.OnQueryTextListener,
        PaginationListener {

    @BindView(R.id.search_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private SearchRecyclerAdapter adapter;

    private String searchQuery;
    private boolean firstPaginationLoad = true;

    private final String TAG = "SearchFragment";

    @Inject
    SearchMvp.Presenter searchPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

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

        adapter = new SearchRecyclerAdapter(new ArrayList<ProductDTO>());

        initRecycler();
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
                        Toast.makeText(getContext(), "on collapsed", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        Toast.makeText(getContext(), "on expanded", Toast.LENGTH_SHORT).show();
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

        addToCartPresenter.addProductToCart(500, event.getProduct()); // TODO change mock id for real
        Log.i(TAG, "onAddToCartEvent " + event.getProduct());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        this.searchQuery = query;

        if (!query.isEmpty()) {
            Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
            adapter.clearItems();

            recyclerView.clearOnScrollListeners(); // fix pagination previous count
            recyclerView.addOnScrollListener(new PaginationOnScrollListener(this, layoutManager));

            searchPresenter.search(query, 0, NetworkConstants.PRODUCTS_LOAD_COUNT);
        } else {
            adapter.clearItems();
        }

        return true;
    }

    @Override
    public void onPaginationLoad(boolean state, int totalItemCount, int limit) {
        Log.i(TAG, "onPaginationLoad: " + state + " " + totalItemCount + " " + limit);

        firstPaginationLoad = state;
        searchPresenter.search(searchQuery, totalItemCount, limit);
    }

    @Override
    public void onSearchSuccess(List<ProductDTO> products) {
        Log.i(TAG, "onSearchSuccess() - " + products.size() + " " + firstPaginationLoad);

        if (!firstPaginationLoad) {
            adapter.addItems(products); // add items (pagination load)
        } else {
            adapter.setItems(products); // new search
        }
    }

    @Override
    public void onSearchError(Throwable e) {
        e.printStackTrace();
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
