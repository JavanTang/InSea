package com.example.tangzhifeng.paperairplane.data.zhihu.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/2/15.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface ZhihuDateSource {


    interface LoadZhiHuListCallback{
        void onZhiHuListLoaded(List<ZhiHuList> zhiHuLists);
        void onZhiHuListNotAvailable();
    }
    interface GetZhiHuCallback{
        void onZhiHuLoaded(ZhiHu zhiHu);
        void onZhiHuNotAvailable();
    }

    void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback);
    void getZHihuList(String date,LoadZhiHuListCallback loadZhiHuListCallback);
    void getZHihuList(String date, LoadZhiHuListCallback loadZhiHuListCallback, Context context);
    void saveZhiHuList(List<ZhiHuList> zhiHuLists);
    void refreshZhiHuList();

    void getZhihu(GetZhiHuCallback getZhiHuCallback);
    void getZhihu(String id,GetZhiHuCallback getZhiHuCallback);
    void saveZhihu(ZhiHu zhiHu);
    void deleteZhiHu(ZhiHu zhiHu);
    void deleteZhiHu(String id);



}
