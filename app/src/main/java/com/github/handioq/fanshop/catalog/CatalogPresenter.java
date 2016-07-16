package com.github.handioq.fanshop.catalog;

public interface CatalogPresenter {

    void getProducts();

    void onItemClicked(int position);

    void onDestroy();

}
