package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.Response;

public interface AddToCartMvp {

    interface Model {

        void addProductToCart(int userId, ProductDTO productDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onProductAdded(Response response);

            void onProductAddError(Throwable error);

            void onCompleted();
        }
    }

    interface AddToCartView {

        void onProductAddSuccess(Response response);

        void onProductAddError(Throwable e);

    }

    interface Presenter {

        void addProductToCart(int userId, ProductDTO productDTO);

        void setView(AddToCartView addToCartView);

    }

}
