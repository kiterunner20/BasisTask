package com.task.basis.core;

public abstract class ErrorAction extends BaseErrorAction {
    @Override
    public void call(Throwable throwable) {
        super.call(throwable);
        handleError(throwable);
    }

    protected abstract void handleError(Throwable throwable);
}
