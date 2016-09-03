package com.github.handioq.fanshop.ui.categories.subcategory;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class SubcategoryModel implements SubcategoryMvp.Model {

    private final NetworkService networkService;
    private SubcategoryMvp.Model.Callback callback;

    private final static String TAG = "CategoryModel";

    public SubcategoryModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getCategory(int categoryId) {

        networkService.getApiService()
                .getCategory(categoryId)
                .map(Mapper::mapCategoriesToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<List<CategoryDVO>>applyScheduler())
                .subscribe(new Subscriber<List<CategoryDVO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadCategoryCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCategoryLoadError(e);
                    }

                    @Override
                    public void onNext(List<CategoryDVO> categories) {
                        callback.onCategoryLoaded(categories);
                    }
                });

        Log.i(TAG, "getCategory()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
