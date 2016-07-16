package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface CatalogModel {
    interface OnFinishedListener {
        void onFinished(List<Product> items);
    }

    void findItems(OnFinishedListener listener);
}
