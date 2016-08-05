package com.github.handioq.fanshop.cart;

import android.view.View;

public interface CartPresenter {

    void getCartItems(int userId);

    void onItemClicked(View view, int position);

    void setView(CartView cartView);

}
