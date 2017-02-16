package com.example.tangzhifeng.paperairplane.util;

import android.os.Handler;
import android.os.Message;

public class AsynNetUtils {
    public interface Callback{
        void onResponse(String response);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    public static void get(final String url, final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.get(url);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        callback.onResponse(response);
//                    }
//                });
                callback.onResponse(response);

            }
        }).start();
    }

    public static void post(final String url, final String content, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.post(url,content);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }
}