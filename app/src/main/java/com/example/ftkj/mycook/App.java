package com.example.ftkj.mycook;

import android.app.Application;
import android.content.Context;

/**
 * Created by FTKJ on 2017/5/22.
 */

public class App extends Application {
    private static Context mContext;
    public static boolean isFirst;
    @Override
    public void onCreate() {
        super.onCreate();
        isFirst = true;
        mContext = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
