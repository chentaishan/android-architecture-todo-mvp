package com.mvp.demo.domain.usecase;


import com.mvp.demo.ActivityUtils;
import com.mvp.demo.data.DataRepository;
import com.mvp.demo.data.IDataSource;
import com.mvp.demo.domain.model.ResultModel;
import com.mvp.demo.mvp.UseCase;

import java.util.List;


public class GetCase1 extends UseCase<GetCase1.RequestValues, GetCase1.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetCase1(DataRepository tasksRepository) {
        mDataRepository = ActivityUtils.checkNotNull(tasksRepository, "mDataRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        //可以获取到requestValues请求参数
        mDataRepository.getTasks(new IDataSource.LoadDataCallback() {
            @Override
            public void onSuccess(List<String> list) {
                ResponseValue responseValue = new ResponseValue(new ResultModel(list));
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onFailure() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {

    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final ResultModel mStarsModel;

        public ResponseValue(ResultModel statistics) {
            mStarsModel = ActivityUtils.checkNotNull(statistics, "mStarsModel cannot be null!");
        }

        public ResultModel getStatistics() {
            return mStarsModel;
        }
    }
}
