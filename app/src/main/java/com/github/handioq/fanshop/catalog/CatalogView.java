package com.github.handioq.fanshop.catalog;

import android.view.View;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CatalogView {

    void showProgress();

    void hideProgress();

    void setProducts(List<ProductDTO> productDTOs);

    void onError(Throwable e);

    void onItemClicked(View view, int position);
}
