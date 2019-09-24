package com.task.basis;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import icepick.Icepick;


public abstract class CoreSwipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            initIntentExtras(getIntent().getExtras());
        }
        Icepick.restoreInstanceState(this, savedInstanceState);
        initDependencies();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroy() {
        destroyPresenter();
        super.onDestroy();
    }

    @Override
    public void setContentView(@LayoutRes int resLayout) {
        super.setContentView(resLayout);
        setUpViews();
    }


    void setUpViews() {
        ButterKnife.bind(this);
        initViews();
        onReady();
    }

    protected abstract void initIntentExtras(Bundle extras);

    //Dependency injection
    protected abstract void initDependencies();

    //Called after onCreate for intiallizing all the views
    protected abstract void initViews();

    //Intiallize the presenter and the UI related tasks
    protected abstract void onReady();

    //Destroying the presenter

    protected abstract void destroyPresenter();
}
