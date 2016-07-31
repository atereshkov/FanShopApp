package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CatalogModel {

    void getProducts(int offset, int count);

    void setCallback(Callback callback);

    interface Callback {

        void onProductsLoaded(List<ProductDTO> productDTOs);

        void onProductsLoadError(Throwable error);
    }

}
