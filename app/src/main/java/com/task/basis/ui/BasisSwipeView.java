package com.task.basis.ui;

import com.task.basis.core.BaseView;
import com.task.basis.data.TaskDataList;
import java.util.ArrayList;

public interface BasisSwipeView extends BaseView {

  void setJsonData(ArrayList<TaskDataList> taskModel);
}
