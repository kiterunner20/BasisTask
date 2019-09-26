package com.task.basis.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.evernote.android.state.State;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.task.basis.BasisTaskApp;
import com.task.basis.R;
import com.task.basis.core.BaseActivity;
import com.task.basis.model.Datum;
import com.task.basis.model.TaskModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class BasisSwipeActivity extends BaseActivity implements BasisSwipeView, CardStackListener {

    @Inject
    BasisSwipePresenter presenter;
    @State
    ArrayList<Datum> basisTaskList = new ArrayList<>();
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.card_stack_view)
    CardStackView cardStackView;
    @BindView(R.id.rewind_button)
    FloatingActionButton rewindButton;
    @BindView(R.id.progress_count)
    ProgressBar progressCount;

    CardStackLayoutManager manager;
    BasisCardSwipeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basis_swipe);
        setUpCardStackView();
    }

    public void setUpCardStackView() {
        initialize();
    }

    private void initialize() {


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onReady() {
        presenter.getJsonData();

        if (manager != null) {
            progressCount.setProgress(basisTaskList.size() - manager.getTopPosition());
        }


    }


    @Override
    protected void destroyPresenter() {
        presenter.detachView();
    }


    @Override
    public void setJsonData(TaskModel taskModel) {
        this.basisTaskList = (ArrayList<Datum>) taskModel.getData();
        manager = new CardStackLayoutManager(this);

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        RecyclerView.ItemAnimator itemAnimator = cardStackView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }

        progressCount.setMax(basisTaskList.size());


        adapter = new BasisCardSwipeAdapter();
        adapter.registerBinder(new BasisTaskBinder());
        cardStackView.setAdapter(adapter);
        adapter.setData(basisTaskList);
        cardStackView.setLayoutManager(manager);

    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

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

    @OnClick(R.id.previous_button)
    void previousButtonClicked() {

        RewindAnimationSetting setting = (new RewindAnimationSetting.Builder()).
                setDirection(Direction.Bottom).
                setDuration(Duration.Normal.duration).
                setInterpolator((Interpolator) (new DecelerateInterpolator())).
                build();
        manager.setRewindAnimationSetting(setting);
        cardStackView.rewind();
    }

    @OnClick(R.id.next_button)
    void nextButtonClicked() {
        SwipeAnimationSetting setting = (new SwipeAnimationSetting.Builder()).setDirection(Direction.Right).setDuration(Duration.Normal.duration).setInterpolator((Interpolator) (new AccelerateInterpolator())).build();
        manager.setSwipeAnimationSetting(setting);
        cardStackView.swipe();
    }

    @OnClick(R.id.rewind_button)
    void rewindButtonClicked() {
    }
}
