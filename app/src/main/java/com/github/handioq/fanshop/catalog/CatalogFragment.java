package com.github.handioq.fanshop.catalog;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.github.handioq.fanshop.catalog.adapter.CatalogRecyclerAdapter;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductInfoActivity;
import com.github.handioq.fanshop.util.ScreenDimensionsHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CatalogFragment extends BaseFragment implements CatalogView {

    @BindView(R.id.catalogProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private CatalogPresenter catalogPresenter;
    private CatalogRecyclerAdapter adapter;

    boolean loading = true;
    private int previousTotal = 0;
    private static final int PAGINATION_LIMIT = 5;

    private Menu optionsMenu;

    @Inject
    NetworkService networkService;

    private final String TAG = "CatalogFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
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

        ((FanShopApp) getActivity().getApplication()).getNetComponent().inject(this);

        adapter = new CatalogRecyclerAdapter(new ArrayList<ProductDTO>());

        catalogPresenter = new CatalogPresenterImpl(this, networkService);
        catalogPresenter.getProducts(0, 5);

        layoutManager = new LinearLayoutManager(getContext()); // 1 card in a row
        //ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());
        //final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCardsCount()); // n cards in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
                    Log.i(TAG, "end onScroll");
                    setRefreshActionButtonState(true);

                    catalogPresenter.getProducts(totalItemCount, PAGINATION_LIMIT);
                    loading = true;

                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            setRefreshActionButtonState(false);
                        }
                    }, 1000);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        optionsMenu = menu;
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            Toast.makeText(getContext(), "not impl", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_settings) {
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

    @Override
    public void showProgress() {
        if (loading)
        {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProducts(List<ProductDTO> productDTOs) {
        adapter.addItems(productDTOs);
    }

    @Override
    public void onItemClicked(View view, int position) {
        //Toast.makeText(getActivity(), "onItemClicked " + position, Toast.LENGTH_SHORT).show();
        startActivity(ProductInfoActivity.makeIntent(getContext(), position));
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
