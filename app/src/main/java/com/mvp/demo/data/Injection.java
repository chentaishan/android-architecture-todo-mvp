package com.mvp.demo.data;

import com.mvp.demo.domain.repository.FakeDataSource;
import com.mvp.demo.domain.repository.RemoteDataSource;
import com.mvp.demo.utils.BaseSchedulerProvider;
import com.mvp.demo.utils.SchedulerProvider;


public class Injection {

    public static DataRepository provideTasksRepository() {
        return DataRepository.getInstance(FakeDataSource.getInstance());
    }

    public static DataRepository provideTasksTemoteRepository() {
        return DataRepository.getInstance(RemoteDataSource.getInstance());
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
