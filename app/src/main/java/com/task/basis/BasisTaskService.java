package com.task.basis;

import com.task.basis.model.TaskModel;

import retrofit2.http.GET;
import rx.Observable;

public interface BasisTaskService {

    @GET("HiringTask.json")
    Observable<TaskModel> getJsonData();
}
