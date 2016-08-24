package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.dvo.SpecificationDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class SpecificationPresenter implements SpecificationMvp.Presenter, SpecificationMvp.Model.Callback {

    private SpecificationMvp.View specificationView;
    private SpecificationMvp.Model specificationModel;
    private NetworkService networkService;

    private final static String TAG = "SpecificationPresenter";

    @Inject
    public SpecificationPresenter(NetworkService networkService) {
        specificationModel = new SpecificationModel(networkService);
        specificationModel.setCallback(this);
    }

    @Override
    public void onSpecificationLoaded(SpecificationDVO specification) {
        specificationView.hideProgress();
        specificationView.setSpecification(specification);
    }

    @Override
    public void onSpecificationLoadError(Throwable error) {
        specificationView.hideProgress();
        specificationView.onError(error);
    }

    @Override
    public void onSpecificationLoadCompleted() {
        specificationView.hideProgress();
    }

    @Override
    public void getSpecification(int id) {
        if (specificationView != null) {
            specificationView.showProgress();
        }

        specificationModel.getSpecification(id);
    }

    @Override
    public void setView(SpecificationMvp.View view) {
        this.specificationView = view;
    }
}