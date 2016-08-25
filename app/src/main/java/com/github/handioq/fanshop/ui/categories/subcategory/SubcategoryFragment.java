package com.github.handioq.fanshop.ui.categories.subcategory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.ui.categories.subcategory.adapter.SubcategoryRecyclerAdapter;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.model.dvo.SubcategoryDVO;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class SubcategoryFragment extends BaseFragment implements SubcategoryMvp.View {

    private final String TAG = "SubcategoryFragment";

    @BindView(R.id.subcategory_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    private SubcategoryRecyclerAdapter adapter;

    private int selectedCategoryId;

    @Inject
    SubcategoryMvp.Presenter subcategoriesPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectedCategoryId = this.getArguments().getInt("id");

        return inflater.inflate(R.layout.fragment_subcategory, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCategoriesComponent().inject(this);

        adapter = new SubcategoryRecyclerAdapter(new ArrayList<SubcategoryDVO>());

        subcategoriesPresenter.setView(this);
        subcategoriesPresenter.getCategory(selectedCategoryId);

        initRecycler();
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoadCategoryProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadCategoryProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCategory(CategoryDVO category) {
        adapter.setSubcategories(category.getSubcategories());
        getActivity().setTitle(category.getName());
    }

    @Override
    public void showLoadCategoryError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
