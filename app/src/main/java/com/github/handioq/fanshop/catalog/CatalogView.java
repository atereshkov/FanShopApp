package com.github.handioq.fanshop.catalog;

import android.view.View;

import com.github.handioq.fanshop.model.Product;

import java.util.List;

public interface CatalogView {

    void showProgress();

    void hideProgress();

    void setProducts(List<Product> products);

    void onError(Throwable e);

    void onItemClicked(View view, int position);
}
