/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mvp.demo.mvp;

import android.os.Handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements IUseCaseScheduler {

	private final Handler mHandler = new Handler();

	public static final int POOL_SIZE = 3;

	public static final int MAX_POOL_SIZE = 4;

	public static final int TIMEOUT = 10;
	
	private final BlockingQueue<Runnable> mWorkQueue;
	
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

	private final ThreadPoolExecutor mThreadPoolExecutor;

	public UseCaseThreadPoolScheduler() {
//		workQueue = new LinkedBlockingQueue<Runnable>();
		mWorkQueue = new LinkedBlockingQueue<Runnable>(POOL_SIZE);
		mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE,TIMEOUT, KEEP_ALIVE_TIME_UNIT, mWorkQueue);
//		mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE,TIMEOUT, KEEP_ALIVE_TIME_UNIT, mWorkQueue,new JobThreadFactory());
	}
	
	private static class JobThreadFactory implements ThreadFactory {
		private static final String THREAD_NAME = "android_";
		private int counter = 0;

		@Override
		public Thread newThread(Runnable runnable) {
			return new Thread(runnable, THREAD_NAME + counter++);
		}
	}

	@Override
	public void execute(Runnable runnable) {
		 if (runnable == null) {
		      throw new IllegalArgumentException("Runnable to execute cannot be null");
		 }
		 try {
			 mThreadPoolExecutor.execute(runnable);
		}catch(RejectedExecutionException e){
			
		} catch (Exception e) {
		}
	}

	@Override
	public <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onSuccess(response);
			}
		});
	}

	@Override
	public <V extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<V> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onError();
			}
		});
	}

}
