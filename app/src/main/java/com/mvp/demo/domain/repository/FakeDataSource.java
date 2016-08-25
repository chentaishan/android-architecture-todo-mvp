package com.mvp.demo.domain.repository;

import com.mvp.demo.data.IDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现接口，真实请求数据
 */
public class FakeDataSource implements IDataSource {

    private static FakeDataSource INSTANCE;

    private FakeDataSource() {
    }

    public static FakeDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeDataSource();
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
            list.add(String.valueOf(i));
        }
        return list;
    }
}
