package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.ProductDTO;

public interface ProductInfoView {

    void showProgress();

    void hideProgress();

    void setProduct(ProductDTO productDTO);

    void onError(Throwable e);

    // TODO: complete methods

}
