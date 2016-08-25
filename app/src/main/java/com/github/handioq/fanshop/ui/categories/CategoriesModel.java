package com.github.handioq.fanshop.ui.categories;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class CategoriesModel implements CategoriesMvp.Model {

    private final NetworkService networkService;
    private CategoriesMvp.Model.Callback callback;

    private final static String TAG = "CatalogModel";

    public CategoriesModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getCategories() {

        networkService.getApiService()
                .getCategories()
                .map(Mapper::mapCategoriesToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<List<CategoryDVO>>applyScheduler())
                .subscribe(new Subscriber<List<CategoryDVO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadCategoriesCompleted();
                }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCategoriesLoadError(e);
                    }

                    @Override
                    public void onNext(List<CategoryDVO> categories) {
                        callback.onCategoriesLoaded(categories);
                    }
                });

        Log.i(TAG, "getCategories()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
