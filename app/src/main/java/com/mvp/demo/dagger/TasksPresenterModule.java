package com.mvp.demo.dagger;

import com.mvp.demo.AcitivtyPresenter;
import com.mvp.demo.Contract;
import com.mvp.demo.data.DataRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 提供着
 */
@Module
public class TasksPresenterModule {

    private final Contract.View mView;
    private final DataRepository mDataRepository;

    public TasksPresenterModule(Contract.View view, DataRepository dataRepository) {
        mView = view;
        mDataRepository = dataRepository;
    }

    @Provides
    Contract.Presenter provideTasksPresenter(){
        return new AcitivtyPresenter(mView,mDataRepository);
    }

    @Provides
    DataRepository provideDataRepository(){
        return mDataRepository;
    }

    @Provides
    Contract.View provideTasksContractView() {
        return mView;
    }


}
