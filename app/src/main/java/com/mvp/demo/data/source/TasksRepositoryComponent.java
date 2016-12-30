package com.mvp.demo.data.source;


import com.mvp.demo.dagger.ApplicationModule;
import com.mvp.demo.data.DataRepository;
import com.mvp.demo.domain.repository.Remote;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {TasksRepositoryModule.class, ApplicationModule.class})
public interface TasksRepositoryComponent {
    @Remote
    DataRepository getTasksRepository();
}
