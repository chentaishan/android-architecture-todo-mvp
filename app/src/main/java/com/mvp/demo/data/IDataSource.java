package com.mvp.demo.data;

import java.util.List;

public interface IDataSource {
	//test
	interface LoadDataCallback {

		void onSuccess(List<String> list);

		void onFailure();
	}
	
	 //test
    void getTasks(LoadDataCallback callback);
}
