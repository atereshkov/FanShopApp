package com.github.handioq.fanshop.productinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ProductInfoFragment extends BaseFragment implements ProductInfoView, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private final static String TAG = "ProductInfoFragment";

    @BindView(R.id.slider)
    SliderLayout imageSlider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSlider();
    }

    private void initSlider(){
        /* HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");*/

        List<String> urls = new ArrayList<>(); // test
        urls.add("https://img2.wbstatic.net/large/new/3120000/3121442-1.jpg");
        urls.add("https://img2.wbstatic.net/large/new/3120000/3121442-2.jpg");
        urls.add("https://img2.wbstatic.net/large/new/3120000/3121442-3.jpg");
        urls.add("https://img2.wbstatic.net/large/new/3120000/3121442-4.jpg");

        for(String url : urls){
            TextSliderView textSliderView = new TextSliderView(getActivity());

            textSliderView
                    .description("no description")
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop) // FitCenterCrop if large sizes in height
                    .setOnSliderClickListener(this);

            imageSlider.addSlider(textSliderView);
        }

        imageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imageSlider.addOnPageChangeListener(this);

        imageSlider.stopAutoCycle(); // stop auto sliding

        //imageSlider.setCustomIndicator((PagerIndicator) getView().findViewById(R.id.custom_indicator));
        imageSlider.getPagerIndicator().setDefaultIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary), Color.WHITE); // specify the colors

        /* Animation */
        imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void showProgress() {
        
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setProduct(Product product) {

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        imageSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        //Log.d("Image Slider", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
