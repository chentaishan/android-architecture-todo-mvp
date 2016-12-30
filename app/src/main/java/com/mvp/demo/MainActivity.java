package com.mvp.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvp.demo.dagger.TasksPresenterModule;
import com.mvp.demo.data.source.TasksRepositoryComponent;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends Activity implements Contract.View {

    private static final String TAG = "MainActivity";

    @Inject
    Contract.Presenter mActivityPresenter;

    private ProgressBar mLoadingView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingView = (ProgressBar) findViewById(R.id.loading);
        mTextView = (TextView) findViewById(R.id.list);
        TasksRepositoryComponent task = ((ToDoApplication) getApplication()).getTasksRepositoryComponent();

        DaggerTasksComponent.builder()
                .tasksRepositoryComponent(task)
                .tasksPresenterModule(new TasksPresenterModule(this,task.getTasksRepository()))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mActivityPresenter = presenter;
    }

    @Override
    public void setLoading(boolean isLoading) {
        mLoadingView.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityPresenter.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityPresenter.onDestroy();
    }

    @Override
    public void show(List<String> list) {
        Log.e(TAG,"list:"+list);
        mTextView.setText(list.toString());
        mTextView.setVisibility(View.VISIBLE);
    }
}
