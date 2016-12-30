package com.mvp.demo;


import com.mvp.demo.dagger.TasksPresenterModule;
import com.mvp.demo.data.source.TasksRepositoryComponent;
import com.mvp.demo.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = TasksRepositoryComponent.class, modules = TasksPresenterModule.class)
public interface TasksComponent {
	
    void inject(MainActivity activity);

}
