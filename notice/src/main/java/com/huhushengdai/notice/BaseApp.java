package com.huhushengdai.notice;

import android.app.Application;

/**
 * Date： 2017/4/6
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class BaseApp extends Application {
    private static BaseApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static BaseApp getInstance() {
        return mApp;
    }
}
