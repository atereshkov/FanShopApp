package com.github.handioq.fanshop.catalog;

import android.view.View;

public interface CatalogPresenter {

    void getProducts();

    void onItemClicked(View view, int position);

}
