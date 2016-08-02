package com.github.handioq.fanshop.catalog;

import android.view.View;

public interface CatalogPresenter {

    void getProducts(int offset, int limit);

    void onItemClicked(View view, int position);

}
