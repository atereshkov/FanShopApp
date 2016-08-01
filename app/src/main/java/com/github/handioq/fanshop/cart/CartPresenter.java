package com.github.handioq.fanshop.cart;

import android.view.View;

public interface CartPresenter {

    void getProducts();

    void onItemClicked(View view, int position);

}
