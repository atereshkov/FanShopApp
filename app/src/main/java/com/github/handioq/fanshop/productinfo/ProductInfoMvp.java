package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.dto.ProductDTO;

public interface ProductInfoMvp {

    interface Model {

        void getProduct(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onProductLoaded(ProductDTO productDTO);

            void onProductLoadError(Throwable error);
        }
    }

    interface ProductInfoView {

        void showProgress();

        void hideProgress();

        void setProduct(ProductDTO productDTO);

        void onError(Throwable e);

        // TODO: complete methods

    }

    interface Presenter {

        void getProduct(int id); // parameters? index? whatever..

        void setView(ProductInfoView productInfoView);

    }
}
