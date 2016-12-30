package com.mvp.demo.dagger;

import com.mvp.demo.AcitivtyPresenter;
import com.mvp.demo.Contract;

import dagger.Module;
import dagger.Provides;

/**
 * 提供着
 */
@Module
public class TasksPresenterModule {

    private final Contract.View mView;

    public TasksPresenterModule(Contract.View view) {
        mView = view;
    }

    @Provides
    Contract.Presenter provideTasksPresenter(){
        return new AcitivtyPresenter(mView);
    }

    @Provides
    Contract.View provideTasksContractView() {
        return mView;
    }

}
