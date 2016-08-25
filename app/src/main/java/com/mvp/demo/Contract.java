package com.mvp.demo;

import com.mvp.demo.mvp.BasePresenter;
import com.mvp.demo.mvp.BaseView;

import java.util.List;


public interface Contract {
	interface View extends BaseView<Presenter> {
		void show(List<String> list);
	}

	interface Presenter extends BasePresenter {
		void onDestroy();
	}
}
