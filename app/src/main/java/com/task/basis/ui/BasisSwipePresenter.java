package com.task.basis.ui;

import com.task.basis.api.BasisApi;
import com.task.basis.core.BasePresenter;
import com.task.basis.core.ErrorAction;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasisSwipePresenter extends BasePresenter<BasisSwipeView> {

    public BasisApi basisApi;

    // Injecting the BasisApi instance via Dagger annotation
    @Inject
    public BasisSwipePresenter(BasisApi basisApi) {
        this.basisApi = basisApi;
    }

    /*RxJava- From the observable stream disposible object will be subsribing to the stream
      The network related operation is performed on the io thread to avoid threading issues.
      RxJava does the work of multithreading by keeping io thread and main thread separately
      Subscription object is also destroyed by calling unsubscribe to avoid the memmory leaks */

    public void getJsonData() {
        showProgress();
        Subscription disposible = basisApi.getJsonData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataModel -> {
                            showContent();
                            if (dataModel != null) {
                                view.setJsonData(dataModel);
                            } else {
                                view.showEmpty("No data found");
                            }
                        },
                        new ErrorAction() {
                            @Override
                            protected void handleError(Throwable throwable) {
                                view.showError("Error in fetching");
                            }
                        });
        addToSubscription(disposible);


    }
}
