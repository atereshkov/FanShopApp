package com.github.handioq.fanshop.ui.categories;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class CategoriesPresenter implements CategoriesMvp.Presenter, CategoriesMvp.Model.Callback {

    private CategoriesMvp.View view;
    private CategoriesMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "CatalogPresenter";

    @Inject
    public CategoriesPresenter(NetworkService networkService) {
        model = new CategoriesModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void getCategories() {
        if (view != null) {
            view.showLoadCategoriesProgress();
        }

        model.getCategories();
    }

    @Override
    public void setView(CategoriesMvp.View view) {
        this.view = view;
    }

    @Override
    public void onCategoriesLoaded(CategoryListDVO categories) {
        if (view != null) {
            view.hideLoadCategoriesProgress();
            view.setCategories(categories);
        }
    }

    @Override
    public void onCategoriesLoadError(Throwable error) {
        if (view != null) {
            view.hideLoadCategoriesProgress();
            view.showLoadCategoriesError(error);
        }
        Log.e(TAG, "onError");
    }

    @Override
    public void onLoadCategoriesCompleted() {
        if (view != null) {
            view.hideLoadCategoriesProgress();
        }
    }
}
