package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface CatalogView {

    void showProgress();

    void hideProgress();

    void setItems(List<Product> items);

    void onError(Throwable e);

    //void showMessage(String message);

}
