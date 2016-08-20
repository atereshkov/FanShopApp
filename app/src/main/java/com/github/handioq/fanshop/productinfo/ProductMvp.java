package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;

public interface ProductMvp {

    interface Model extends Mvp.Model {

        void getProduct(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onProductLoaded(ProductDTO productDTO);

            void onProductLoadError(Throwable error);
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setProduct(ProductDTO productDTO);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<ProductMvp.View> {

        void getProduct(int id);

    }
}
