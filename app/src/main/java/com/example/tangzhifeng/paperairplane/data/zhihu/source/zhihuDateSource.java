package com.example.tangzhifeng.paperairplane.data.zhihu.source;

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

    interface CheckZhihuListUpdateCallBack{
        void onZHihuListUpdate(ZhiHuList zhiHuList);
        void onZhihuListNotUpdate();
    }

    //检查知乎日报是否更新
    void isZhihuListUpdate(ZhiHuList zhiHuList,CheckZhihuListUpdateCallBack checkZhihuListUpdateCallBack);

    //获取知乎日报列表集合
    void getZhiHuList(@NonNull LoadZhiHuListCallback loadZhiHuListCallback);
    void getZHihuList(String date,LoadZhiHuListCallback loadZhiHuListCallback);

    //保存知乎日报列表
    void saveZhiHuList(List<ZhiHuList> zhiHuLists);

    //获取知乎详细信息
    void getZhihu(String id,GetZhiHuCallback getZhiHuCallback);

    //保存知乎详细信息
    void saveZhihu(ZhiHu zhiHu);

    //删除知乎详细信息
    void deleteZhiHu(ZhiHu zhiHu);
    void deleteZhiHu(String id);



}
