package com.mvp.demo.data.source;

import com.mvp.demo.data.DataRepository;
import com.mvp.demo.data.Injection;
import com.mvp.demo.domain.repository.Local;
import com.mvp.demo.domain.repository.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRepositoryModule {

    @Singleton
    @Provides
    @Local
    DataRepository provideTasksLocalDataSource() {
        return Injection.provideTasksRepository();
    }

    @Singleton
    @Provides
    @Remote
    DataRepository provideTasksRemoteDataSource() {
        return Injection.provideTasksTemoteRepository();
    }

}
