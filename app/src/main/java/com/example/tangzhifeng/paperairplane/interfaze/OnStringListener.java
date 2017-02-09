package com.example.tangzhifeng.paperairplane.interfaze;

import com.android.volley.VolleyError;

/**
 * Created by tangzhifeng on 2017/2/9.
 * 这个是Volley请求网络的时候 返回成功与失败的
 */

public interface OnStringListener {
    void onSuccess(String result);

    void onError(VolleyError e);
}
