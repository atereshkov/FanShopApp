package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class CatalogPresenterImpl implements CatalogPresenter, CatalogModel.OnFinishedListener {

    private CatalogView catalogView;
    private NetworkService networkService;
    private Subscription subscription;
    private CatalogModel catalogModel;

    public CatalogPresenterImpl(CatalogView catalogView, NetworkService networkService) {
        this.catalogView = catalogView;
        this.networkService = networkService;
        catalogModel = new CatalogModelImpl();

        //catalogModel.findItems(this); // TODO: start findItems from here and remove onResume from Activity
    }

    @Override
    public void onResume() {
        if (catalogView != null) {
            catalogView.showProgress();
        }

        catalogModel.findItems(this);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onFinished(List<Product> items) {
        if (catalogView != null) {
            catalogView.setItems(items);
            catalogView.hideProgress();
        }
    }
}
