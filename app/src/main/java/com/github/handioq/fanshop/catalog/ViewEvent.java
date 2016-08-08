package com.github.handioq.fanshop.catalog;

public class ViewEvent {

    public final CatalogMvp.View catalogView;

    public ViewEvent(CatalogMvp.View catalogView) {
        this.catalogView = catalogView;
    }
}
