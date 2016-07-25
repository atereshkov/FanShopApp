package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.Product;

public interface ProductInfoView {

    void showProgress();

    void hideProgress();

    void setProduct(Product product);

    void onError(Throwable e);

    // TODO: complete methods

}
