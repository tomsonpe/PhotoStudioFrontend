package com.assignmenttp.config.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/05/13.
 */
public class App extends Application{
    private static Context context;
    private static App singleton;

    public void onCreate(){
        super.onCreate();
        App.context = getContext();
        singleton = this;
    }

    public static Context getContext(){
        return App.context;
    }
}
