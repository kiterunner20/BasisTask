package com.task.basis.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.task.basis.R;
import com.task.basis.data.TaskDataList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasisTaskBinder extends ItemBinder<TaskDataList, BasisTaskBinder.ViewHolder> {

  @Override public BasisTaskBinder.ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
    return new BasisTaskBinder.ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_card, parent, false));
  }

  //Setting the individual items
  @Override public void bind(BasisTaskBinder.ViewHolder holder, TaskDataList item) {
    holder.countId.setText(item.getID());
    holder.textID.setText(item.getText());
  }

  @Override public boolean canBindData(Object item) {
    return item instanceof TaskDataList;
  }

  public class ViewHolder extends ItemViewHolder<TaskDataList> {

    @BindView(R.id.count_id) TextView countId;
    @BindView(R.id.text_id) TextView textID;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(v -> {

      });
    }
  }
}
