package com.example.tangzhifeng.paperairplane.data.douban.source.local;

import android.content.Context;

import com.example.tangzhifeng.paperairplane.data.InSeaDbHelper;
import com.example.tangzhifeng.paperairplane.data.douban.source.DoubanDateSource;

/**
 * 作者: tangzhifeng on 2017/2/27.
 * 邮箱: tzfjobmail@gmail.com
 */

public class DoubanLocalDateSource implements DoubanDateSource {

    static DoubanLocalDateSource sLocalDateSource;

    private InSeaDbHelper mInSeaDbHelper;

    private DoubanLocalDateSource(Context context) {
        mInSeaDbHelper=new InSeaDbHelper(context);
    }

    public static DoubanLocalDateSource getInstance(Context context) {
        if(sLocalDateSource==null){
            sLocalDateSource=new DoubanLocalDateSource(context);
        }
        return sLocalDateSource;
    }

    @Override
    public void getDouban(String id, DoubanCallback loadDoubanCallback) {

    }

    @Override
    public void getDoubanList(DoubanListCallback loadDoubanListCallback) {

    }

    @Override
    public void getDoubanList(String date, DoubanListCallback remoteDoubanListCallback) {

    }
}
