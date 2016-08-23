package com.github.handioq.fanshop.ui.wishlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.cart.interaction.AddToCartClickEvent;
import com.github.handioq.fanshop.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.ui.wishlist.adapter.WishlistRecyclerAdapter;
import com.github.handioq.fanshop.util.AuthPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class WishlistFragment extends BaseFragment implements WishlistMvp.View, AddToCartMvp.View {

    @BindView(R.id.wishlist_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private WishlistRecyclerAdapter adapter;

    @Inject
    WishlistMvp.Presenter wishlistPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

    @Inject
    AuthPreferences authPreferences;

    private final String TAG = "WishlistFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getWishlistComponent().inject(this);

        adapter = new WishlistRecyclerAdapter(new ArrayList<ProductDVO>());

        addToCartPresenter.setView(this);
        wishlistPresenter.setView(this);
        wishlistPresenter.getWishlist(authPreferences.getUserId());

        initRecycler();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 card in a row
        //ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());
        //final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCardsCount()); // n cards in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoadWishlistProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadWishlistProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setWishlist(List<ProductDVO> products) {
        adapter.setItems(products);
    }

    @Override
    public void showLoadWishlistError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onProductAddSuccess(Response response) {
        Toast.makeText(getContext(), response.getStatusMessage() + " - " + response.getStatusCode(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onProductAddSuccess");
    }

    @Override
    public void onProductAddError(Throwable e) {
        Log.e(TAG, e.toString());
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
    public void onAddToCartEvent(AddToCartClickEvent event) {
        if (authPreferences.isUserLoggedIn()) {
            addToCartPresenter.addProductToCart(authPreferences.getUserId(), event.getProduct().getId());
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.cart_add_item_not_logged), Toast.LENGTH_SHORT).show();
        }

        Log.i(TAG, "onAddToCartEvent");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}

