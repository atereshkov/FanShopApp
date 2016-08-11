package com.github.handioq.fanshop.categories;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.net.NetworkService;

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
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<List<CategoryDTO>>applyScheduler())
                .subscribe(new Subscriber<List<CategoryDTO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadCategoriesCompleted();
                }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCategoriesLoadError(e);
                    }

                    @Override
                    public void onNext(List<CategoryDTO> categories) {
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
