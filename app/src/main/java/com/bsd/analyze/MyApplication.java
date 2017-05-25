package com.bsd.analyze;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author ShiDa.Bian
 * @date 2017/5/25.
 */
public class MyApplication extends Application {
    //在需要使用的地方观察
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        refWatcher = LeakCanary.install(this);
    }
}
