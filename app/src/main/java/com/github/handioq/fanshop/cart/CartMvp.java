package com.github.handioq.fanshop.cart;

import android.view.View;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CartMvp {

    interface Model {

        void gerCartItems(int userid);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(List<ProductDTO> productDTOs);

            void onProductsLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setCartItems(List<ProductDTO> productDTOs);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter {

        void getCartItems(int userId);

        //void onItemClicked(View view, int position);

        void setView(CartMvp.View cartView);

    }
}
