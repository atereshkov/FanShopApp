package com.github.handioq.fanshop.catalog;

public class ViewEvent {

    public final CatalogMvp.CatalogView catalogView;

    public ViewEvent(CatalogMvp.CatalogView catalogView) {
        this.catalogView = catalogView;
    }
}
