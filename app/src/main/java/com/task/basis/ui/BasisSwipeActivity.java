package com.task.basis.ui;

import android.os.Bundle;

import com.task.basis.BasisTaskApp;
import com.task.basis.R;
import com.task.basis.core.CoreSwipeActivity;
import com.task.basis.model.TaskModel;

import javax.inject.Inject;

public class BasisSwipeActivity extends CoreSwipeActivity implements BasisSwipeView {

    @Inject
    BasisSwipePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis_swipe);
    }

    @Override
    protected void initIntentExtras(Bundle extras) {

    }

    @Override
    protected void initDependencies() {
        BasisTaskApp.getBasisTaskComponent().inject(this);
    }

    @Override
    protected void initViews() {
        presenter.attachView(this);
    }

    @Override
    protected void onReady() {
        presenter.getJsonData();
    }

    @Override
    protected void destroyPresenter() {

    }


    @Override
    public void setJsonData(TaskModel taskModel) {

    }
}
