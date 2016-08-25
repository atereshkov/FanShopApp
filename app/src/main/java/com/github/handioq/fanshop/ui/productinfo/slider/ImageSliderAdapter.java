package com.github.handioq.fanshop.ui.productinfo.slider;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.model.dvo.ImageDVO;

import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    private List<ImageDVO> images;
    private LayoutInflater inflater;
    private Context context;

    public ImageSliderAdapter(Context context, List<ImageDVO> imageDTOs) {
        this.context = context;
        this.images = imageDTOs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slider_image, view, false);

        ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image_display);

        Glide.with(view.getContext())
                .load(images.get(position).getImage())
                .into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
