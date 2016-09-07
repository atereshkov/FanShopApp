package com.github.handioq.fanshop.ui.catalog.search;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class SearchPresenter implements SearchMvp.Presenter, SearchMvp.Model.Callback  {

    private SearchMvp.View searchView;
    private SearchMvp.Model searchModel;
    private NetworkService networkService;

    private final static String TAG = "SearchPresenter";

    @Inject
    public SearchPresenter(NetworkService networkService) {
        searchModel = new SearchModel(networkService);
        searchModel.setCallback(this);
    }

    @Override
    public void search(String query, int offset, int limit) {
        if (searchView != null) {
            searchView.showSearchProgress();
            Log.i(TAG, "showLoadProductsProgress() on catalogView");
        }

        searchModel.search(query, offset, limit);
    }

    @Override
    public void onSearchSuccess(ProductListDVO products) {
        searchView.onSearchSuccess(products);
    }

    @Override
    public void onSearchError(Throwable error) {
        searchView.onSearchError(error);
    }

    @Override
    public void onSearchCompleted() {
        searchView.hideSearchProgress();
    }

    @Override
    public void setView(SearchMvp.View view) {
        this.searchView = view;
    }
}
