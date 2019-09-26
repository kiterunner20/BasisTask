package com.task.basis.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskDataList implements Parcelable {

  String id;
  String text;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(text);
  }

  public TaskDataList() {

  }

  public void setId(String id) {
    this.id = id;
  }

  public String getID() {
    return id;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public TaskDataList(Parcel in) {
    id = in.readString();
    text = in.readString();
  }

  public static final Parcelable.Creator<TaskDataList> CREATOR =
      new Parcelable.Creator<TaskDataList>() {

        @Override public TaskDataList createFromParcel(Parcel source) {
          return new TaskDataList(source);
        }

        @Override public TaskDataList[] newArray(int size) {
          return new TaskDataList[size];
        }
      };
}
