package com.github.handioq.fanshop.ui.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.PassOrderDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.ui.cart.adapter.CartRecyclerAdapter;
import com.github.handioq.fanshop.ui.cart.interaction.RemoveFromCartEvent;
import com.github.handioq.fanshop.ui.cart.interaction.RemoveFromCartMvp;
import com.github.handioq.fanshop.ui.checkout.CheckoutActivity;
import com.github.handioq.fanshop.ui.checkout.CheckoutEvent;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.Mapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class CartFragment extends BaseFragment implements CartMvp.View, RemoveFromCartMvp.View {

    private final static String TAG = "CartFragment";

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.cart_items_count)
    TextView cartItemsCount;

    private LinearLayoutManager layoutManager;
    private CartRecyclerAdapter adapter;

    @Inject
    CartMvp.Presenter cartPresenter;

    @Inject
    RemoveFromCartMvp.Presenter removeFromCartPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCartComponent().inject(this);

        adapter = new CartRecyclerAdapter(new ArrayList<ProductDVO>());

        removeFromCartPresenter.setView(this);
        cartPresenter.setView(this);
        cartPresenter.getCartItems(authPreferences.getUserId());

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onCheckoutClickEvent(CheckoutEvent event) {
        Log.i(TAG, "CheckoutEvent");

        if (adapter.getItemCount() != 0) {
            PassOrderDTO passOrder = new PassOrderDTO(Mapper.mapProductId(adapter.getItems()));
            startActivity(CheckoutActivity.makeIntent(getContext(), passOrder));
        } else {
            Toast.makeText(getContext(), "Your cart is empty..", Toast.LENGTH_SHORT).show(); // TODO extract resource string
        }
    }

    @Subscribe
    public void onRemoveFromCartEvent(RemoveFromCartEvent event) {
        Log.i(TAG, "onRemoveFromCartEvent");

        if (authPreferences.isUserLoggedIn()) {
            removeFromCartPresenter.removeProductFromCart(authPreferences.getUserId(), event.getProductId());
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.cart_remove_item_not_logged), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        cartItemsCount.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        cartItemsCount.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCartItems(ProductListDVO products) {
        adapter.setItems(products.getProducts());
        cartItemsCount.setText(getResources().getQuantityString(R.plurals.cart_items_count, adapter.getItemCount(), adapter.getItemCount()));
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductRemoveSuccess() {
        Toast.makeText(getContext(), "Product was removed from user's cart", Toast.LENGTH_SHORT).show(); // TODO extract resource string
        cartPresenter.getCartItems(authPreferences.getUserId());
    }

    @Override
    public void onProductRemoveError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
