package com.task.basis.core;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import com.task.basis.R;

import butterknife.BindView;


public abstract class CoreSwipeActivity extends BaseActivity implements BaseView {

    private int layoutResId;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
        super.setContentView(R.layout.activity_core_swipe);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
