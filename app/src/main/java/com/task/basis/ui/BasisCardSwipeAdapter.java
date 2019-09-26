package com.task.basis.ui;

import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.task.basis.model.Datum;

import java.util.List;

public class BasisCardSwipeAdapter extends RecyclerAdapter {

    private DataListManager<Datum> dataListManager;

    public BasisCardSwipeAdapter() {

        dataListManager = new DataListManager<>(this);

        addDataManager(dataListManager);
    }


    public void setData(List<Datum> basisTaskList) {
        dataListManager.addAll(basisTaskList);
    }


    public DataListManager<Datum> getDataListManager() {
        return dataListManager;
    }
}
