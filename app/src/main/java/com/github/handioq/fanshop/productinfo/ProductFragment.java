package com.github.handioq.fanshop.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.model.dto.ImageDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ImageDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.productinfo.adapter.InfoAdapter;
import com.github.handioq.fanshop.productinfo.adapter.WrapContentViewPager;
import com.github.handioq.fanshop.productinfo.slider.ImageSliderAdapter;
import com.github.handioq.fanshop.ui.wishlist.interaction.AddToWishlistMvp;
import com.github.handioq.fanshop.ui.wishlist.interaction.RemoveWishlistMvp;
import com.github.handioq.fanshop.util.AuthPreferences;

import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductFragment extends BaseFragment implements ProductMvp.View, ViewPager.OnPageChangeListener,
        AddToCartMvp.View, AddToWishlistMvp.View, RemoveWishlistMvp.View {

    private final static String TAG = "ProductFragment";
    private final static String ARGUMENT_ID = "id";

    private int selectedItemId;
    private InfoAdapter infoAdapter;
    private int dotsCount;
    private ImageView[] dots;

    private ProductDVO selectedProduct;

    @BindView(R.id.image_pager)
    ViewPager imageSlider;

    @BindView(R.id.view_pager_count_dots)
    LinearLayout viewPagerCountDots;

    @BindView(R.id.view_pager_container)
    WrapContentViewPager descriptionPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.info_item_price)
    TextView infoItemPriceView;

    @BindView(R.id.progressbar_info)
    ProgressBar progressBarView;

    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    @BindView(R.id.info_description)
    TextView descriptionView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.fav_button)
    ImageButton favoriteButton;

    @Inject
    ProductMvp.Presenter productInfoPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

    @Inject
    AddToWishlistMvp.Presenter addToWishlistPresenter;

    @Inject
    RemoveWishlistMvp.Presenter removeWishlistPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static ProductFragment newInstance(int id) {
        ProductFragment fragment = new ProductFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);

        ((FanShopApp) getContext().getApplicationContext()).getProductInfoComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectedItemId = this.getArguments().getInt(ARGUMENT_ID);

        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(SpecificationFragment.newInstance(selectedItemId));
        fragments.add(ReviewsFragment.newInstance(selectedItemId));

        infoAdapter = new InfoAdapter(getActivity().getSupportFragmentManager(), fragments, getActivity());
        descriptionPager.setAdapter(infoAdapter);
        tabLayout.setupWithViewPager(descriptionPager);

        removeWishlistPresenter.setView(this);
        addToCartPresenter.setView(this);
        addToWishlistPresenter.setView(this);
        productInfoPresenter.setView(this);
        productInfoPresenter.getProduct(selectedItemId);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (authPreferences.isUserLoggedIn()) {
                    addToCartPresenter.addProductToCart(authPreferences.getUserId(), selectedProduct.getId());
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.cart_add_item_not_logged), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initSlider(List<ImageDVO> images) {

        imageSlider.setAdapter(new ImageSliderAdapter(getActivity(), images));
        imageSlider.addOnPageChangeListener(this);

        dotsCount = images.size();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.nonselecteditem_dot, null));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            viewPagerCountDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.nonselecteditem_dot, null));
        }

        dots[position].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selecteditem_dot, null));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.fav_button)
    void onFavoriteClick() {
        if (authPreferences.isUserLoggedIn()) {
            if (selectedProduct.isUserFavorite()) {
                removeWishlistPresenter.removeProduct(authPreferences.getUserId(), selectedProduct.getId());
            } else {
                addToWishlistPresenter.addProductToWishlist(authPreferences.getUserId(), selectedProduct.getId());
            }
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.wishlist_add_item_not_logged), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProductAddedToWishlist(Response response) {
        Toast.makeText(getActivity(), response.getStatusMessage() + " - " + response.getStatusCode(), Toast.LENGTH_SHORT).show();
        favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
    }

    @Override
    public void onWishlistAddError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void showProgress() {
        progressBarView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBarView.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProduct(ProductDVO product) {
        selectedProduct = product;

        getActivity().setTitle(product.getName());
        initSlider(product.getImages());

        infoItemPriceView.setText(getActivity().getString(R.string.catalog_price, product.getPrice()));
        infoItemPriceView.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryTextBlack));
        descriptionView.setText(product.getDescription());

        if (selectedProduct.isUserFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onProductAddSuccess(Response response) {
        Toast.makeText(getContext(), response.getStatusMessage() + " - " + response.getStatusCode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductAddError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onProductRemovedFromWishlist() {
        Toast.makeText(getContext(), "Product removed from wishlist", Toast.LENGTH_SHORT).show();
        favoriteButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    @Override
    public void onWishlistRemoveError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
