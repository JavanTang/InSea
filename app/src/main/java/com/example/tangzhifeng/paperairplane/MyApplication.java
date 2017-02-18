package com.example.tangzhifeng.paperairplane;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext=getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
