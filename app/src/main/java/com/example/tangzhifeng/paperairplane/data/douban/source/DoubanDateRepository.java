package com.example.tangzhifeng.paperairplane.data.douban.source;

import android.content.Context;

import com.example.tangzhifeng.paperairplane.MyApplication;
import com.example.tangzhifeng.paperairplane.data.douban.source.local.DoubanLocalDateSource;
import com.example.tangzhifeng.paperairplane.data.douban.source.remote.DoubanRemoteDateSource;
import com.example.tangzhifeng.paperairplane.util.HttpUtil;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanDateRepository implements DoubanDateSource {

    DoubanDateSource mRemote;
    DoubanDateSource mLocal;

    public DoubanDateRepository(Context context) {
        mRemote= DoubanRemoteDateSource.getInstance();
        mLocal= DoubanLocalDateSource.getInstance(context);
    }

    @Override
    public void getDouban(String uri, DoubanCallback loadDoubanCallback) {
        if (HttpUtil.isNetworkAvailable(MyApplication.getContext())) {
            mRemote.getDouban(uri, loadDoubanCallback);
        } else {
            mLocal.getDouban(uri, loadDoubanCallback);
        }
    }

    @Override
    public void getDoubanList(DoubanListCallback loadDoubanListCallback) {
        if (HttpUtil.isNetworkAvailable(MyApplication.getContext())) {
            mRemote.getDoubanList(loadDoubanListCallback);
        } else {
            mLocal.getDoubanList(loadDoubanListCallback);
        }
    }

    @Override
    public void getDoubanList(String date, DoubanListCallback remoteDoubanListCallback) {
        if (HttpUtil.isNetworkAvailable(MyApplication.getContext())) {
            mRemote.getDoubanList(date, remoteDoubanListCallback);
        } else {
            mLocal.getDoubanList(remoteDoubanListCallback);
        }
    }
}
