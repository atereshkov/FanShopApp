package com.github.handioq.fanshop.ui.cart.interaction;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.net.model.Response;

public interface AddToCartMvp {

    interface Model extends Mvp.Model {

        void addProductToCart(int userId, int productId);

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

        void addProductToCart(int userId, int productId);

    }

}
