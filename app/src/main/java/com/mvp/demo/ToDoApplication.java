package com.mvp.demo;

import android.app.Application;

import com.mvp.demo.dagger.ApplicationModule;
import com.mvp.demo.data.source.DaggerTasksRepositoryComponent;
import com.mvp.demo.data.source.TasksRepositoryComponent;

/**
 * Created by whiskeyfei on 16/12/27.
 */

public class ToDoApplication extends Application {

    private TasksRepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mRepositoryComponent = DaggerTasksRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public TasksRepositoryComponent getTasksRepositoryComponent() {
        return mRepositoryComponent;
    }
}
