package com.example.tangzhifeng.paperairplane.data.jiandan.source;

import com.example.tangzhifeng.paperairplane.data.jiandan.Jiandan;
import com.example.tangzhifeng.paperairplane.data.jiandan.source.remote.JiandanDateRemoteSource;

/**
 * 作者: tangzhifeng on 2017/3/6.
 * 邮箱: tzfjobmail@gmail.com
 */

public class JiandanDateRepository implements JiandanDateSource {
    JiandanDateRemoteSource mRemoteSource;

    public JiandanDateRepository(JiandanDateRemoteSource remoteSource) {
        mRemoteSource = remoteSource;
    }

    @Override
    public void getJiandan(JiandanCallback jiandanCallback) {
        mRemoteSource.getJiandan(jiandanCallback);
    }

    @Override
    public void saveJiandan(Jiandan jiandan) {

    }
}
