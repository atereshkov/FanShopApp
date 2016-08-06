package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.Response;

public interface AddToCartModel {

    void addProductToCart(int userId, ProductDTO productDTO);

    void setCallback(Callback callback);

    interface Callback {

        void onProductAdded(Response response);

        void onProductAddError(Throwable error);

        void onCompleted();
    }

}
