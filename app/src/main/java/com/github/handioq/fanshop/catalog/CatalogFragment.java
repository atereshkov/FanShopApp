package com.github.handioq.fanshop.catalog;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.catalog.adapter.CatalogRecyclerAdapter;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.util.ScreenDimensionsHelper;

import java.util.List;

import butterknife.BindView;

public class CatalogFragment extends BaseFragment implements CatalogView {

    @BindView(R.id.catalogProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //private RecyclerView.LayoutManager layoutManager;
    private CatalogPresenter catalogPresenter;
    private CatalogRecyclerAdapter adapter;

    private final String TAG = "CatalogFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_catalog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());

        //layoutManager = new LinearLayoutManager(this); // 1 card in a row
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCardsCount()); // n cards in a row
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        catalogPresenter = new CatalogPresenterImpl(this, ((FanShopApp) getActivity().getApplication()).getNetworkService());
        catalogPresenter.getProducts();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Product> items) {
        adapter = new CatalogRecyclerAdapter(items, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCompleted() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}