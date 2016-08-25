package com.github.handioq.fanshop.ui.productinfo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.handioq.R;

import java.util.List;

public class InfoAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private Context context;

    public InfoAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.pager_title_description);
            case 1:
                return context.getResources().getString(R.string.pager_title_reviews);
        }
        return null;
    }

}
