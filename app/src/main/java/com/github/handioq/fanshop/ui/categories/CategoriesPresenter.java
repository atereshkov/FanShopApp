package com.github.handioq.fanshop.ui.categories;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class CategoriesPresenter implements CategoriesMvp.Presenter, CategoriesMvp.Model.Callback {

    private CategoriesMvp.View categoriesView;
    private CategoriesMvp.Model categoriesModel;
    private NetworkService networkService;

    private final static String TAG = "CatalogPresenter";

    @Inject
    public CategoriesPresenter(NetworkService networkService) {
        categoriesModel = new CategoriesModel(networkService);
        categoriesModel.setCallback(this);
    }

    @Override
    public void getCategories() {
        if (categoriesView != null) {
            categoriesView.showLoadCategoriesProgress();
        }

        categoriesModel.getCategories();
    }

    @Override
    public void setView(CategoriesMvp.View view) {
        this.categoriesView = view;
    }

    @Override
    public void onCategoriesLoaded(List<CategoryDVO> categories) {
        categoriesView.hideLoadCategoriesProgress();
        categoriesView.setCategories(categories);
    }

    @Override
    public void onCategoriesLoadError(Throwable error) {
        categoriesView.hideLoadCategoriesProgress();
        categoriesView.showLoadCategoriesError(error);
        Log.e(TAG, "onError");
    }

    @Override
    public void onLoadCategoriesCompleted() {
        categoriesView.hideLoadCategoriesProgress();
    }
}
