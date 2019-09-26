package com.task.basis.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.evernote.android.state.State;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.task.basis.BasisTaskApp;
import com.task.basis.R;
import com.task.basis.core.BaseActivity;
import com.task.basis.data.TaskData;
import com.task.basis.data.TaskDataList;
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

import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class BasisSwipeActivity extends BaseActivity implements BasisSwipeView, CardStackListener {

  //Dependency injection
  @Inject BasisSwipePresenter presenter;
  //Maintaining the state
  @State ArrayList<TaskDataList> basisTaskList = new ArrayList<>();

  //Binding the views to ignore findById every time
  @BindView(R.id.progressbar) ProgressBar progressBar;
  @BindView(R.id.card_stack_view) CardStackView cardStackView;
  @BindView(R.id.rewind_button) FloatingActionButton rewindButton;
  @BindView(R.id.progress_count) ProgressBar progressCount;
  @BindView(R.id.tv_error) TextView tvError;

  CardStackLayoutManager manager;
  BasisCardSwipeAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basis_swipe);
  }

  @Override protected void initIntentExtras(Bundle extras) {
    //NO OP
  }

  @Override protected void initDependencies() {
    BasisTaskApp.getBasisTaskComponent().inject(this);
  }

  //Attaching the views
  @Override protected void initViews() {
    presenter.attachView(this);
  }

  @Override protected void onReady() {
    if (basisTaskList == null || basisTaskList.size() == 0) {
      presenter.getJsonData();
    } else {
      setJsonData(basisTaskList);
    }
  }

  //Detaching the views while unsubscribe to avoid memmory leaks
  @Override protected void destroyPresenter() {
    presenter.detachView();
  }

  /* If api call is a success using CardStackView which extends recyclerview will take care of
   the swiping part. Then its set to the adapter via this cardStackLayoutManager*/
  @Override public void setJsonData(ArrayList<TaskDataList> taskModel) {
    if (taskModel != null) {
      this.basisTaskList = taskModel;
      setUpCardStackView();
      progressCount.setMax(basisTaskList.size());

      adapter = new BasisCardSwipeAdapter();
      adapter.registerBinder(new BasisTaskBinder());
      cardStackView.setAdapter(adapter);
      adapter.setData(basisTaskList);
      cardStackView.setLayoutManager(manager);
    } else {
      Toast.makeText(getApplicationContext(), "No data fetched", Toast.LENGTH_LONG).show();
    }
  }

  //Setting up the properties for CardStackLayoutManager
  public void setUpCardStackView() {

    manager = new CardStackLayoutManager(this, this);

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
  }

  @OnClick(R.id.previous_button) void previousButtonClicked() {

    RewindAnimationSetting setting = (new RewindAnimationSetting.Builder()).
        setDirection(Direction.Bottom).
        setDuration(Duration.Normal.duration).
        setInterpolator((new DecelerateInterpolator())).
        build();
    manager.setRewindAnimationSetting(setting);
    cardStackView.rewind();
  }

  @OnClick(R.id.next_button) void nextButtonClicked() {
    SwipeAnimationSetting setting =
        (new SwipeAnimationSetting.Builder()).setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator((new AccelerateInterpolator()))
            .build();
    manager.setSwipeAnimationSetting(setting);
    cardStackView.swipe();
  }

  @OnClick(R.id.rewind_button) void rewindButtonClicked() {
    if (manager != null) {
      manager.scrollToPosition(0);
      progressCount.setProgress(manager.getTopPosition() + 1);
    }
  }

  /*This methods are implemented and every time progressbar is updated for each of the override
     methods and progressbar is updated accordingly in the UI */

  @Override public void onCardDragging(Direction direction, float ratio) {

  }

  @Override public void onCardSwiped(Direction direction) {
    progressCount.setProgress(manager.getTopPosition());
  }

  @Override public void onCardRewound() {
    progressCount.setProgress(manager.getTopPosition() + 1);
  }

  @Override public void onCardCanceled() {
    progressCount.setProgress(manager.getTopPosition() + 1);
  }

  @Override public void onCardAppeared(View view, int position) {
    progressCount.setProgress(manager.getTopPosition() + 1);
  }

  @Override public void onCardDisappeared(View view, int position) {
    progressCount.setProgress(manager.getTopPosition() + 1);
  }

  //Methods implemented from BaseView. UI related things during network call.

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
    tvError.setVisibility(View.GONE);
  }

  @Override public void showError(String error) {
    progressBar.setVisibility(View.GONE);
    tvError.setText(error);
    tvError.setVisibility(View.VISIBLE);
  }

  @Override public void showContent() {
    progressBar.setVisibility(View.GONE);
    tvError.setVisibility(View.GONE);
  }

  @Override public void showEmpty(String message) {
    tvError.setVisibility(View.VISIBLE);
    tvError.setText(message);
  }
}
