package com.github.handioq.fanshop.ui.catalog.search;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import rx.Subscriber;

public class SearchModel implements SearchMvp.Model {

    private static final String TAG = "SearchModel";

    private final NetworkService networkService;
    private SearchModel.Callback callback;

    public SearchModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void search(String query, int offset, int limit) {
        networkService.getApiService()
                .search(query, offset, limit)
                .map(Mapper::mapProductListToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<ProductListDVO>applyScheduler())
                .subscribe(new Subscriber<ProductListDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onSearchCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSearchError(e);
                    }

                    @Override
                    public void onNext(ProductListDVO products) {
                        callback.onSearchSuccess(products);
                    }
                });

        Log.i(TAG, "search() " + query);
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;

        Log.i(TAG, "setCallback()");
    }
}
