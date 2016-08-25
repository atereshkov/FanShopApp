package com.github.handioq.fanshop.ui.categories.subcategory;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

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
                .map(Mapper::mapCategoryToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<CategoryDVO>applyScheduler())
                .subscribe(new Subscriber<CategoryDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadCategoryCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCategoryLoadError(e);
                    }

                    @Override
                    public void onNext(CategoryDVO category) {
                        callback.onCategoryLoaded(category);
                    }
                });

        Log.i(TAG, "getCategory()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
