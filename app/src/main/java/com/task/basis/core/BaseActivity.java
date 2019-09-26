package com.task.basis.core;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.evernote.android.state.StateSaver;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            initIntentExtras(getIntent().getExtras());
        }
        StateSaver.restoreInstanceState(this, savedInstanceState);
        initDependencies();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);
        setUpViews();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroy() {
        destroyPresenter();
        super.onDestroy();
    }


    void setUpViews() {
        ButterKnife.bind(this);
        initViews();
        onReady();
    }

    protected abstract void initIntentExtras(Bundle extras);

    //Dependency injection is done in this method
    protected abstract void initDependencies();

    //Called after onCreate for intiallizing all the views
    protected abstract void initViews();

    //Intiallize the presenter and the UI related tasks
    protected abstract void onReady();

    //Destroying the presenter

    protected abstract void destroyPresenter();

}
