package com.example.tangzhifeng.paperairplane.util;

import android.content.Context;
import android.support.v4.util.Pools;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by tangzhifeng on 2017/2/8.
 */

public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getVolleySingleton(Context context) {
        if (volleySingleton == null) {
            volleySingleton = new VolleySingleton(context);
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
