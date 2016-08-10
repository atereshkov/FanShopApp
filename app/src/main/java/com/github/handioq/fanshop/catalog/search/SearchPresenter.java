package com.github.handioq.fanshop.catalog.search;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

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
    public void search(String query) {
        if (searchView != null) {
            searchView.showSearchProgress();
            Log.i(TAG, "showLoadProductsProgress() on catalogView");
        }

        searchModel.search(query);
    }

    @Override
    public void onSearchSuccess(List<ProductDTO> products) {
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
