package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.Product;

import java.util.List;

public class CatalogPresenterImpl implements CatalogPresenter {

    private CatalogView catalogView;

    public CatalogPresenterImpl(CatalogView catalogView) {
        this.catalogView = catalogView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {

    }
}
