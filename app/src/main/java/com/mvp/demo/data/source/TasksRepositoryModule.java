package com.mvp.demo.data.source;

import android.content.Context;

import com.mvp.demo.data.DataRepository;
import com.mvp.demo.data.Injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRepositoryModule {

    @Singleton
    @Provides
    DataRepository provideTasksLocalDataSource(Context context) {
        return Injection.provideTasksRepository(context);
    }

//    @Singleton
//    @Provides
//    DataRepository provideTasksRemoteDataSource() {
//        return Injection.provideTasksRepository(context);
//    }

}
