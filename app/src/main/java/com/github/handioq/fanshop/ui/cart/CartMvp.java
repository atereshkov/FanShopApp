package com.github.handioq.fanshop.ui.cart;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public interface CartMvp {

    interface Model extends Mvp.Model {

        void getCartItems(int userid);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(List<ProductDVO> productDTOs);

            void onProductsLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setCartItems(List<ProductDVO> products);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<CartMvp.View> {

        void getCartItems(int userId);

    }
}
