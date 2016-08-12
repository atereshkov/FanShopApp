package com.github.handioq.fanshop.productinfo;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.model.dto.ImageDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.Response;
import com.github.handioq.fanshop.productinfo.adapter.InfoAdapter;
import com.github.handioq.fanshop.productinfo.adapter.WrapContentViewPager;
import com.github.handioq.fanshop.productinfo.slider.ImageSliderAdapter;

import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import butterknife.BindView;

public class ProductInfoFragment extends BaseFragment implements ProductInfoMvp.View, ViewPager.OnPageChangeListener,
        AddToCartMvp.View {

    private final static String TAG = "ProductInfoFragment";

    private int selectedItemId;
    private InfoAdapter infoAdapter;
    private int dotsCount;
    private ImageView[] dots;

    private ProductDTO selectedProduct;

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

    @Inject
    ProductInfoMvp.Presenter productInfoPresenter;

    @Inject
    AddToCartMvp.Presenter addToCartPresenter;

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
        selectedItemId = this.getArguments().getInt("id");

        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        Context context = getActivity();

        List<Fragment> fragments = new Vector<Fragment>();

        Bundle bundle = new Bundle();
        bundle.putInt("id", selectedItemId);

        Fragment descriptionFragment = new DescriptionInfoFragment();
        descriptionFragment.setArguments(bundle);

        Fragment reviewsFragment = new ReviewsInfoFragment();
        reviewsFragment.setArguments(bundle);

        fragments.add(descriptionFragment);
        fragments.add(reviewsFragment);

        infoAdapter = new InfoAdapter(getActivity().getSupportFragmentManager(), fragments, context);
        descriptionPager.setAdapter(infoAdapter);
        tabLayout.setupWithViewPager(descriptionPager);

        addToCartPresenter.setView(this);

        productInfoPresenter.setView(this);
        productInfoPresenter.getProduct(selectedItemId);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addToCartPresenter.addProductToCart(5100, selectedProduct);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    private void initSlider(List<ImageDTO> imageDTOs) {

        imageSlider.setAdapter(new ImageSliderAdapter(getActivity(), imageDTOs));
        imageSlider.addOnPageChangeListener(this);

        dotsCount = imageDTOs.size();
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
    public void setProduct(ProductDTO productDTO) {
        selectedProduct = productDTO;

        getActivity().setTitle(productDTO.getName());
        initSlider(productDTO.getImageDTOs());

        infoItemPriceView.setText(getActivity().getString(R.string.catalog_price, productDTO.getPrice()));
        infoItemPriceView.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryTextBlack));

        descriptionView.setText(productDTO.getDescription());
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
        e.printStackTrace();
    }
}
