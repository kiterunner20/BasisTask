package com.task.basis.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class TaskData implements Parcelable {

  List<TaskDataList> taskDataList;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(taskDataList);
  }

  public TaskData() {
  }

  public void setTaskDataList(List<TaskDataList> taskDataList) {
    this.taskDataList = taskDataList;
  }

  public List<TaskDataList> getTaskDataList() {
    return taskDataList;
  }

  public TaskData(Parcel in) {
    this.taskDataList = in.createTypedArrayList(TaskDataList.CREATOR);
  }

  public static final Parcelable.Creator<TaskData> CREATOR = new Parcelable.Creator<TaskData>() {

    @Override public TaskData createFromParcel(Parcel source) {
      return new TaskData(source);
    }

    @Override public TaskData[] newArray(int size) {
      return new TaskData[size];
    }
  };
}
