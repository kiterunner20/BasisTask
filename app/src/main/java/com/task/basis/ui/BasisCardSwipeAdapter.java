package com.task.basis.ui;

import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.task.basis.data.TaskDataList;

import java.util.List;

public class BasisCardSwipeAdapter extends RecyclerAdapter {

  private DataListManager<TaskDataList> dataListManager;

  public BasisCardSwipeAdapter() {

    dataListManager = new DataListManager<>(this);

    addDataManager(dataListManager);
  }

  public void setData(List<TaskDataList> basisTaskList) {
    dataListManager.addAll(basisTaskList);
  }
}
