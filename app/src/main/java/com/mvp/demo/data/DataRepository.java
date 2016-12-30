package com.mvp.demo.data;

import com.mvp.demo.ActivityUtils;

import java.util.List;


public class DataRepository implements IDataSource {
    private static DataRepository INSTANCE = null;

    private final IDataSource mTasksRemoteDataSource;


    private DataRepository(IDataSource tasksRemoteDataSource) {
        mTasksRemoteDataSource = ActivityUtils.checkNotNull(tasksRemoteDataSource);
    }

    public static DataRepository getInstance(IDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

//    public static void destroyInstance() {
//        INSTANCE = null;
//    }

    @Override
    public void getTasks(final LoadDataCallback callback) {
        mTasksRemoteDataSource.getTasks(new LoadDataCallback() {

            @Override
            public void onSuccess(List<String> list) {
                callback.onSuccess(list);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

}