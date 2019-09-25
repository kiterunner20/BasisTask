package com.task.basis.core;

import android.util.Log;

import androidx.annotation.CallSuper;

import java.net.UnknownHostException;

import rx.functions.Action1;

public class BaseErrorAction implements Action1<Throwable> {

    @CallSuper
    @Override
    public void call(Throwable throwable) {
        if (!(throwable instanceof UnknownHostException)) {
            Log.v("Exception", throwable.toString());
        }
    }
}
