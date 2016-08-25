package com.mvp.demo.data;

import android.content.Context;

import com.mvp.demo.ActivityUtils;
import com.mvp.demo.domain.repository.FakeDataSource;
import com.mvp.demo.domain.usecase.GetCase1;
import com.mvp.demo.mvp.UseCaseHandler;


public class Injection {

    public static DataRepository provideTasksRepository(Context context) {
        ActivityUtils.checkNotNull(context);
        return DataRepository.getInstance(FakeDataSource.getInstance());
    }
    
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
    
    public static GetCase1 provideGetStatistics(Context context) {
        return new GetCase1(provideTasksRepository(context));
    }

}
