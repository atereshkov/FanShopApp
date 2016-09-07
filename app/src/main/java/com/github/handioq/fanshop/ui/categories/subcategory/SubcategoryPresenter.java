package com.github.handioq.fanshop.ui.categories.subcategory;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class SubcategoryPresenter implements SubcategoryMvp.Presenter, SubcategoryMvp.Model.Callback {

    private SubcategoryMvp.View subcategoryView;
    private SubcategoryMvp.Model subcategoryModel;
    private NetworkService networkService;

    private final static String TAG = "SubcategoryPresenter";

    @Inject
    public SubcategoryPresenter(NetworkService networkService) {
        subcategoryModel = new SubcategoryModel(networkService);
        subcategoryModel.setCallback(this);
    }

    @Override
    public void getCategory(int categoryId) {
        if (subcategoryView != null) {
            subcategoryView.showLoadCategoryProgress();
        }

        subcategoryModel.getCategory(categoryId);
    }

    @Override
    public void setView(SubcategoryMvp.View view) {
        this.subcategoryView = view;
    }

    @Override
    public void onCategoryLoaded(CategoryListDVO categories) {
        subcategoryView.hideLoadCategoryProgress();
        subcategoryView.setCategory(categories);
    }

    @Override
    public void onCategoryLoadError(Throwable error) {
        subcategoryView.hideLoadCategoryProgress();
        subcategoryView.showLoadCategoryError(error);
        Log.e(TAG, "onError");
    }

    @Override
    public void onLoadCategoryCompleted() {
        subcategoryView.hideLoadCategoryProgress();
    }
}
