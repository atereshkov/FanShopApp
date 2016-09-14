package com.github.handioq.fanshop.ui.productinfo;

import com.github.handioq.fanshop.model.dvo.SpecificationDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class SpecificationPresenter implements SpecificationMvp.Presenter, SpecificationMvp.Model.Callback {

    private SpecificationMvp.View view;
    private SpecificationMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "SpecificationPresenter";

    @Inject
    public SpecificationPresenter(NetworkService networkService) {
        model = new SpecificationModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onSpecificationLoaded(SpecificationDVO specification) {
        if (view != null) {
            view.hideProgress();
            view.setSpecification(specification);
        }
    }

    @Override
    public void onSpecificationLoadError(Throwable error) {
        if (view != null) {
            view.hideProgress();
            view.onError(error);
        }
    }

    @Override
    public void onSpecificationLoadCompleted() {
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void getSpecification(int id) {
        if (view != null) {
            view.showProgress();
        }

        model.getSpecification(id);
    }

    @Override
    public void setView(SpecificationMvp.View view) {
        this.view = view;
    }
}