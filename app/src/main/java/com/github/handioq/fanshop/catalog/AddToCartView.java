package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.Response;

public interface AddToCartView {

    void onProductAddSuccess(Response response);

    void onProductAddError(Throwable e);

}
