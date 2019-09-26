package com.task.basis.api;

import com.task.basis.BasisTaskService;
import com.task.basis.data.TaskData;
import com.task.basis.data.TaskDataList;
import com.task.basis.model.Datum;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import rx.Observable;

public class BasisApi {

  private final BasisTaskService basisTaskService;
  TaskData taskData;

  //Constructor injection, BasisTaskService object dependency is injected with the inject annotation
  //The same basisTaskService object can be used without creating the new Instance every time
  @Inject public BasisApi(BasisTaskService basisTaskService) {
    this.basisTaskService = basisTaskService;
  }

  //From the serializable observable stream its mapped to TaskData which is Parcelable class.
  //Returning parcelable TaskData object to the presenter


  public Observable<TaskData> getJsonData() {
    return basisTaskService.getJsonData().map(taskModel -> {

      if (taskModel != null) {
        if (taskModel.getData() != null) {
          taskData = new TaskData();
          List<TaskDataList> taskDataLists = new ArrayList<>();
          for (Datum datum : taskModel.getData()) {
            TaskDataList list = new TaskDataList();
            list.setId(datum.getId());
            list.setText(datum.getText());
            taskDataLists.add(list);
          }

          taskData.setTaskDataList(taskDataLists);
        }
      }
      return taskData;
    });
  }
}
