package com.wenbchen.android.imdb.application;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wenbchen on 7/12/16.
 */
public class IMDBSearchApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
