package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CatalogMvp {

    interface Model extends Mvp.Model {

        void getProducts(int offset, int count);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(List<ProductDTO> productDTOs);

            void onProductsLoadError(Throwable error);

            void onLoadProductsCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadProductsProgress();

        void hideLoadProductsProgress();

        void setProducts(List<ProductDTO> productDTOs);

        void showLoadProductsError(Throwable e); // change to showLoadProductsError(int errorCode);

    }

    interface Presenter extends Mvp.Presenter<CatalogMvp.View> {

        void getProducts(int offset, int limit);

    }
}
