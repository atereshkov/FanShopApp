package com.github.handioq.fanshop.cart;

import android.view.View;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CartView {

    void showProgress();

    void hideProgress();

    void setCartItems(List<ProductDTO> productDTOs);

    void onError(Throwable e);

    void onItemClicked(View view, int position);

}
