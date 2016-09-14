package com.github.handioq.fanshop.ui.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.github.handioq.fanshop.model.dvo.SpecificationDVO;
import com.github.handioq.fanshop.util.ErrorUtils;
import com.github.handioq.fanshop.util.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class SpecificationFragment extends BaseFragment implements SpecificationMvp.View {

    private int productId;

    private final static String TAG = "SpecificationFragment";
    private final static String ARGUMENT_ID = "id";

    @BindView(R.id.color_text)
    TextView colorTextView;

    @BindView(R.id.country_text)
    TextView countryTextView;

    @BindView(R.id.sizes_text)
    TextView sizesTextView;

    @BindView(R.id.brand_text)
    TextView brandTextView;

    @BindView(R.id.code_text)
    TextView codeTextView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    SpecificationMvp.Presenter specificationPresenter;

    public static SpecificationFragment newInstance(int id) {
        SpecificationFragment fragment = new SpecificationFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        ((FanShopApp) getContext().getApplicationContext()).getProductInfoComponent().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_info_specification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBundle(getArguments());

        specificationPresenter.setView(this);
        specificationPresenter.getSpecification(productId);
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            productId = bundle.getInt(ARGUMENT_ID);
        }
    }

    private void hideInfoView(boolean hide) {
        if (hide) {
            colorTextView.setVisibility(View.GONE);
            countryTextView.setVisibility(View.GONE);
            brandTextView.setVisibility(View.GONE);
            codeTextView.setVisibility(View.GONE);
        } else {
            colorTextView.setVisibility(View.VISIBLE);
            countryTextView.setVisibility(View.VISIBLE);
            brandTextView.setVisibility(View.VISIBLE);
            codeTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        //hideInfoView(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //hideInfoView(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setSpecification(SpecificationDVO specification) {
        colorTextView.setText(getString(R.string.specification_value_color, specification.getColor()));
        countryTextView.setText(getString(R.string.specification_value_country, specification.getCountry()));
        brandTextView.setText(getString(R.string.specification_value_brand, specification.getBrand()));
        codeTextView.setText(getString(R.string.specification_value_code, specification.getCode()));
        sizesTextView.setText(StringUtils.getStringSizes(specification.getSizes()));
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }
}