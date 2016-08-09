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

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setProducts(List<ProductDTO> productDTOs);

        void showError(Throwable e); // change to showError(int errorCode);

        //void onItemClicked(View view, int position);

        //void onAddToCartClicked(ProductDTO productDTO);
    }

    interface Presenter extends Mvp.Presenter<CatalogMvp.View> {

        void getProducts(int offset, int limit);

        //void onItemClicked(View view, int position);
    }
}
