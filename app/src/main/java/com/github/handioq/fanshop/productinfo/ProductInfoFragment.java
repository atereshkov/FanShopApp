package com.github.handioq.fanshop.productinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.Image;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.productinfo.adapter.InfoAdapter;
import com.github.handioq.fanshop.productinfo.adapter.WrapContentViewPager;
import com.github.handioq.fanshop.productinfo.slider.ImageSliderAdapter;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;

public class ProductInfoFragment extends BaseFragment implements ProductInfoView, ViewPager.OnPageChangeListener {

    private final static String TAG = "ProductInfoFragment";

    private int selectedItemId;
    private ProductInfoPresenter productInfoPresenter;

    @BindView(R.id.imagePager)
    ViewPager imageSlider;

    @BindView(R.id.viewPagerCountDots)
    LinearLayout viewPagerCountDots;

    private int dotsCount;
    private ImageView[] dots;

    @BindView(R.id.view_pager_container)
    WrapContentViewPager descriptionPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private InfoAdapter infoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
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
        Log.e(TAG, "onViewCreated");

        Context context = getActivity();

        List<Fragment> fragments = new Vector<Fragment>();

        Bundle bundle = new Bundle();
        bundle.putInt("id", selectedItemId);

        Fragment descrFragment = Fragment.instantiate(context, DescriptionInfoFragment.class.getName());
        descrFragment.setArguments(bundle);

        fragments.add(descrFragment);
        fragments.add(Fragment.instantiate(context, ReviewsInfoFragment.class.getName()));
        infoAdapter = new InfoAdapter(getActivity().getSupportFragmentManager(), fragments, context);

        descriptionPager.setAdapter(infoAdapter);

        descriptionPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //descriptionPager.reMeasureCurrentPage(descriptionPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(descriptionPager);

        //tabLayout.addTab(tabLayout.newTab().setText("Description")); // TODO change hardcoded strings
        //tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                descriptionPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        productInfoPresenter = new ProductInfoPresenterImpl(this, ((FanShopApp) getActivity().getApplication()).getNetworkService());
        productInfoPresenter.getProduct(selectedItemId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    private void initSlider(List<Image> images) {

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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setProduct(Product product) {
        Log.e(TAG, "PRODUCT  ---> " + product.getId() + product.getName());

        initSlider(product.getImages());
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
