package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.dto.ProductDTO;

public interface ProductInfoModel {

    void getProduct(int id);

    void setCallback(Callback callback);

    interface Callback {

        void onProductLoaded(ProductDTO productDTO);

        void onProductLoadError(Throwable error);
    }

}
