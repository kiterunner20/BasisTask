package com.task.basis.core;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V extends BaseView> {

    protected V view;
    private CompositeSubscription subscriptions;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
        if (null != subscriptions && subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
        }
    }

    protected boolean isViewAttached() {
        return this.view != null;
    }

    protected final void addToSubscription(Subscription subscription) {
        if (null == subscriptions) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    protected void showProgress() {
        if (isViewAttached()) {
            view.showProgress();
        }
    }

    protected void showError(String error) {
        if (isViewAttached()) {
            view.showError(error);
        }
    }

    protected boolean showContent() {
        if (isViewAttached()) {
            view.showContent();
            return true;
        }
        return false;
    }

}
