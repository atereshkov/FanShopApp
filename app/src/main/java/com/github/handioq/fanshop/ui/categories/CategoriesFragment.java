package com.github.handioq.fanshop.ui.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dvo.CategoryListDVO;
import com.github.handioq.fanshop.ui.categories.adapter.CategoriesRecyclerAdapter;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.util.ScreenDimensionsHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoriesFragment extends BaseFragment implements CategoriesMvp.View {

    private final String TAG = "CatalogFragment";

    @BindView(R.id.categories_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoriesRecyclerAdapter adapter;

    @Inject
    CategoriesMvp.Presenter categoriesPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getCategoriesComponent().inject(this);

        adapter = new CategoriesRecyclerAdapter(new ArrayList<CategoryDVO>());

        categoriesPresenter.setView(this);
        categoriesPresenter.getCategories();

        initRecycler();
    }

    private void initRecycler() {
        //layoutManager = new LinearLayoutManager(getContext()); // 1 card in a row
        ScreenDimensionsHelper screenDimensionsHelper = new ScreenDimensionsHelper(getActivity());
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), screenDimensionsHelper.getCategoriesCount());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoadCategoriesProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadCategoriesProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCategories(CategoryListDVO categories) {
        adapter.setCategories(categories.getCategories());
    }

    @Override
    public void showLoadCategoriesError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
