package com.example.tangzhifeng.paperairplane.data.jiandan.source.remote;

import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;
import com.example.tangzhifeng.paperairplane.data.jiandan.source.JiandanDateSource;
import com.example.tangzhifeng.paperairplane.util.Api;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;
import com.google.gson.Gson;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JiandanDateRemoteSource implements JiandanDateSource {
    private static JiandanDateRemoteSource sDateRemoteSource;

    public JiandanDateRemoteSource() {

    }

    public static JiandanDateRemoteSource getInstance() {
        if(sDateRemoteSource==null){
            sDateRemoteSource=new JiandanDateRemoteSource();
        }
        return sDateRemoteSource;
    }
    @Override
    public void getJiandan(final JiandanCallback jiandanCallback) {
        HttpUtil.sendHttpRequest(Api.JIANDAN, new HttpUtil.IHttpCallbackListenet() {
            @Override
            public void onFinish(String response) {
                Gson gson=new Gson();
                Jiandan jiandan=gson.fromJson(response,Jiandan.class);
                jiandanCallback.onSuccess(jiandan);
            }

            @Override
            public void onError(Exception e) {
                jiandanCallback.onFailure(e);
            }
        });
    }

    @Override
    public void saveJiandan(Jiandan jiandan) {

    }
}
