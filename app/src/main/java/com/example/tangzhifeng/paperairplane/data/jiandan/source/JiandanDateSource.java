package com.example.tangzhifeng.paperairplane.data.jiandan.source;

import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface JiandanDateSource {
    interface JiandanCallback{
        void onFailure(Exception e);
        void onSuccess(Jiandan jiandan);
    }

    void getJiandan(JiandanCallback jiandanCallback);
    void saveJiandan(Jiandan jiandan);
}
