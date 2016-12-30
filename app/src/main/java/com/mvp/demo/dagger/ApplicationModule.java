package com.mvp.demo.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


/**
 * 提供者
 */

@Module
public final class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

}