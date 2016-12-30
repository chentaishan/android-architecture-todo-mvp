package com.mvp.demo;


import com.mvp.demo.data.DataRepository;
import com.mvp.demo.data.IDataSource;
import com.mvp.demo.data.Injection;
import com.mvp.demo.utils.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class AcitivtyPresenter implements Contract.Presenter {

    private final Contract.View mView;//view接口 用于更新UI
    private final BaseSchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    private DataRepository mDataRepository;

    @Inject
    public AcitivtyPresenter(Contract.View statisticsView,DataRepository dataRepository) {
        mView = ActivityUtils.checkNotNull(statisticsView, "StatisticsView cannot be null!");
        mSchedulerProvider = Injection.provideSchedulerProvider();
        mDataRepository = dataRepository;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        startTask();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void onDestroy() {

    }

    private void startTask() {
        mSubscriptions.clear();
        Subscription subscription = getObservable().subscribe(getSubscriber());
        mSubscriptions.add(subscription);
    }

    private Observable<List<String>> getObservable() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(final Subscriber<? super List<String>> subscriber) {
                mDataRepository.getTasks(new IDataSource.LoadDataCallback() {
                    @Override
                    public void onSuccess(List<String> list) {
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFailure() {
                        subscriber.onError(new Exception());
                    }
                });
            }
        })
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui());
    }


    private Subscriber<List<String>> getSubscriber() {
        return new Subscriber<List<String>>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.setLoading(true);
            }

            @Override
            public void onCompleted() {
                mView.setLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                mView.setLoading(false);
            }

            @Override
            public void onNext(List<String> list) {
                mView.show(list);
            }
        };
    }


}
