package com.mvp.demo;


import com.mvp.demo.domain.model.ResultModel;
import com.mvp.demo.domain.usecase.GetCase1;
import com.mvp.demo.mvp.UseCase;
import com.mvp.demo.mvp.UseCaseHandler;


public class AcitivtyPresenter implements Contract.Presenter {

    private final Contract.View mView;//view 接口 用于更新UI
    private final UseCaseHandler mUseCaseHandler;//当前用例层
    private final GetCase1 mGetCase1;//中间层 数据请求在这里

    public AcitivtyPresenter(UseCaseHandler useCaseHandler, Contract.View statisticsView, GetCase1 getStatistics) {
        mUseCaseHandler = ActivityUtils.checkNotNull(useCaseHandler, "useCaseHandler cannot be null!");
        mView = ActivityUtils.checkNotNull(statisticsView, "StatisticsView cannot be null!");
        mGetCase1 = ActivityUtils.checkNotNull(getStatistics, "getStatistics cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        startTask();
    }

    @Override
    public void onDestroy() {

    }


    private void startTask() {
        mUseCaseHandler.execute(mGetCase1, new GetCase1.RequestValues(), mGetCallback);
    }

    private UseCase.UseCaseCallback<GetCase1.ResponseValue> mGetCallback = new UseCase.UseCaseCallback<GetCase1.ResponseValue>() {

        @Override
        public void onSuccess(GetCase1.ResponseValue response) {
            ResultModel statistics = response.getStatistics();
            if (statistics.getList() == null) {
                return;
            }
            mView.show(statistics.getList());
        }

        @Override
        public void onError() {

        }
    };
}
