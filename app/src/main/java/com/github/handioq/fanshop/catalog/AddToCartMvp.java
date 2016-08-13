package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.model.Response;

public interface AddToCartMvp {

    interface Model extends Mvp.Model {

        void addProductToCart(int userId, ProductDTO productDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onProductAdded(Response response);

            void onProductAddError(Throwable error);

            void onAddToCartCompleted();
        }
    }

    interface View extends Mvp.View {

        void onProductAddSuccess(Response response);

        void onProductAddError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <AddToCartMvp.View> {

        void addProductToCart(int userId, ProductDTO productDTO);

    }

}
