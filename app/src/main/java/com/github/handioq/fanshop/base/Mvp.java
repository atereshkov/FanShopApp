package com.github.handioq.fanshop.base;

public interface Mvp {

    interface Model {

    }

    interface View {

    }

    interface Presenter<V> {

        void setView(V view);

    }
}
