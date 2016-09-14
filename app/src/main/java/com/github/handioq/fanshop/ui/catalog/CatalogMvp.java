package com.github.handioq.fanshop.ui.catalog;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ProductListDVO;

import java.util.List;

public interface CatalogMvp {

    interface Model extends Mvp.Model {

        void getProducts(long category, int offset, int count);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(ProductListDVO products);

            void onProductsLoadError(Throwable error);

            void onLoadProductsCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadProductsProgress();

        void hideLoadProductsProgress();

        void setProducts(ProductListDVO products);

        void showLoadProductsError(Throwable e); // change to showLoadProductsError(int errorCode);

    }

    interface Presenter extends Mvp.Presenter<CatalogMvp.View> {

        void getProducts(long category, int offset, int limit);

    }
}
