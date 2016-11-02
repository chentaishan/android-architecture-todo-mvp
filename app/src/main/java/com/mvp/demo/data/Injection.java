package com.mvp.demo.data;

import android.content.Context;

import com.mvp.demo.ActivityUtils;
import com.mvp.demo.domain.repository.FakeDataSource;
import com.mvp.demo.utils.BaseSchedulerProvider;
import com.mvp.demo.utils.SchedulerProvider;


public class Injection {

    public static DataRepository provideTasksRepository(Context context) {
        ActivityUtils.checkNotNull(context);
        return DataRepository.getInstance(FakeDataSource.getInstance());
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
