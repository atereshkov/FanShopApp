package com.github.handioq.fanshop.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbind;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
