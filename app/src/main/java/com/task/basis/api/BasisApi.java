package com.task.basis.api;

import com.task.basis.BasisTaskService;
import com.task.basis.model.TaskModel;

import javax.inject.Inject;

import rx.Observable;

public class BasisApi {

  private final BasisTaskService basisTaskService;

  //Constructor injection, BasisTaskService object dependency is injected with the inject annotation
  //The same basisTaskService object can be used without creating the new Instance every time
  @Inject public BasisApi(BasisTaskService basisTaskService) {
    this.basisTaskService = basisTaskService;
  }

  //With RxJavaAdapter
  public Observable<TaskModel> getJsonData() {
    return basisTaskService.getJsonData();
  }
}
