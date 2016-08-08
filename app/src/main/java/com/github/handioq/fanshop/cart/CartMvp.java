package com.github.handioq.fanshop.cart;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CartMvp {

    interface Model extends Mvp.Model {

        void gerCartItems(int userid);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(List<ProductDTO> productDTOs);

            void onProductsLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setCartItems(List<ProductDTO> products);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<CartMvp.View> {

        void getCartItems(int userId);

    }
}
