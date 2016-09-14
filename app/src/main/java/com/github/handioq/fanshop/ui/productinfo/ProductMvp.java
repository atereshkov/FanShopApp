package com.github.handioq.fanshop.ui.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

public interface ProductMvp {

    interface Model extends Mvp.Model {

        void getProduct(int productId, int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onProductLoaded(ProductDVO product);

            void onProductLoadError(Throwable error);
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setProduct(ProductDVO product);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<ProductMvp.View> {

        void getProduct(int productId, int userId);

    }
}
