package com.medivh.daemon;

import android.app.Application;

import com.medivh.damon.Daemon;

/**
 * Created by Medivh on 2018/3/23.
 */

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDaemon();

    }

    private void initDaemon() {
        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        Daemon.initialize(this, DemoService.class, Daemon.DEFAULT_WAKE_UP_INTERVAL);
        DemoService.sShouldStopService = false;
        Daemon.startServiceMayBind(DemoService.class);
    }
}
