package com.task.basis.core;

public interface BaseView {

    void showProgress();

    void showError(String error);

    void showContent();

    void showEmpty(String error);

}
