package com.github.handioq.fanshop.catalog;

import android.view.View;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CatalogMvp {

    interface Model {

        void getProducts(int offset, int count);

        void setCallback(Callback callback);

        interface Callback {

            void onProductsLoaded(List<ProductDTO> productDTOs);

            void onProductsLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface CatalogView {

        void showProgress();

        void hideProgress();

        void setProducts(List<ProductDTO> productDTOs);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

        void onAddToCartClicked(ProductDTO productDTO);
    }

    interface Presenter {

        void getProducts(int offset, int limit);

        //void onItemClicked(View view, int position);

        void setView(CatalogView catalogView);

    }
}
