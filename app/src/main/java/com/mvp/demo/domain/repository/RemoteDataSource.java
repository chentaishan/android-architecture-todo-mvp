package com.mvp.demo.domain.repository;

import android.util.Log;

import com.mvp.demo.data.IDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现接口，真实请求数据
 */
public class RemoteDataSource implements IDataSource {
    private static final String TAG = "RemoteDataSource";

    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {
        Log.d(TAG,"RemoteDataSource");
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(final LoadDataCallback callback) {
        callback.onSuccess(getList());
    }

    private List<String> getList() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(i + TAG );
        }
        return list;
    }
}
