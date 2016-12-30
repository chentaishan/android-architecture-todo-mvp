package com.mvp.demo;


import com.mvp.demo.data.Injection;
import com.mvp.demo.utils.BaseSchedulerProvider;

import java.util.ArrayList;
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

    @Inject
    public AcitivtyPresenter(Contract.View statisticsView) {
        mView = ActivityUtils.checkNotNull(statisticsView, "StatisticsView cannot be null!");
        mSchedulerProvider = Injection.provideSchedulerProvider();
        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    void setupListeners() {
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
            public void call(Subscriber<? super List<String>> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(i + "--");
                }
                subscriber.onNext(list);
                subscriber.onCompleted();
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
