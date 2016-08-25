package com.mvp.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.mvp.demo.data.Injection;

import java.util.List;

public class MainActivity extends Activity implements Contract.View {
    private static final String TAG = "MainActivity";
    private Contract.Presenter mActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityPresenter = new AcitivtyPresenter(Injection.provideUseCaseHandler(),
                this,
                Injection.provideGetStatistics(getApplicationContext()));
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mActivityPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityPresenter.onDestroy();
    }

    @Override
    public void show(List<String> list) {
        Log.e(TAG,"list:"+list);
    }
}
